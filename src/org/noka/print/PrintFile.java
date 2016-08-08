package org.noka.print;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

/**
 * 打印文件生成，包括pdf
 * @author rebin
 * @create 2011-09-09
 */
public class PrintFile {
	private static final String fp="WEB-INF/classes/org/noka/print/";
	
	/**
	 * @param rootpath
	 * @param data
	 * @param xsl
	 * @param out
	 */
	public static void GetPrintFile(String rootpath,InputStream data,InputStream xsl,OutputStream out,String FileType){
		try{
			//--------------重写配置文件-----------------------------
			WriteConfig(rootpath+fp+"config.xml",rootpath+fp+"tconfig.xml","${rootdir}",rootpath+fp);
			//------------------------------------------------------
			FopFactory fopFactory = FopFactory.newInstance();//初始化
            fopFactory.setUserConfig(rootpath+fp+"tconfig.xml"); //加载配置文件
            FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
            out = new BufferedOutputStream(out);
            try {
	            Fop fop = fopFactory.newFop(FileType, foUserAgent, out);
	            TransformerFactory factory = TransformerFactory.newInstance();
	            Transformer transformer = factory.newTransformer(new StreamSource(xsl));
	            transformer.setParameter("versionParam", "2.0");
	            Source src = new StreamSource(data);
	            Result res = new SAXResult(fop.getDefaultHandler());
	            transformer.transform(src, res);
            } finally {
                out.close();
            }
		}catch(Exception se){
			se.printStackTrace();
		}
	}
	
	/**
	 * 替换文件中的变量
	 * @param file
	 * @param var
	 * @param value
	 */
	private static void  WriteConfig(String rfile,String wfile,String var,String value){
		try{
			//-------------配置文件检查------------------------------------
			File file = new File(wfile);
			if(!file.exists())
				return ;
			//------------读取---------------------------------------------
			BufferedReader br = new BufferedReader(new FileReader(rfile));//读取
			BufferedWriter bw = new BufferedWriter(new FileWriter(wfile));//写入
			String body=null;
			StringBuffer bodybf=new StringBuffer();
			while((body=br.readLine())!=null){
				bodybf.append(body.replace(var, value)+"\n");
			}
			br.close();
			bw.write(bodybf.toString());
			bw.flush();
			bw.close();
		}catch(Exception se){
			se.printStackTrace();
		}
	}
	
	
	public static void main(String[] args){
		try{
			PrintFile.GetPrintFile("D:/eclipsework/ar/src/", 
					new FileInputStream("D:/eclipsework/FO/src/org/noka/data/xmls/personal.dm.xml"),
					new FileInputStream("D:/eclipsework/FO/src/t4.xsl"),
					new FileOutputStream("D:/dd.pdf"),
					MimeConstants.MIME_PDF);
		}catch(Exception se){
			se.printStackTrace();
		}
	}
	
	

}
