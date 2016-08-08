package org.noka.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

/**
 * 人员选择
 * @author rebin
 *
 */
public class PeopSelectAction extends BaseAction{

	private static final long serialVersionUID = -8600682478161358326L;
	/**
	 * select user
	 * @return
	 */
	@Action(value="userselect",results={@Result(name="peopselect_userSelect",location="peopselect/userSelect.jsp")})
	public String UserSelect(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String sid = request.getParameter("sid");//role id
		String s = request.getParameter("s");//显示控件id
		String v = request.getParameter("v");//值空间id
		
		String role_sql="SELECT ROLEID AS ID ,-1 AS PID,ROLENAME AS NAME,ROLETEXT AS TITLE,ROLEID as LEVE FROM NK_SYS_ROLE ";
		String dept_sql="SELECT DID AS ID ,DPID AS PID,DNAME AS NAME,DTEXT AS TITLE,DLVIE as LEVE FROM NK_SYS_DEPT ";
		String user_sql="SELECT USID AS "+$L("org.word.jsp.id")+",USNAME AS "+$L("org.user.jsp.usern")+", USXNAME AS "+$L("org.myuser.jsp.name")+",USSEX AS "+$L("org.myuser.jsp.sex")+",USMOBLE AS "+$L("org.myuser.jsp.moble")+",USTEL AS "+$L("org.myuser.jsp.tel")+",USEMAIL AS "+$L("org.myuser.jsp.email")+", DNAME AS "+$L("org.user.jsp.deptur")+", " +"USWORK,USBORTH AS "+$L("org.myuser.jsp.bro")+",USTEXT,USPASS,USPASSWORD,DID,DLVIE as LEVE,USID,USROLE FROM NK_SYS_USERINFO ,NK_SYS_DEPT WHERE USWORK=DID ";
			
		request.setAttribute("role_sql",  role_sql);
		request.setAttribute("dept_sql",dept_sql);
		request.setAttribute("user_sql",sqlFilter(user_sql, "NK_SYS_USERINFO"));
		
		if(sid==null || sid.equals("1")){//多选
			request.setAttribute("isrdion", "no");
			request.setAttribute("checkbox", "checkbox");
		}else{
			request.setAttribute("isrdion", "yes");
			request.setAttribute("checkbox", "radio");
		}
		request.setAttribute("s", (s==null?"nkuser":s));
		request.setAttribute("v", (v==null?"nkuser":v));
		return "peopselect_userSelect";
	}
}
