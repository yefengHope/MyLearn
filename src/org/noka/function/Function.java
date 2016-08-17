package org.noka.function;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.noka.constvar.ConstVar;
import org.noka.item.ButtonItem;
import org.nokatag.jdom.Document;
import org.nokatag.jdom.Element;
import org.nokatag.jdom.input.SAXBuilder;
import org.nokatag.system.ServletNokaContext;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * 系统方法类
 * @author xiefangjian
 *
 */
public class Function {
	
	
	//--------------------------------------------------------------
	/*
	 * 根基不同的分隔符分隔
	 */
	public static List<String> StringToList(String str,String fg){
		List<String> list = new ArrayList<String>();
		for(String strn:str.split(fg)){
			list.add(strn);
		}
		return list;
	}
	
	/**
	 * 按钮分隔
	 * @param str
	 * @param fg ,  
	 * @param fg2 @
	 * @return
	 */
	public static List<ButtonItem> StringToButton(String str,String fg,String fg2){
		List<ButtonItem> list = new ArrayList<ButtonItem>();
		for(String strn:str.split(fg)){
			ButtonItem button = new ButtonItem();
			String[] buttons = strn.split(fg2);
			button.setPage(buttons[0]);
			button.setText(buttons[1]);
			button.setBname(buttons[2]);
			button.setBstr(strn);
			list.add(button);
		}
		return list;
	}
	/**
	 * 根据key返回具体的值
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String XmlConstValue(String key){
		String skins="";
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
				if(en.getAttributeValue("key").equals(key)){//找到key
					skins= en.getText();
				}
			}
		}catch(Exception se){
			se.printStackTrace();
		}
		return skins;
	}
	/**
	 * 通用格式化日期字符串
	 * @param date
	 * @return
	 */
	public static String formdate(String date){
		String sb=date;
		SimpleDateFormat[] ACCEPT_DATE_FORMATS = {   
	        new SimpleDateFormat("yyyy-MM-dd"),   
	        new SimpleDateFormat("yyyy年MM月dd日 hh:mm"),
	        new SimpleDateFormat("yyyy年MM月dd日"),
	        new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"),
	        new SimpleDateFormat("yyyy-MM-dd HH:mm"),
	        new SimpleDateFormat("yyyy-MM-dd kk:mm"),
	        new SimpleDateFormat("MM/dd/yyyy hh:mm:ss"),
	        new SimpleDateFormat("yyyy/MM/dd hh:mm")
	      };   
		SimpleDateFormat sft=new SimpleDateFormat("yyyy-MM-dd");
			for(int i=0;i<ACCEPT_DATE_FORMATS.length;i++){
				SimpleDateFormat sf=ACCEPT_DATE_FORMATS[i];
				try{
					return sft.format(sf.parse(date));
				}catch(Exception se){
					System.out.println("a:"+date+"   "+se.toString());
				}
			}
			return sb;
	}
	/**
	 * 查找数据库指定的表里面的指定字段是否有该值
	 * @return 有返回0 没有返回1
	 */
	public static String finduser(String v,String filde,String table){
		String rv="0";
		try{
			if(v==null || v.trim().length()<1)
				return rv;
			Connection con = ServletNokaContext.getConnection();
			PreparedStatement pst = con.prepareStatement("select "+filde+" from "+table+" where "+filde+"=? ");
			pst.setString(1,v);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				rv="0";//找到
			}else{
				rv="1";//未找到
			}
			rs.close();
			pst.close();
		}catch(Exception se){
			rv="0";//调用失败
			se.printStackTrace();
		}
		return rv;
	}
	/**
	 * 取得文件
	 * @param request
	 * @return
	 */
	public static String getFileName(HttpServletRequest request){
		String uri=request.getRequestURI();
		if(uri.indexOf(";")!=-1)
			uri=uri.substring(0,uri.indexOf(";"));
		if(uri!=null && uri.trim().length()>0){
			if(uri.lastIndexOf("/")<uri.length()-1)
				uri=uri.substring(uri.lastIndexOf("/")+1,uri.length());
			else
				return null;
		}
		return uri;
	}
	/**
	 * 取得文件
	 * @param uri
	 * @return
	 */
	public static String getFileName(String uri){
		if(uri.indexOf("?")!=-1)
			uri=uri.substring(0,uri.indexOf("?"));
		if(uri!=null && uri.trim().length()>0){
			if(uri.lastIndexOf("/")<uri.length()-1)
				uri=uri.substring(uri.lastIndexOf("/")+1,uri.length());
			else
				return null;
		}
		return uri;
	}
	/**
	 * 根据key返回值
	 * @param key
	 * @return
	 */
	public static String KeyToValue(String key){
		if("1".equals(key))
			return "男";
		if("2".equals(key))
			return "女";
		return "";
	}
	/**
	 * 根据用户ID返回用户姓名
	 * @param v
	 * @param request
	 * @return
	 */
	public static String getfugle(String v){
		String rv="";
		try{
			if(v==null || v.trim().length()<1)
				return rv;
			Connection con = ServletNokaContext.getConnection();
			PreparedStatement pst = con.prepareStatement("select USXNAME from NK_SYS_USERINFO where USID=? ");
			pst.setString(1,v);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				rv=rs.getString(1);
			}
			rs.close();
			pst.close();
		}catch(Exception se){
		}
		return rv;
	}
	//-------------------------------------------------------------------------
	public static String findDept(String v){
		String rv="";
		try{
			if(v==null)
				return rv;
			Connection con = ServletNokaContext.getConnection();
			PreparedStatement pst = con.prepareStatement("select ODNAME from NK_SYS_ODEPT where ODID=? ");
			pst.setString(1,v);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				rv=rs.getString(1);
			}else{
				rv="";//未找到
			}
			rs.close();
			pst.close();
		}catch(Exception se){
			rv="";//调用失败
			se.printStackTrace();
		}
		return rv;
	}
//----------
}
