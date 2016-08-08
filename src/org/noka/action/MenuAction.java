package org.noka.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.noka.function.LanguageRead;
import org.noka.item.MenuItem;
import org.nokatag.system.DataUtil;

/**
 * 菜单管理
 * @author rebin
 *
 */
public class MenuAction extends BaseAction{
	private static final long serialVersionUID = -150426226294072224L;
	private MenuItem menuItem = null;
	private String menuids = null;//菜单ids
	private String id=null;
	private String dlvie=null;//级别号
	//--------主界面--------------
	@Action(value="menuAdmin",results={@Result(name="menu_menu",location="menu/menu.jsp")})
	public String menuAdmin(){
		HttpServletRequest request = ServletActionContext.getRequest();
		try{
			//----------------树形sql-----------------------------------------------------------------------------------------------
			String tSQL="SELECT MENUID AS ID ,MENUPID AS PID,MENUNAME AS NAME,MENUNAME AS TITLE,MENULEVE as LEVE,MENUURL,MENUOPERATE,MENUIMAGE,MENUTAXIS,MENUTARGET,MENUNBURL FROM NK_SYS_MENU";
			request.setAttribute("tsql", tSQL);
			//----------------------------------------------------------------------------------------------------------------------
			String SQL="SELECT MENUID AS ID,MENUPID AS PID,MENULEVE as LEVE,MENUNAME AS "+$L("org.menu.jsp.menu_name")+", MENUURL AS "+$L("org.menu.jsp.menu_url")+",MENUOPERATE AS "+$L("org.menu.jsp.menu_operate")+",MENUIMAGE AS "+$L("org.menu.jsp.menu_image")+",MENUTAXIS as "+$L("org.menu.jsp.menu_taxis")+",MENUTARGET as "+$L("org.menu.jsp.menu_target")+",MENUNBURL as "+$L("org.menu.jsp.menu_nburl")+" FROM NK_SYS_MENU WHERE 1=1 ";		
			if(id!=null && !id.equalsIgnoreCase("0")){
				Long tid=Long.parseLong(id);
				MenuItem tb = (MenuItem)DataUtil.object("from MenuItem where menuid="+tid+" ");
				if(tb!=null){
					SQL+=" AND MENULEVE LIKE  '"+tb.getMenuleve()+"_"+tb.getMenuid()+"%' ";
					request.setAttribute("dept",tb);
					request.setAttribute("isrro","no");
				}
			}else{
				MenuItem tb = new MenuItem();
				tb.setMenuid(-1L);
				tb.setMenupid(-1L);
				tb.setMenuleve("0");
				request.setAttribute("dept",tb);
				request.setAttribute("isrro","yes");
			}
			request.setAttribute("sql", SQL);
		}catch(Exception se){
			se.printStackTrace();
		}
		if(msg!=null)
			request.setAttribute("msg", LanguageRead.getLang(msg));
		return "menu_menu";
	}
	//-----------添加---------------
	@Action("menuAdd")
	public String menuAdd(){
		try{
		if(menuItem!=null){
			menuItem.setMenupid(menuItem.getMenuid());
			menuItem.setMenuleve(menuItem.getMenuleve()+"_"+menuItem.getMenupid());
			DataUtil.insert(menuItem);
			$P("1");
		}else{
			$P("2");
		}
		}catch(Exception se){}
		return null;
	}
	//------------修改------------------
	@Action("updateMenu")
	public String updateMenu(){
		try{
		if(menuItem!=null && menuItem.getMenuid()!=null){
			DataUtil.update(menuItem);
			$P("3");
		}else{
			$P("4");
		}
		}catch(Exception se){}
		return null;
	}
	//-----------删除----------------------
	@Action("menuDel")
	public String menuDel(){
		List<MenuItem> MenuItemList = new ArrayList<MenuItem>();
		if(menuids!=null){
			try{
				String[] wordids = menuids.split(",");
			for(int i=0;i<wordids.length;i++){
				MenuItem menuItems = new MenuItem();
				Long srt = Long.parseLong(wordids[i]);
				menuItems.setMenuid(srt);
				MenuItemList.add(menuItems);
			}
			if(!MenuItemList.isEmpty()){
				DataUtil.deleteList(MenuItemList);
				$P("5");
			}else{
				$P("6");
			}
			}catch(Exception se){
				se.printStackTrace();
			}
		}
		return null;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getDlvie() {
		return dlvie;
	}
	public void setDlvie(String dlvie) {
		this.dlvie = dlvie;
	}
	public MenuItem getMenuItem() {
		return menuItem;
	}
	public void setMenuItem(MenuItem menuItem) {
		this.menuItem = menuItem;
	}
	public String getMenuids() {
		return menuids;
	}
	public void setMenuids(String menuids) {
		this.menuids = menuids;
	}
	

}
