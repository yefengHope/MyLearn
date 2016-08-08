package org.noka.print;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.noka.constvar.ConstVar;
import org.nokatag.system.Concest;

/**
 * 打印
 * @author rebin
 *
 */
public class PrintFilter {

	@SuppressWarnings({ "unused", "rawtypes" })
	public void Print(String rootpath,HttpServletRequest request,HttpServletResponse response){
		String optype = request.getParameter("o");//业务类型
		String filetype = request.getParameter("t");//file type
		Integer fpt=Integer.valueOf(filetype);
		PrintUtil printutil=null;
		Map ma=ConstVar.PRINTS.get(optype);
		try{
			String fileext = PrintUtil.FILEEXT[fpt];
			if("rtf".equalsIgnoreCase(fileext)){
				response.addHeader("Content-Disposition", "attachment;filename=nokaprint."+fileext);
			}
			response.setContentType(PrintUtil.TYPES[fpt]);
			//---------------生成xsl文件------------------------------
			InputStream xsl = new FileInputStream(ma.get("xslfile").toString());
			String xml=SerFunction(ma.get("class").toString(),request);
			InputStream data= new ByteArrayInputStream(xml.toString().getBytes("UTF-8"));
			PrintFile.GetPrintFile(rootpath,data,xsl,response.getOutputStream(),PrintUtil.TYPES[fpt]);//输出
		}catch(Exception se){
			se.printStackTrace();
		}
	}
	//-----------------------------------------------------------------------------
	@SuppressWarnings({ "rawtypes", "resource" })
	public static String SerFunction(String path,HttpServletRequest request){
		try{
			Class myClass  = Class.forName(path);
			if(myClass==null){
				URL url = new  URL("file:"+Concest.ROOTDIR+"WEB-INF/classes");
				URLClassLoader myClassLoader  =   new  URLClassLoader( new  URL[]   {url} , Thread.currentThread().getContextClassLoader()); 
				myClass  = myClassLoader.loadClass(path);
			}
			if(myClass!=null){
				PrintUtil pru=(PrintUtil)myClass.newInstance();
				return pru.GetPrint(request);
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
}
