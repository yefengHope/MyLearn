package org.noka.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nokatag.system.ServletNokaContext;
/**
 * web监控接口
 * @author rebin
 *
 */
@SuppressWarnings("serial")
public class WebCheckServlet extends HttpServlet {
	private static final long FREQUENCY_MINUTE = 2;//2分钟内只能处理一次
	private static Date REQUEST_NEWDATE = null;
	public WebCheckServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); 
	}
	public void init() throws ServletException {
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String reCheck = "OK";
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//-------------------------------频率检测------------------------------------------
		if(minute(REQUEST_NEWDATE)<FREQUENCY_MINUTE){//在FREQUENCY_MINUTE分钟内，直接返回，不作处理
			response.setStatus(HttpServletResponse.SC_OK);
			reCheck="OK";
		}else{//-------------------------------自定义状态检测-------------------------------
			try{
				Connection con = ServletNokaContext.getConnection();
				PreparedStatement pa=  con.prepareStatement("SELECT COUNT(1) FROM NK_SYS_MENU");
				ResultSet rs = pa.executeQuery();
				if(rs.next()){
					response.setStatus(HttpServletResponse.SC_OK);//成功返回200
					reCheck="OK";
				}
				rs.close();
				pa.close();
				System.out.println(sf.format(new Date())+":请求成功------------------------------------------------");
			}catch(Exception se){
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);//错误返回500
				System.out.println(sf.format(new Date())+"--------------------------------------------------------------------------");
				se.printStackTrace();
				reCheck="ER";
			}
		}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(reCheck);
		out.flush();
		out.close();
	}

	/**
	 * 计算传入的时间与现在系统时间比较，相差多少分钟
	 * @param udate
	 * @return
	 */
	private long minute(Date udate){
		long minute = 0;
		if(null!=udate){
			Date ndate = new Date();
			long ntime = ndate.getTime() - udate.getTime();
			minute = ntime /(1000 *60);
		}
		return minute;
	}
	

}
