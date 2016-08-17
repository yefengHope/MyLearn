package org.noka.filter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipFile;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.RequestUtils;
import org.directwebremoting.servlet.HttpConstants;
import org.noka.constvar.ConstVar;
import org.noka.function.LanguageRead;
import org.noka.print.PrintFilter;
import org.nokatag.jdom.Document;
import org.nokatag.jdom.Element;
import org.nokatag.jdom.input.SAXBuilder;
import org.nokatag.system.BugInfoWrint;
import org.nokatag.system.Concest;
import org.nokatag.system.FileUpload;
import org.nokatag.temp.MiniTemplator;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class SystemFilter implements Filter{
	private static String xmlfilepath=null;
	public void destroy() {
		
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String resourcePath = RequestUtils.getServletPath(request);
		
        if ("".equals(resourcePath) && null != request.getPathInfo()) {
            resourcePath = request.getPathInfo();
        }
        String rooturl = rooturl(request);
        //--------------------------配置项输出--------------------------------------
		for(String n:ConstVar.XMLCONST.keySet()){
			String xmlconst=ConstVar.XMLCONST.get(n);
			request.setAttribute(n,xmlconst.replace("${rooturl}",rooturl));
		}
		lung(request);//加载语言文件
		//------------------------------------------------------------------------
		request.setAttribute("rooturl", rooturl);//跟路径
		String muid = request.getParameter("muid");
		if(muid!=null && muid.trim().length()>0){
			request.setAttribute("muid", muid);//跟路径
		}
		String msg = request.getParameter("msg");
		if(msg!=null)
			request.setAttribute("msg", LanguageRead.getLang(msg));
			
		request.setAttribute("nround",System.currentTimeMillis());
		 //--------------------------session验证------------------------------------
        if(!SQLFilter.isSessionSuc(request)){//session验证失败
        	 response.setContentType("text/html; charset=UTF-8");
             response.sendRedirect(ConstVar.XMLCONST.get("no-sessionUrl").replace("${rooturl}",rooturl));
        }else if (resourcePath.startsWith("/"+ConstVar.SKINPREFIX)) {//noka 处理
			findStaticResource(resourcePath,request,response);
		}else if (resourcePath.startsWith("/print.noka")) {//print 
			PrintFilter print = new PrintFilter();
			print.Print(ConstVar.ROOTPATH, request, response);
		}else{
			try{chain.doFilter(request, response);}catch(Exception se){}
		}
	}

	@SuppressWarnings("unchecked")
	public void init(FilterConfig config) throws ServletException {
		//--------------web.xml读取-------------------------------
		xmlfilepath = config.getInitParameter("xmlfilepath");
		String rootpath = (config.getServletContext().getRealPath("/")).replace("\\", "/").replace("20%", " ");
		if(!rootpath.endsWith("/")){
			rootpath=rootpath+"/";
		}
		//------------配置项xml读取---------------------------------
		ConstVar.CONTXMLPATH=rootpath+xmlfilepath;
		ConstVar.ROOTPATH=rootpath;
		System.out.println("noka pro config file path is:"+ConstVar.CONTXMLPATH);
		try{
			SAXBuilder saxbuilder = new SAXBuilder(); // 新建立构造器 
			saxbuilder.setEntityResolver(new EntityResolver() {
                public InputSource resolveEntity(String publicId,String systemId) throws SAXException, IOException {
                	String dtd=ConstVar.CONTXMLPATH;
                	dtd=dtd.substring(0,dtd.lastIndexOf("."))+".dtd";
                	InputSource source = new InputSource(new FileInputStream(dtd));
                	source.setPublicId(publicId);  
                    source.setSystemId(systemId);
                	return source;
                }
            });
			Document doc = saxbuilder.build(new FileInputStream(ConstVar.CONTXMLPATH));
			Element rootelementroot=doc.getRootElement(); 
			List<Element>  datalist = rootelementroot.getChildren("option");
			for(Element en:datalist){
				if(en.getAttributeValue("key").equalsIgnoreCase("skin")){//皮肤配置项
					ConstVar.XMLCONST.put("skin","${rooturl}/"+ConstVar.SKINPREFIX+en.getText());
					ConstVar.SKINPATH=ConstVar.ROOTPATH+"/"+en.getText();
				}else if(en.getAttributeValue("key").equalsIgnoreCase("path-language")){//语言文件路径
					ConstVar.XMLCONST.put("path-language", ConstVar.ROOTPATH+en.getText());
				}else if(en.getAttributeValue("key").equalsIgnoreCase("no-sessionCheck")){//不需要session验证的
					ConstVar.NOSESSION=en.getText();
				}else if(en.getAttributeValue("key").equalsIgnoreCase("session-publick")){//需要session验证的公共url
					ConstVar.PUBLIC_URLS=en.getText();
				}else if(en.getAttributeValue("key").equalsIgnoreCase("cache-file")){//缓存文件
					ConstVar.CACHEFILE=("true".equalsIgnoreCase(en.getText()));
				}else if(en.getAttributeValue("key").equalsIgnoreCase("server-manid")){//主版本号
					ConstVar.SERVER_MAIN_ID=Integer.parseInt(null==en.getText()?"1":en.getText());
				}else if(en.getAttributeValue("key").equalsIgnoreCase("server-subid")){//次版本号
					ConstVar.SERVER_SUB_ID=Integer.parseInt(null==en.getText()?"2":en.getText());
				}else{
					ConstVar.XMLCONST.put(en.getAttributeValue("key"),en.getText());
				}
			}
			//------------------------dbtable-filters-------------------------------------
			List<Element> dbtables = rootelementroot.getChildren("dbtable-filters");
			List<Element> table = dbtables.get(0).getChildren("table");
			for (Element en : table) {
				Map<String,String> ma= new HashMap<String, String>();
				ma.put("key",en.getAttributeValue("key"));
				ma.put("userfield",en.getAttributeValue("user-field"));
				ma.put("deptfield",en.getAttributeValue("dept-field"));
				ma.put("lang",en.getAttributeValue("lang"));
				String select = en.getAttributeValue("select");
				StringBuffer select_str = new StringBuffer();
				if(select==null)
					ma.put("select","[{value:'1',label:'$[org.noka.sys.alldept]'},{value:'2',label:'$[org.noka.sys.thedept]'},{value:'3',label:'$[org.noka.sys.thesubdept]'},{value:'4',label:'$[org.noka.sys.usermy]'},{value:'5',label:'$[org.noka.sys.appdept]'}]");
				else{
					String[] se = select.split(",");
					select_str.append("[");
					for(int i=0;i<se.length;i++){
						if("true".equalsIgnoreCase(se[i])){
							if(i>=4)
								select_str.append(ConstVar.DBTABLE_SELECT[i]);
							else
								select_str.append(ConstVar.DBTABLE_SELECT[i]+",");
						}
					}
					select_str.append("]");
					ma.put("select",select_str.toString());
				}
				ConstVar.DBTABLE.add(ma);
			}
			// -----------print----------------------------------------------
			List<Element> prints = rootelementroot.getChildren("prints");
			List<Element> print = prints.get(0).getChildren("print");
			for (Element en : print) {
				Map<String,String> ma= new HashMap<String, String>();
				ma.put("id",en.getAttributeValue("id"));
				ma.put("class",en.getAttributeValue("class"));
				if(en.getAttributeValue("xslfile")==null){
					String class_str=en.getAttributeValue("class");
					String file_name=class_str.substring(class_str.lastIndexOf(".")+1,class_str.length());//file name
					class_str=class_str.substring(0,class_str.lastIndexOf("."));
					class_str=class_str.replace(".", "/");
					String xslfile=ConstVar.ROOTPATH+"/WEB-INF/classes/"+class_str+"/"+file_name+".xsl";
					ma.put("xslfile",xslfile);
				}else{
					ma.put("xslfile",en.getAttributeValue("xslfile"));
				}
				ConstVar.PRINTS.put(en.getAttributeValue("id"),ma);
			}
			//-----------------浏览器缓存-----------------------------------------------------------------------------
			List<Element>  cache_files = rootelementroot.getChildren("cache-files");
			if(cache_files!=null && !cache_files.isEmpty()){
				List<Element>  cache_file=cache_files.get(0).getChildren("cache-file");
					if(cache_file!=null && !cache_file.isEmpty()){
						for(Element c:cache_file){
							String extension = c.getAttributeValue("extension");//扩展名 *.js
							String expires = c.getAttributeValue("expires");//到期期限 2D 表示2天,2h表示2小时,2m表示2分 2s表示2秒
							ConstVar.CACHE_FILES.put(extension,expires);
						}
					}
			}
		}catch(Exception se){
		}
		//------------配置项xml读取end---------------------------------
	}
	/**
	 * 获取系统虚拟绝对路径
	 * @param request
	 * @return
	 */
	private  String rooturl(HttpServletRequest request){
		String path = request.getContextPath();
		return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
	}
	/**
	 * 语言选择
	 * @param request
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public  void lung(HttpServletRequest request){
		try{
			if(request.getHeader("accept-language")==null) return;
			String mark = request.getHeader("accept-language").split(",")[0];//客户端语言
			mark=mark.toLowerCase();//将所有大写转换成小写
			request.setAttribute("syslanuage", mark);
			// ---------从内存取语言文件--------------------------------------------
			if (ConstVar.LANGUAGES.get(mark) != null) {
				Map<String, Map> ma = ConstVar.LANGUAGES.get(mark);
				for (String k : ma.keySet()) {
					request.setAttribute(k, ma.get(k));
				}
				return;
			} else {
				ConstVar.LANGUAGES.put(mark, new HashMap<String, Map>());
				ConstVar.LANGUAGES_STR.put(mark, new HashMap<String, String>());
			}
			//------------------------------------------------------------------------------
			String fp = ConstVar.XMLCONST.get("path-language")+"/language_"+mark+".xml";
			File file = new File(fp);
			InputStream input = null;
			if(!file.exists()){//所属语言文件不存在，使用默认加载语言
				fp=ConstVar.XMLCONST.get("path-language")+"/language_"+Concest.DEFAULTLANGUAGE+".xml";
				input = new FileInputStream(fp);
			}else{
				input = new FileInputStream(fp);
			}
			SAXBuilder saxbuilder = new SAXBuilder(); // 新建立构造器 
			saxbuilder.setEntityResolver(new EntityResolver() {
                public InputSource resolveEntity(String publicId,String systemId) throws SAXException, IOException {
                	String dtd=ConstVar.XMLCONST.get("path-language")+"/language.dtd";
                	InputSource source = new InputSource(new FileInputStream(dtd));
                	source.setPublicId(publicId);  
                    source.setSystemId(systemId);
                	return source;
                }
            });
			Document doc = saxbuilder.build(input);//加载语言文件
			Element rootelementroot=doc.getRootElement(); 
			List<Element>  page = rootelementroot.getChildren("page");//取得包分类 org.noka.sys
			for(Element en:page){
				String id=en.getAttributeValue("id");//org.noka.sys
				Map<String,Map> topmap=new HashMap<String,Map>();//顶级Map
				Map endMap=new HashMap();//最后一级map
				//-------------检查是否已经有该map------------------------
				Map oldMap=null;
				try{
					oldMap = (Map)request.getAttribute(id.split("\\.")[0]);
				}catch(Exception se){}
				if(oldMap!=null)
					endMap=oldMap;
				//--------------------------------------------
				topmap=endMap;//开始最后一级和第一级为同一个map
				for(int i=1;i<id.split("\\.").length;i++){
					//-----------检查是否已经有该Map--------------
					Map tmap = null;// 
					try{
						tmap=(Map)endMap.get(id.split("\\.")[i]);
					}catch(Exception se){
						
					}
					//------------------------------------------
					if(tmap==null){//如果没有，新建
						tmap = new HashMap();
						endMap.put(id.split("\\.")[i], tmap);
					}
					endMap=tmap;
				}
				List<Element> option=en.getChildren("option");
				for(Element op:option){
					endMap.put(op.getAttributeValue("key"),op.getText());
					ConstVar.LANGUAGES_STR.get(mark).put(id + "." + op.getAttributeValue("key"),op.getText());
				}
				request.setAttribute(id.split("\\.")[0],topmap);
				ConstVar.LANGUAGES.get(mark).put(id.split("\\.")[0], topmap);
			}
		}catch(Exception se){
			//BugInfoWrint.Print(se);
		}
	}
	/**
	 * 皮肤文件读取
	 * @param name
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public  InputStream GetSkinsFile(String name) {
		try {
			InputStream in=null;
			if(ConstVar.CACHEFILE){//是否缓存文件
				if(ConstVar.FILE_CACHE.get(name)==null && name.indexOf(ConstVar.SKINPREFIX)!=-1){
					ZipFile zipFile = new ZipFile(ConstVar.SKINPATH);
					String finame=name.substring(name.indexOf(".nks/")+".nks/".length(),name.length());
					InputStream fi= zipFile.getInputStream(zipFile.getEntry(finame));
					ByteArrayOutputStream arrayOutputStream =new ByteArrayOutputStream();
					byte[] bytes = new byte[1];
					while(fi.read(bytes) != -1) {
						arrayOutputStream.write(bytes);
					}
					arrayOutputStream.close();
					fi.close();
					ConstVar.FILE_CACHE.put(name, arrayOutputStream.toByteArray());
					ConstVar.FILE_LAST_UPDATE_TIME.put(name, new Date().getTime());//缓存文件最后修改时间
					BugInfoWrint.Print("重新生成内存文件："+name);
					zipFile.close();
				}
				return new ByteArrayInputStream((byte[])ConstVar.FILE_CACHE.get(name));
			}else{
				if(name.indexOf(ConstVar.SKINPREFIX)!=-1){
					ZipFile zipFile = new ZipFile(ConstVar.SKINPATH);
					String finame=name.substring(name.indexOf(".nks/")+".nks/".length(),name.length());
					in= zipFile.getInputStream(zipFile.getEntry(finame));
					return in;
				}
			}
			
		}catch (IOException e1) {
		}
		return null;

	}
	/**
	 * 输出文件到网页上
	 * @param name
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	protected void findStaticResource(String name, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(name.indexOf(".")!=-1){
        	//----------------------------------------------------------------------------------
			String Prefixes=name.substring(name.lastIndexOf("."),name.length());//文件扩展名
			InputStream input = GetSkinsFile(name);
	        String contentType = getContentType(Prefixes);
	        if (contentType != null) {
	          response.setContentType(contentType);
	        }
	        //----------------------------------------------------------------------------------
	        Long lasttime = ConstVar.FILE_LAST_UPDATE_TIME.get(name);
	        if(lasttime!=null && isUpToDate(request,lasttime)){
	        	response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);//不需要更新
	        	return;
	        }
	        //---------------------------------------------------------------------------------
	        response.setHeader("only-if-cached", "public");
	        String extension=ConstVar.CACHE_FILES.get("*"+Prefixes);//缓存期限
	        if(extension==null){
	        	extension=ConstVar.CACHE_FILES.get(name);//缓存期限
	        }
	        if(extension==null){
		        response.setHeader("Cache-Control", "public");
	        }else if(extension.endsWith("D")){//天为单位
	        	int d=Integer.parseInt(extension.substring(0,extension.length()-1));//天数
	        	long s=d*24*60*60;
	        	GregorianCalendar cal = new GregorianCalendar();
	        	cal.setTimeInMillis(lasttime==null?new Date().getTime():lasttime);
	        	cal.add(GregorianCalendar.DATE, d);//在日期上加d天
	        	response.setHeader("Cache-Control", "max-age="+s);
	        	response.setDateHeader("Expires", cal.getTimeInMillis());
	        }else if(extension.endsWith("h")){//小时为单位
	        	int h=Integer.parseInt(extension.substring(0,extension.length()-1));//小时
	        	long s=h*60*60;
	        	GregorianCalendar cal = new GregorianCalendar();
	        	cal.setTimeInMillis(lasttime==null?new Date().getTime():lasttime);
	        	cal.add(GregorianCalendar.HOUR, h);//在日期上加h小果
	        	response.setHeader("Cache-Control", "max-age="+s);
	        	response.setDateHeader("Expires", cal.getTimeInMillis());
	        }else if(extension.endsWith("s")){//秒为单位
	        	int s=Integer.parseInt(extension.substring(0,extension.length()-1));//秒
	        	GregorianCalendar cal = new GregorianCalendar();
	        	cal.setTimeInMillis(lasttime==null?new Date().getTime():lasttime);
	        	cal.add(GregorianCalendar.SECOND, s);//在日期上加s秒
	        	response.setHeader("Cache-Control", "max-age="+s);
	        	response.setDateHeader("Expires", cal.getTimeInMillis());
	        }else if(extension.endsWith("m")){//分为单位
	        	int m=Integer.parseInt(extension.substring(0,extension.length()-1));//分钟
	        	long s=m*60;
	        	GregorianCalendar cal = new GregorianCalendar();
	        	cal.setTimeInMillis(lasttime==null?new Date().getTime():lasttime);
	        	cal.add(GregorianCalendar.MINUTE, m);//在日期上加m分种
	        	response.setHeader("Cache-Control", "max-age="+s);
	        	response.setDateHeader("Expires", cal.getTimeInMillis());
	        }
	        if(name!=null){
		        response.setDateHeader(HttpConstants.HEADER_LAST_MODIFIED, ConstVar.FILE_LAST_UPDATE_TIME.get(name));
		        response.setHeader(HttpConstants.HEADER_ETAG, "\"" + ConstVar.FILE_LAST_UPDATE_TIME.get(name) + '\"');
	        }
	        //---------------------------------------------------------------------------------
	        try {
	          copy(input, response.getOutputStream(),request,name);
	        } catch(Exception s){
	        	response.sendError(HttpServletResponse.SC_NOT_FOUND);//异常
	        	BugInfoWrint.Print(s);
	        }finally {
	          input.close();
	        }
        }
        
    }
	/**
	 * 文件输出类型
	 * @param name
	 * @return
	 */
	protected String getContentType(String name) {
        if (name.endsWith(".js")) {
            return "text/javascript";
        } else if (name.endsWith(".css")) {
            return "text/css";
        } else if (name.endsWith(".html")) {
            return "text/html";
        } else if (name.endsWith(".htm")) {
            return "text/html";
        }else if (name.endsWith(".txt")) {
            return "text/plain";
        } else if (name.endsWith(".gif")) {
            return "image/gif";
        } else if (name.endsWith(".jpg") || name.endsWith(".jpeg")) {
            return "image/jpeg";
        } else if (name.endsWith(".png")) {
            return "image/png";
        } else {
            return null;
        }
    }
	/**
	 * 判读是否为文本文件
	 * @param name
	 * @return
	 */
	protected Boolean isText(String name) {
        if (name.endsWith(".js")) {
            return true;
        } else if (name.endsWith(".css")) {
        	return true;
        } else if (name.endsWith(".html")) {
        	return true;
        } else if (name.endsWith(".htm")) {
        	return true;
        }else if (name.endsWith(".txt")) {
        	return true;
        }else {
            return false;
        }
    }
	/**
	 * 文件拷贝输出
	 * @param input
	 * @param output
	 * @throws IOException
	 */
	 protected void copy(InputStream input, OutputStream output,HttpServletRequest request,String name) throws IOException {
	    try{
	   
		 if(name.endsWith(".js") || name.endsWith(".html") || name.endsWith(".css") || name.endsWith("txt") || name.endsWith(".htm")){
	    	 //---------------------------------------
	    	 MiniTemplator t = new MiniTemplator(input);
	    	 t.setVariable ("rooturl",rooturl(request));
	    	 t.setVariable("skin", ConstVar.SKINPATH);
	    	 t.setVariable("datalist", FileUpload.filetable(request));
		     for(String key:ConstVar.XMLCONST.keySet()){
		    	 String xmlconst=ConstVar.XMLCONST.get(key);
		    	 t.setVariable (key,xmlconst.replace("${rooturl}", rooturl(request)));
		     }
		     t.generateOutput(output);
	     }else{
	    	 final byte[] buffer = new byte[1];
	         int n;
	         while (-1 != (n = input.read(buffer))) {
	             output.write(buffer, 0, n);
	         }
	    	
	     }
	     output.flush();
		 output.close();
		 input.close();
	    }catch(Exception se){
	    }
	 }
	 
	 protected boolean isUpToDate(HttpServletRequest req, Long lastModified)
	    {
		 	if(lastModified==null)
		 		return false;
	        String etag = "\"" + lastModified + '\"';
	        long modifiedSince = -1;
	        try
	        {
	            // HACK: Websphere appears to get confused sometimes
	            modifiedSince = req.getDateHeader(HttpConstants.HEADER_IF_MODIFIED);
	        }
	        catch (RuntimeException ex)
	        {
	        }

	        if (modifiedSince != -1)
	        {
	            // Browsers are only accurate to the second
	            modifiedSince -= modifiedSince % 1000;
	        }
	        String givenEtag = req.getHeader(HttpConstants.HEADER_IF_NONE);
	        // Deal with missing etags
	        if (givenEtag == null)
	        {
	            // There is no ETag, just go with If-Modified-Since
	            if (modifiedSince >= lastModified)
	            {
	                return true;
	            }
	            // There are no modified settings, carry on
	            return false;
	        }
	        // Deal with missing If-Modified-Since
	        if (modifiedSince == -1)
	        {
	            if (!etag.equals(givenEtag))
	            {
	                return true;
	            }
	            // There are no modified settings, carry on
	            return false;
	        }
	        // Do both values indicate that we are in-date?
	        if (etag.equals(givenEtag) || modifiedSince >= lastModified)
	        {
	            return true;
	        }
	        return false;
	    }
}
