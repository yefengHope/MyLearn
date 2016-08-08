package org.noka.action;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.noka.constvar.ConstVar;
import org.noka.function.Function;
import org.noka.item.ButtonItem;
import org.noka.item.DlForRoleItem;
import org.noka.item.MenuItem;
import org.noka.item.PopedomswardItem;
import org.noka.item.RoleItem;
import org.nokatag.system.DataUtil;
import org.nokatag.system.DataWhere;


public class PopedomAction extends  BaseAction{
	private static final long serialVersionUID = -3144579447928862423L;
	private List<String> menuids = null;//菜单id列表
	private String roleid = null;//角色Id
	private List<String> buttons = null;//按钮权限
	private String menuid=null;//菜单id
	private List<String> dlroleids = null;//数据过滤id
	/**
	 * 权限管理主界面

	 * @return
	 */
	@Action(value="popedom",results={@Result(name="popedom_popedom",location="popedom/popedom.jsp")})
	public String popedom(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String beginid="0";//开始id
		String beginpid="-1";//开始父id
		if(roleid!=null){
			//--------初始化角色信息-----------------------------------------------------------------
			List<DataWhere> dwlist = new ArrayList<DataWhere>();
			dwlist.add(new DataWhere("roleid",Long.parseLong(roleid)));//角色id
			RoleItem roleItem = (RoleItem)DataUtil.object("from RoleItem where roleid=:roleid",dwlist);
			if(roleItem!=null){
				request.setAttribute("role", roleItem);
			}
			//---------初始化权限信息-------------------------------------------------------------------
			List<PopedomswardItem> popedosmswardsold = DataUtil.objectList("from PopedomswardItem where psroleid=:roleid",dwlist);
			if(popedosmswardsold!=null){
				String value = "";
				for(PopedomswardItem popedom:popedosmswardsold){
					value+=","+popedom.getPsmenuid().toString();
				}
				if(value.startsWith(","))
					value=value.substring(1);
				request.setAttribute("value", value);
			}
		}
		
		String SQL="SELECT MENUID AS ID,MENUPID AS PID,MENUNAME AS  NAME, MENUNAME AS TITLE,MENULEVE AS LEVE FROM NK_SYS_MENU";
		request.setAttribute("sql", SQL);
		request.setAttribute("beginid", beginid);
		request.setAttribute("beginpid", beginpid);
		if(msg!=null)
			request.setAttribute("msg", $L(msg));
		return "popedom_popedom";
	}
	
	/**
	 * 按钮权限界面
	 * @return
	 */
	@Action(value="popedomButton",results={@Result(name="popedom_popedombutton",location="popedom/popedombutton.jsp")})
	public String popedomButton(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String buttonstr="";
		if(menuid!=null){
			List<DataWhere> dwlist = new ArrayList<DataWhere>();
			dwlist.add(new DataWhere("menuid",Long.parseLong(menuid)));//角色id
			MenuItem menuItem = (MenuItem)DataUtil.object("from MenuItem where menuid=:menuid",dwlist);
			if(menuItem.getMenuoperate()!=null && menuItem.getMenuoperate().trim().length()>1){
				request.setAttribute("menuItem", menuItem);
				request.setAttribute("buttons", Function.StringToButton(menuItem.getMenuoperate(), ",","@"));//操作按钮
				List<ButtonItem> b = Function.StringToButton(menuItem.getMenuoperate(), ",","@");
				for(ButtonItem bs:b){
					System.out.println(bs.getText()+" :  "+$L(bs.getText()));
					buttonstr+="<input type=\"checkbox\" name=\"buttons\" value=\""+bs.getBstr()+"\" "+ischeck(menuid,roleid,bs.getBstr())+" />"+$L(bs.getText())+"<br>";
				}
			}
			request.setAttribute("buttonlist", buttonstr);
			request.setAttribute("roleid", roleid);
			if(msg!=null)
				request.setAttribute("msg", $L(msg));
		}
		
		return "popedom_popedombutton";
	}
	/**
	 * 保存授权
	 * @return
	 */
	@Action("popedomsave")
	public String popedomsave(){
		List<PopedomswardItem> popedosmswards = new ArrayList<PopedomswardItem>();
		try{
			if(menuids!=null && !menuids.isEmpty() && roleid!=null){
				for(String menuid:menuids){
					PopedomswardItem popedomsward = new PopedomswardItem();
					popedomsward.setPsroleid(Long.parseLong(roleid));//设置角色id
					popedomsward.setPsmenuid(Long.parseLong(menuid));//菜单id
					List<DataWhere> dwlist = new ArrayList<DataWhere>();
					dwlist.add(new DataWhere("menuid",Long.parseLong(menuid)));//角色id
					MenuItem menu = (MenuItem)DataUtil.object("from MenuItem where menuid=:menuid",dwlist);
					if(menu.getMenuoperate()!=null){
						popedomsward.setPswork(menu.getMenuoperate());
					}
					popedosmswards.add(popedomsward);
				}
				//-------删除以前的对象---------------
				List<DataWhere> dwlist = new ArrayList<DataWhere>();
				dwlist.add(new DataWhere("roleid",Long.parseLong(roleid)));//角色id
				List<PopedomswardItem> popedosmswardsold = DataUtil.objectList("from PopedomswardItem where psroleid=:roleid",dwlist);
				DataUtil.deleteList(popedosmswardsold);
				//------插入新的授权记录--------------
				DataUtil.insertList(popedosmswards);
				ConstVar.URLS.clear();//清空权限缓存
				ConstVar.BUTTONS.clear();//清空按钮权限缓存
				$P("1");
			}else if( (menuids==null || menuids.isEmpty()) && roleid!=null){
				//-------删除以前的对象---------------
				List<DataWhere> dwlist = new ArrayList<DataWhere>();
				dwlist.add(new DataWhere("roleid",Long.parseLong(roleid)));//角色id
				List<PopedomswardItem> popedosmswardsold = DataUtil.objectList("from PopedomswardItem where psroleid=:roleid",dwlist);
				DataUtil.deleteList(popedosmswardsold);
				ConstVar.URLS.clear();//清空权限缓存
				ConstVar.BUTTONS.clear();//清空按钮权限缓存
				$P("1");
			}else{
				$P("2");
			}
		}catch(Exception se){
			
		}
		return null;
	}
	/**
	 * 按钮权限
	 * @return
	 */
	@Action(value="buttondom",results={@Result(name="popedomButton",type="redirect",location="popedomButton.nk",params={"muid","%{muid}","menuid","%{menuid}","roleid","%{roleid}","msg","${msg}"})})
	public String buttondom(){
		String butstr="";
		if(menuid!=null){
			List<DataWhere> dwlist = new ArrayList<DataWhere>();
			dwlist.add(new DataWhere("menuid",Long.parseLong(menuid)));//菜单id
			dwlist.add(new DataWhere("roleid",Long.parseLong(roleid)));//角色id
			//menuid
			PopedomswardItem pw = (PopedomswardItem)DataUtil.object("from PopedomswardItem where psmenuid=:menuid and psroleid=:roleid",dwlist);
			if(pw!=null){
				if(buttons!=null){
					for(String buttonids:buttons){
						butstr+=","+buttonids;
					}
				}else{
					butstr+=", ";
				}
				pw.setPswork(butstr.substring(1));
				DataUtil.update(pw);
				$P("1");
			}else{
				$P("2");
			}
		}else{
			$P("2");
		}
		ConstVar.URLS.clear();//清空权限缓存
		ConstVar.BUTTONS.clear();//清空按钮权限缓存
		return null;
	}
	//--------数据过滤主界面---------------------------------------
	@Action(value="dlfitarte",results={@Result(name="popedom_popedomdl",location="popedom/popedomdl.jsp")})
	public String dlfitarte(){
		HttpServletRequest request = ServletActionContext.getRequest();
		if(roleid!=null){
			//-------------------获取数据过率配置表------------------------------------
			List<Map<String,String>> dbtableList = new ArrayList<Map<String,String>>();
			for(Map<String,String> ma:ConstVar.DBTABLE){
				Map<String,String> mb = new HashMap<String,String>();
				mb.put("key", ma.get("key"));
				mb.put("userfield", ma.get("userfield"));
				mb.put("deptfield", ma.get("deptfield"));
				mb.put("lang", $L(ma.get("lang")));
				mb.put("radiovalue","1");//默认为1
				mb.put("treeshow","none");//默认为不显示
				mb.put("select",ma.get("select")
						.replace("$[org.noka.sys.alldept]", $L("org.noka.sys.alldept"))
						.replace("$[org.noka.sys.thedept]", $L("org.noka.sys.thedept"))
						.replace("$[org.noka.sys.thesubdept]", $L("org.noka.sys.thesubdept"))
						.replace("$[org.noka.sys.usermy]", $L("org.noka.sys.usermy"))
						.replace("$[org.noka.sys.appdept]", $L("org.noka.sys.appdept")));//选择控制
				dbtableList.add(mb);
			}
			request.setAttribute("dbtableList",dbtableList);
			//-------------------设置角色信息-------------------------------------------
			List<DataWhere> dwlist = new ArrayList<DataWhere>();
			dwlist.add(new DataWhere("roleid",Long.parseLong(roleid)));//角色id
			RoleItem roleItem = (RoleItem)DataUtil.object("from RoleItem where roleid=:roleid",dwlist);
			request.setAttribute("roleItem", roleItem);
			//-------------------显示已设置的信息---------------------------------------
			List<DlForRoleItem> DlForRoleItemsOld = DataUtil.objectList("from DlForRoleItem where droleid=:roleid",dwlist);
			if(DlForRoleItemsOld!=null && !DlForRoleItemsOld.isEmpty()){
				for(Map<String,String> mb:dbtableList){
					for(DlForRoleItem df:DlForRoleItemsOld){
						if(mb.get("key").equalsIgnoreCase(df.getDrtablename())){
							mb.put("radiovalue", String.valueOf(df.getDrtype()));
							mb.put("treevalue", df.getDrdept());
							mb.put("kvalue", df.getDrtablename()+"@"+df.getDruserfield()+"@"+df.getDrdeptfield()+"@"+df.getDrtype()+"@"+(df.getDrdept()==null?"":df.getDrdept()));//表名@用户字段@部门字段@类型@指定部门
							if(df.getDrtype()==5)
								mb.put("treeshow","");//默认为不显示
						}
					}
				}
			}
		}
		//---------------------------------------部门---------------------------------------------------------
		String SQL="SELECT DID AS ID,DPID AS PID,DNAME AS  NAME, DNAME AS TITLE,DLVIE AS LEVE FROM NK_SYS_DEPT";
		request.setAttribute("sql", SQL);
		if(msg!=null)
			request.setAttribute("msg", $L(msg));
		return "popedom_popedomdl";
	}
	/**
	 * 数据过滤操作
	 * @return
	 */
	@Action("dfitartedo")
	public String dfitartedo(){
		try{
			if(dlroleids!=null && roleid!=null){
				//------------删除该角色的数据过滤配置信息--------------------------------------------------------------
				List<DataWhere> dwlist = new ArrayList<DataWhere>();
				dwlist.add(new DataWhere("roleid",Long.parseLong(roleid)));//角色id
				List<DlForRoleItem> DlForRoleItemsOld = DataUtil.objectList("from DlForRoleItem where droleid=:roleid",dwlist);
				DataUtil.deleteList(DlForRoleItemsOld);
				//------------加入该角色新的数据过滤配置信息-------------------------------------------------------------
				List<DlForRoleItem> DlForRoleItems = new ArrayList<DlForRoleItem>();
				for(String dlrid:dlroleids){//表名@用户字段@部门字段@类型@指定部门
					DlForRoleItem dlForRoleItem = new DlForRoleItem();
					try{
						if(dlrid.indexOf("@")!=-1){
							String[] pars = dlrid.split("@");
							Integer ftype = Integer.parseInt(pars[3]);
							if(ftype!=1){//所有部门不插入数据，该状态为默认状态
								dlForRoleItem.setDroleid(Long.parseLong(roleid));//角色ID
								dlForRoleItem.setDrtablename(pars[0]);//表名
								dlForRoleItem.setDruserfield(pars[1]);//用户字段
								dlForRoleItem.setDrdeptfield(pars[2]);//部门字段
								dlForRoleItem.setDrtype(ftype);//过滤类型
								if(pars.length>=5)
									dlForRoleItem.setDrdept(pars[4]);//指定部门
								DlForRoleItems.add(dlForRoleItem);
							}
						}
					}catch(Exception se){
						se.printStackTrace();
					}
				}
				DataUtil.insertList(DlForRoleItems);
				$P("1");
			}else{
				$P("2");
			}
		}catch(Exception se){}
		return null;
	}
	//-----------------------------------------------------------
	public List<String> getMenuids() {
		return menuids;
	}

	public void setMenuids(List<String> menuids) {
		this.menuids = menuids;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public List<String> getButtons() {
		return buttons;
	}

	public void setButtons(List<String> buttons) {
		this.buttons = buttons;
	}

	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}
	//-------------------------------------------------------------
	private String ischeck(String id,String roleid,String value){
		if(id!=null && roleid!=null){
			PopedomswardItem popedomswardItem = (PopedomswardItem)DataUtil.object("from PopedomswardItem where psroleid='"+roleid+"' and psmenuid='"+id+"' ");
			if(popedomswardItem!=null && popedomswardItem.getPswork()!=null && popedomswardItem.getPswork().trim().length()>3){
				 List<ButtonItem> list = Function.StringToButton(popedomswardItem.getPswork(), ",","@");//操作按钮
				 for(ButtonItem b:list){
					 if(b.getBstr().equalsIgnoreCase(value)){
						 return "checked";
					 }
				 }
			}else{
				return "";
			}
		}else{
			return "";
		}
		return "";
	}

	public List<String> getDlroleids() {
		return dlroleids;
	}

	public void setDlroleids(List<String> dlroleids) {
		this.dlroleids = dlroleids;
	}
}
