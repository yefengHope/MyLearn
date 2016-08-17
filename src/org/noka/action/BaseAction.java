package org.noka.action;

import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Writer;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.noka.function.LanguageRead;
import org.noka.gson.Gson;
import org.noka.item.DlForRoleItem;
import org.noka.item.UserItem;
import org.nokatag.system.Concest;
import org.nokatag.system.DataUtil;
import org.nokatag.system.ServletNokaContext;

import com.opensymphony.xwork2.ActionSupport;
/**
 * 基础action
 * @author xiefangjian
 *
 */
public class BaseAction extends ActionSupport{
	private static final long serialVersionUID = -5064593600501988897L;
	protected String msg=null;//操作消息
	protected String muid=null;//菜单id
	
	@Action(value="setnowurl")
	public String login(){
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().setAttribute("nowurl", request.getParameter("nowurl"));
		return null;
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getMuid() {
		return muid;
	}
	public void setMuid(String muid) {
		this.muid = muid;
	}
	public String $L(String key){
		return LanguageRead.getLang(key);
	}
	/**
	 * <p>@Description 输出JSON到页面上</p>
	 * <p>author rebin </p>
	 * <p>@Time 下午9:19:16</p>
	 * <p>xiefangjian@163.com</p>
	 * @param t
	 */
	protected <T> void $PJson(T t){
		try{
			Gson gson = new Gson();
			HttpServletResponse response = ServletActionContext.getResponse();
			//PrintStream out = new PrintStream(response.getOutputStream());//获取out输出对象
			Writer wr=new OutputStreamWriter(response.getOutputStream(),Concest.ENCODING);
			wr.write(gson.toJson(t));
			wr.flush();
			//out.println(gson.toJson(t));
		}catch(Exception se){}
	}
	/**
	 * 打印页面消息
	 * @param msg
	 */
	protected void $P(String msg){
		try{
			HttpServletResponse response = ServletActionContext.getResponse();
			PrintStream out = new PrintStream(response.getOutputStream());//获取out输出对象
			out.println(msg);
		}catch(Exception se){}
	}
	/**
	 * 数据过滤方法
	 * @param sql
	 * @param tablename
	 * @param request
	 * @return
	 */
	protected  String sqlFilter(String sql,String tablename){
		UserItem user = (UserItem)ServletNokaContext.getRequest().getSession().getAttribute("user");
		String userdept = (String)ServletNokaContext.getRequest().getSession().getAttribute("userdept");//下属所有部门ID
		String role = user.getUsrole();
		List<DlForRoleItem> dlForRoleItem = DataUtil.objectList("from DlForRoleItem where droleid in ("+role+") and drtablename = '"+tablename+"' ");
		String sqlwhere="";
		if(dlForRoleItem!=null && !dlForRoleItem.isEmpty() && role.split(",").length==dlForRoleItem.size()){
			for(DlForRoleItem dl :dlForRoleItem){//过滤类型(1所有部门|2本部门|3本部门及下属部门|4用户自己|5指定部门)
				if(sqlwhere.trim().length()>1)
					sqlwhere+=" or ";
				if(2==dl.getDrtype()){//2本部门
					sqlwhere+=" "+dl.getDrdeptfield()+" in ("+user.getUswork()+")";
				}else if(3==dl.getDrtype()){//3本部门及下属部门
					sqlwhere+=" "+dl.getDrdeptfield()+" in ("+userdept+")";
				}else if(4==dl.getDrtype()){//4用户自己
					sqlwhere+=" "+dl.getDruserfield()+" in ("+user.getUsid()+") and "+dl.getDrdeptfield()+" in ("+user.getUswork()+")";//防止用户调整部门之后，看到以前部门的数据
				}else if(5==dl.getDrtype()){//5指定部门
					sqlwhere+=" "+dl.getDrdeptfield()+" in ("+dl.getDrdept()+")";
				}
			}
		}
		if(sqlwhere.trim().length()>3){
			sql = "select * from ( "+sql+"  ) "+tablename+" where  "+sqlwhere;
		}
		return sql;
	}
}
