package org.noka.action;


import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.noka.constvar.ConstVar;
import org.noka.item.DeptItem;
import org.noka.item.UserItem;
import org.noka.struts.logPlugin.ALog;
import org.noka.struts.logPlugin.ALog.TYPE;
import org.nokatag.password.DESPlus;
import org.nokatag.system.DataUtil;
import org.nokatag.system.DataWhere;



public class UserAction extends BaseAction{

	private static final long serialVersionUID = -1089974646131974074L;
	private UserItem user = null;
	private String userrows = null;//
	private String roleids = null;//

	/**
	 * 系统登陆 
	 * @return
	 */
	@ALog(msg="用户登陆",type=TYPE.ERROR,request={"user.usname","user.uspassword"})
	@Action(value="login",results={@Result(name="login",location="main/login.jsp"),@Result(name="main_action",type="redirect",location="main.nk")})
	public String login(){
		String pass = "";
		HttpServletRequest request = ServletActionContext.getRequest();
		//--------------------------------------------------------------
		request.getSession().removeAttribute("user");
		request.getSession().removeAttribute("startTime");
		request.getSession().removeAttribute("userdept");
		//--------------------------------------------------------------
		if(user!=null){
			//获得当前系统时间
			Calendar c = Calendar.getInstance();
			Date date = c.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm");
			String startTime = sdf.format(date);//当前系统时间
			
			
			try {
				DESPlus des = new DESPlus(ConstVar.PASSKEY);
				pass = des.encrypt(user.getUspassword());//加密
			} catch (Exception e) {
				e.printStackTrace();
			}
			//----------------设置用户名和密码---------------------
			List<DataWhere> dwlist = new ArrayList<DataWhere>();
			dwlist.add(new DataWhere("username",user.getUsname().trim()));//用户名
			dwlist.add(new DataWhere("password",pass));//密码
			//--------------------------------------------------------
			UserItem userprave=(UserItem)DataUtil.object("from UserItem where  uspass=1 and usname=:username and uspassword=:password ",dwlist);
			if(userprave!=null){
				request.getSession().setAttribute("user", userprave);//加入session
				request.getSession().setAttribute("startTime", startTime);//加入session
				request.getSession().setAttribute("userdept", getDepts(userprave.getUswork()));//加入session
				return "main_action";
			}else{
				//-------------------------用户名---------------------------------------------------------------
				List<DataWhere> userdwlist = new ArrayList<DataWhere>();
				userdwlist.add(new DataWhere("username",user.getUsname()));//用户名
				UserItem username = (UserItem)DataUtil.object("from UserItem where usname=:username ",userdwlist);
				//-------------------------是否禁用-------------------------------------------------------------
				List<DataWhere> uspasswlist = new ArrayList<DataWhere>();
				uspasswlist.add(new DataWhere("username",user.getUsname()));//用户名
				UserItem userpass = (UserItem)DataUtil.object("from UserItem where usname=:username and  uspass=1 ",uspasswlist);
				
				if(username==null){//没有该用户
					request.setAttribute("erro",$L("org.login.jsp.OuputNoUser"));
					return "login";
				}else if(userpass==null){//该用户被禁用
					request.setAttribute("erro",$L("org.login.jsp.UpassErro"));
					return "login";
				}else{
					request.setAttribute("erro",$L("org.login.jsp.PassErro"));
					return "login";
				}
			}
		}else{
			return "login";
		}
	}
	/**
	 * 修改密码界面
	 * @return
	 */
	@Action(value="updateforjsp",results={@Result(name="user_myinfo",location="user/myinfo.jsp")})
	public String updateForJsp(){
		HttpServletRequest request = ServletActionContext.getRequest();
		if(request.getSession().getAttribute("user")!=null){
			UserItem userpriave = (UserItem)request.getSession().getAttribute("user");
			UserItem useritem = (UserItem)DataUtil.object("from UserItem where usid='"+userpriave.getUsid()+"' ");
			request.setAttribute("useritem", useritem);
		}
		if(msg!=null)
			request.setAttribute("msg", $L(msg));
		return "user_myinfo";
	}
	/**
	 * 修改密码
	 * @return
	 */
	@Action("myuserupdate")
	public String update(){
		try{
			if(user!=null){
				List<DataWhere> userdwlist = new ArrayList<DataWhere>();
				userdwlist.add(new DataWhere("usid",user.getUsid()));//用户名
				UserItem useritem = (UserItem)DataUtil.object("from UserItem where usid=:usid ",userdwlist);
				useritem.setUsborth(user.getUsborth());
				useritem.setUsemail(user.getUsemail());
				useritem.setUsmoble(user.getUsmoble());
				useritem.setUssex(user.getUssex());
				useritem.setUstext(user.getUstext());
				useritem.setUstel(user.getUstel());
				useritem.setUsxname(user.getUsxname());
				DataUtil.update(useritem);
				$P("1");
			}else{
				$P("2");
			}
		}catch(Exception se){}
		return null;
	}
	//-----------------------------------------------------------------------
	/**
	 * 用户管理主界面
	 * @return
	 */
	@Action(value="userman",results={@Result(name="popedom_user",location="popedom/user.jsp")})
	public String userman(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String SQL="SELECT USID AS "+$L("org.word.jsp.id")+",USNAME AS "+$L("org.user.jsp.usern")+", USXNAME AS "+$L("org.myuser.jsp.name")+",USSEX AS "+$L("org.myuser.jsp.sex")+",USMOBLE AS "+$L("org.myuser.jsp.moble")+",USTEL AS "+$L("org.myuser.jsp.tel")+",USEMAIL AS "+$L("org.myuser.jsp.email")+", DNAME AS "+$L("org.user.jsp.deptur")+", " +
		"USWORK,USBORTH AS "+$L("org.myuser.jsp.bro")+",USTEXT,USPASS,USPASSWORD,DID,USSEX,USFUGLE,USFUGLE as USFUGLEN FROM NK_SYS_USERINFO ,NK_SYS_DEPT WHERE USWORK=DID ";
		
		request.setAttribute("sql", sqlFilter(SQL, "NK_SYS_USERINFO"));
		
		request.setAttribute("deptsql","SELECT DID AS ID,DPID AS PID,DNAME AS NAME,DNAME AS TITLE,DLVIE as LEVE FROM NK_SYS_DEPT");
		if(msg!=null)
			request.setAttribute("msg", $L(msg));
		return "popedom_user";
	}
	/**
	 * 添加新用户
	 */
	@Action("useradd")
	public String useradd(){
		try {
			String pass = "";
			DESPlus des;
			if(user!=null){
				des = new DESPlus(ConstVar.PASSKEY);
				pass = des.encrypt(user.getUspassword());//加密
				if(!chUser(user.getUsname())){
					user.setUsdate(new Date());
					user.setUsname(user.getUsname().replaceAll(" ", "").trim());
					user.setUspassword(pass);
					DataUtil.insert(user);
					$P("1");
				}else{
					$P("2");
				}
			}else{
				$P("2");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 修改用户
	 * @return
	 */
	@Action("userupdate")
	public String userupdate(){
		
		if(user.getUspassword()!=null){
			String pass = "";
			HttpServletRequest request = ServletActionContext.getRequest();
			DESPlus des;
			try {
				HttpServletResponse response = ServletActionContext.getResponse();
				PrintStream out = new PrintStream(response.getOutputStream());//获取out输出对象
				String idname=request.getParameter("idname");
				if(!idname.equals(user.getUspassword()))//密码与原始密码不一致，需要修改
				{
					des = new DESPlus(ConstVar.PASSKEY);
					pass = des.encrypt(user.getUspassword());//加密
					if(user!=null&&user.getUsid()!=null){
						List<DataWhere> userdwlist = new ArrayList<DataWhere>();
						userdwlist.add(new DataWhere("usid",user.getUsid()));//用户ID
						userdwlist.add(new DataWhere("usname",user.getUsname()));//用户名
						UserItem useritem = (UserItem)DataUtil.object("from UserItem where usid=:usid and usname=:usname",userdwlist);
						if(useritem==null){
							out.print("4");
							return null;
						}
						useritem.setUsxname(user.getUsxname());
						useritem.setUspassword(pass);
						useritem.setUssex(user.getUssex());
						useritem.setUsborth(user.getUsborth());
						useritem.setUsmoble(user.getUsmoble());
						useritem.setUstel(user.getUstel());
						useritem.setUsemail(user.getUsemail());
						useritem.setUspass(user.getUspass());
						useritem.setUstext(user.getUstext());
						useritem.setUswork(user.getUswork());
						useritem.setUsfugle(user.getUsfugle());
						DataUtil.update(useritem);
						$P("3");
					}else{
						$P("4");
					}
				}
				else
				{
					if(user!=null&&user.getUsid()!=null){
						List<DataWhere> userdwlist = new ArrayList<DataWhere>();
						userdwlist.add(new DataWhere("usid",user.getUsid()));//用户名
						userdwlist.add(new DataWhere("usname",user.getUsname()));//用户名
						UserItem useritem = (UserItem)DataUtil.object("from UserItem where usid=:usid and usname=:usname",userdwlist);
						if(useritem==null){
							out.print("4");
							return null;
						}
						useritem.setUsxname(user.getUsxname());
						useritem.setUssex(user.getUssex());
						useritem.setUsborth(user.getUsborth());
						useritem.setUsmoble(user.getUsmoble());
						useritem.setUstel(user.getUstel());
						useritem.setUsemail(user.getUsemail());
						useritem.setUspass(user.getUspass());
						useritem.setUstext(user.getUstext());
						useritem.setUswork(user.getUswork());
						useritem.setUsfugle(user.getUsfugle());
						DataUtil.update(useritem);
						$P("3");
					}else{
						$P("4");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			try{
			HttpServletResponse response = ServletActionContext.getResponse();
			PrintStream out = new PrintStream(response.getOutputStream());//获取out输出对象
			if(user!=null&&user.getUsid()!=null){
				List<DataWhere> userdwlist = new ArrayList<DataWhere>();
				userdwlist.add(new DataWhere("usid",user.getUsid()));//用户名
				userdwlist.add(new DataWhere("usname",user.getUsname()));//用户名
				UserItem useritem = (UserItem)DataUtil.object("from UserItem where usid=:usid and usname=:usname",userdwlist);
				if(useritem==null){
					out.print("4");
					return null;
				}
				useritem.setUsxname(user.getUsxname());
				useritem.setUssex(user.getUssex());
				useritem.setUsborth(user.getUsborth());
				useritem.setUsmoble(user.getUsmoble());
				useritem.setUstel(user.getUstel());
				useritem.setUsemail(user.getUsemail());
				useritem.setUspass(user.getUspass());
				useritem.setUstext(user.getUstext());
				useritem.setUswork(user.getUswork());
				useritem.setUsfugle(user.getUsfugle());
				DataUtil.update(useritem);
				$P("3");
			}else{
				$P("4");
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	/**
	 * 删除用户
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Action("userdel")
	public String userdel(){
		List users = new ArrayList();
		if(userrows!=null){
			try{
				String[] userids = userrows.split(",");
			for(int i=0;i<userids.length;i++){
				UserItem userItem = new UserItem();
				Long srt = Long.parseLong(userids[i]);
				userItem.setUsid(srt);
				users.add(userItem);
			}
			if(!users.isEmpty()){
				DataUtil.deleteList(users);
				$P("5");
			}else{
				$P("6");
			}
			}catch(Exception se){
				
			}
		}
		return null;
	}
	/**
	 * 启用用户
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Action("userstart")
	public String userstart(){
		List users = new ArrayList();
		if(userrows!=null){
			try{
				String[] userids = userrows.split(",");
			for(int i=0;i<userids.length;i++){
				List<DataWhere> userdwlist = new ArrayList<DataWhere>();
				userdwlist.add(new DataWhere("usid",userids[i]));//用户名
				
				UserItem userItem = (UserItem)DataUtil.object("from UserItem where usid=:usid ",userdwlist);
				userItem.setUspass(1);
				users.add(userItem);
			}
			if(!users.isEmpty()){
				DataUtil.updateList(users);
				$P("7");
			}else{
				$P("8");
			}
			}catch(Exception se){
				
			}
		}
		return null;
	}
	/**
	 * 禁用用户
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Action("userstop")
	public String userstop(){
		List users = new ArrayList();
		if(userrows!=null){
			try{
			String[] userids = userrows.split(",");
			for(int i=0;i<userids.length;i++){
				List<DataWhere> userdwlist = new ArrayList<DataWhere>();
				userdwlist.add(new DataWhere("usid",userids[i]));//用户名
				UserItem userItem = (UserItem)DataUtil.object("from UserItem where usid=:usid ",userdwlist);
				userItem.setUspass(0);
				users.add(userItem);
			}
			if(!users.isEmpty()){
				DataUtil.updateList(users);
				$P("9");
			}else{
				$P("10");
			}
			}catch(Exception se){
				
			}
		}
		return null;
	}
	/**
	 * 角色分配界面
	 * @return
	 */
	@Action(value="userrole",results={@Result(name="popedom_userforrole",location="popedom/userforrole.jsp")})
	public String userrole(){
		HttpServletRequest request = ServletActionContext.getRequest();
		//Connection con = HibernateUtil.currentSession().connection();
		if(user!=null){
			List<DataWhere> userdwlist = new ArrayList<DataWhere>();
			userdwlist.add(new DataWhere("usid",user.getUsid()));//用户名
			
			UserItem userItem = (UserItem)DataUtil.object("from UserItem where usid=:usid ",userdwlist);
			request.setAttribute("user", userItem);
			String rSQL="SELECT ROLEID AS VALUE,ROLENAME AS TEXT FROM NK_SYS_ROLE WHERE ROLEID IN ("+userItem.getUsrole()+")";
			request.setAttribute("rsql", rSQL);
		}
		String SQL="SELECT ROLEID AS VALUE,ROLENAME AS TEXT FROM NK_SYS_ROLE";
		request.setAttribute("sql", SQL);
		return "popedom_userforrole";
	}
	//===========================

	/**
	 * 修改用户个人密码
	 * @return
	 */
	@Action(value="mypassword",results={@Result(name="user_mypassword",location="user/mypassword.jsp")})
	public String updateMypasswordJsp(){
		HttpServletRequest request = ServletActionContext.getRequest();
		UserItem u=(UserItem)request.getSession().getAttribute("user");//加入session
		request.setAttribute("user",u);
		return "user_mypassword";
	}
	@Action("mypasswordupdate")
	public String updateMypassword(){
		String pass = "",pass2 = "";
		DESPlus des,des2;
		HttpServletRequest request = ServletActionContext.getRequest();
		try{
			String username = user.getUsname();//request.getParameter("username");//用户名
			String pw = request.getParameter("oldpassword");//原密码
			try {
				 des = new DESPlus(ConstVar.PASSKEY);
				 pass = des.encrypt(pw);//加密
			}catch (Exception e) {
				 e.printStackTrace();
			}
			List<DataWhere> userdwlist = new ArrayList<DataWhere>();
			userdwlist.add(new DataWhere("username",username));//用户名
			userdwlist.add(new DataWhere("uspassword",pass));//密码
			UserItem useritem = (UserItem)DataUtil.object("from UserItem where usname=:username and uspassword=:uspassword",userdwlist);
			if(useritem!=null){
			try {
				des2 = new DESPlus(ConstVar.PASSKEY);
				pass2 = des2.encrypt(user.getUspassword());//加密新密码
			} catch (Exception e) {
				e.printStackTrace();
			}
			useritem.setUspassword(pass2);//修改新密码
			DataUtil.update(useritem);
			$P("1");
			}else{
				$P("2");
			}
		}catch(Exception se){}
		return null;
	}
	/**
	 * bottom 底部界面
	 * @return
	 */
	@Action(value="bottom",results={@Result(name="bottom",location="main/bottom.jsp")})
	public String bottomJsp(){
		HttpServletRequest request = ServletActionContext.getRequest();
		if(request.getSession().getAttribute("user")!=null && request.getSession().getAttribute("startTime")!=null){
			String startTime = (String) request.getSession().getAttribute("startTime");
			UserItem userpriave = (UserItem)request.getSession().getAttribute("user");
			UserItem useritem = (UserItem)DataUtil.object("from UserItem where usid='"+userpriave.getUsid()+"' ");
			DeptItem treeBook = (DeptItem)DataUtil.object("from DeptItem where did='"+useritem.getUswork()+"' ");
			
			
			//获得当前系统时间
			Calendar c = Calendar.getInstance();
			Date date = c.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String startTimes = sdf.format(date);//当前系统时间
			
			request.setAttribute("useritem", useritem);
			request.setAttribute("startTime", startTime);
			request.setAttribute("treeBook", treeBook);
			request.setAttribute("startTimes", startTimes);
		}
		return "bottom";
	}
	/**
	 * 检测用户名是否已经存在
	 * @param usename
	 * @return
	 */
	private boolean chUser(String usename){
		try{
			//-------------------------用户名---------------------------------------------------------------
			List<DataWhere> userdwlist = new ArrayList<DataWhere>();
			userdwlist.add(new DataWhere("username",usename));//用户名
			UserItem username = (UserItem)DataUtil.object("from UserItem where usname=:username ",userdwlist);
			if(username!=null)
				return true;
		}catch(Exception se){}
		return false;
	}
	/**
	 * 获取指定部门下属所有部门（包含本部门）
	 * @param deptid
	 * @return 部门ID,部门ID
	 */
	private String getDepts(Long deptid){
		List<DataWhere> userdwlist = new ArrayList<DataWhere>();
		userdwlist.add(new DataWhere("did",deptid));//用户名
		DeptItem tb = (DeptItem)DataUtil.object("from DeptItem where did=:did ",userdwlist);
		String depts = "0";
		if(tb!=null){
			depts=""+tb.getDid();
			List<DeptItem> treeList = DataUtil.objectList("from DeptItem where dlvie like '"+tb.getDlvie()+"_"+tb.getDid()+"%'");
			if(treeList!=null){
				for(DeptItem tr:treeList){
					depts+=","+tr.getDid();
				}
			}
		}
		return depts;
	}
	//==============================
	public UserItem getUser() {
		return user;
	}
	public void setUser(UserItem user) {
		this.user = user;
	}

	
	public String getRoleids() {
		return roleids;
	}

	public void setRoleids(String roleids) {
		this.roleids = roleids;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getUserrows() {
		return userrows;
	}
	public void setUserrows(String userrows) {
		this.userrows = userrows;
	}
	
	
}
