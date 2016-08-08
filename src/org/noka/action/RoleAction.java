package org.noka.action;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.noka.item.RoleItem;
import org.nokatag.system.DataUtil;
import org.nokatag.system.ServletNokaContext;


public class RoleAction extends BaseAction{

	private static final long serialVersionUID = 3176656591935177710L;
	private RoleItem role = null;
	private String  roeidrows = null;
	//======================================================================
	
	/**
	 * 主操作界面
	 */
	@Action(value="role",results={@Result(name="popedom_role",location="popedom/role.jsp")})
	public String roleForJsp(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String SQL="SELECT ROLEID AS "+$L("org.word.jsp.id")+",ROLENAME AS "+$L("org.role.jsp.rolename")+",ROLETEXT AS "+$L("org.word.jsp.notes")+",ROLECOMPOSITOR AS "+$L("org.word.jsp.pxun")+" FROM NK_SYS_ROLE ";
		request.setAttribute("sql", SQL);
		if(msg!=null)
			request.setAttribute("msg", $L(msg));
		return "popedom_role";
	}
	@Action("roleadd")
	public String roleadd(){
		try{
		if(role!=null){
			DataUtil.insert(role);
			$P("5");
		}else{
			$P("6");
		}
		}catch(Exception se){}
		return null;
	}
	
	@Action("roledel")
	public String roledel(){
		List<RoleItem> roles = new ArrayList<RoleItem>();
		if(roeidrows!=null){
			try{
			String[] roleids = roeidrows.split(",");
			for(int i=0;i<roleids.length;i++){
				RoleItem roleitem = new RoleItem();
				Long srt = Long.parseLong(roleids[i]);
				roleitem.setRoleid(srt);
				roles.add(roleitem);
			}
			if(!roles.isEmpty()){
				DataUtil.deleteList(roles);//删除角色
				//---------删除关联的角色授权------------------------------------
				Connection con = ServletNokaContext.getConnection();
				con.setAutoCommit(false);
				PreparedStatement pa = con.prepareStatement("DELETE FROM NK_SYS_POPEDOMSWARD WHERE PSROLEID = ?");
				for(int i=0;i<roleids.length;i++){
					Long srt = Long.parseLong(roleids[i]);
					pa.setLong(1,srt);
					pa.addBatch();
				}
				pa.executeUpdate();
				con.commit();
				con.setAutoCommit(true);
				//------------------------------------------------------------
				$P("1");
			}else{
				$P("2");
			}
			}catch(Exception se){}
		}
	
		return null;
	}
	@Action("roleupdate")
	public String roleupdate(){
		try{
		if(role!=null && role.getRoleid()!=null){
			DataUtil.update(role);
			$P("3");
		}else{
			$P("4");
		}
		}catch(Exception se){}
		return null;
	}
	
	
	
	
	
	//======================================================================
	public RoleItem getRole() {
		return role;
	}
	public void setRole(RoleItem role) {
		this.role = role;
	}

	public String getRoeidrows() {
		return roeidrows;
	}

	public void setRoeidrows(String roeidrows) {
		this.roeidrows = roeidrows;
	}

}
