package org.noka.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.noka.constvar.ConstVar;
import org.noka.gson.Gson;
import org.noka.item.MenuItem;
import org.noka.item.MenuJSItem;
import org.noka.item.PopedomswardItem;
import org.noka.item.UserItem;
import org.nokatag.system.DataUtil;
import org.nokatag.system.DataWhere;

public class MainAction extends BaseAction{

	private static final long serialVersionUID = -5905048691894195572L;
	private String muid="1";
	
	//-------主界面-------------
	@Action(value="main",results={@Result(name="main",location="main/newmain.jsp")})
	public String main(){
		HttpServletRequest request = ServletActionContext.getRequest();
		UserItem sysuserItem = (UserItem)request.getSession().getAttribute("user");
		List<MenuJSItem> JSMenuList = new ArrayList<MenuJSItem>();//菜单缓存
		List<MenuJSItem> JSMenuTopList = new ArrayList<MenuJSItem>();//一级菜单
		List<MenuJSItem> JSMenuLeftList = new ArrayList<MenuJSItem>();//初始二，三级菜单
		try{
			List<PopedomswardItem> popdeoms = DataUtil.objectList("from PopedomswardItem where psroleid in("+sysuserItem.getUsrole()+") ");
			String menuids="0";
			for(PopedomswardItem pd:popdeoms){
				menuids+=","+String.valueOf(pd.getPsmenuid());
			}
			request.getSession().setAttribute("menuids", menuids);
			//-------------一级菜单------------------------------------------------
			List<MenuItem> menuItemList = DataUtil.objectList("from MenuItem where  menupid=1 and menuid in("+menuids+") order by menutaxis asc"); //一级菜单列表
			for(MenuItem menuitem:menuItemList){
				menuitem.setMenuname($L(menuitem.getMenuname()));
				if(null!=menuitem.getMenuimage()){
					String img =menuitem.getMenuimage().replace("${skin}",ConstVar.XMLCONST.get("skin")).replace("${rooturl}",request.getAttribute("rooturl").toString());
					menuitem.setMenuimage(img);
				}
				JSMenuList.add(new MenuJSItem(String.valueOf(menuitem.getMenuid()), menuitem.getMenuname(), menuitem.getMenuurl(), menuitem.getMenuimage(), menuitem.getMenutarget()));
				JSMenuTopList.add(new MenuJSItem(String.valueOf(menuitem.getMenuid()),menuitem.getMenuname(), menuitem.getMenuurl(), menuitem.getMenuimage(), menuitem.getMenutarget()));
			}
			//----------------二级菜单-------------------------------------------------
			for(MenuJSItem menuitem:JSMenuList){
				List<DataWhere> where = new ArrayList<DataWhere>();
				where.add(new DataWhere("menuid",Long.parseLong(menuitem.getMuid())));
				List<MenuItem> menuItemList1 = DataUtil.objectList("from MenuItem where  menupid=:menuid  and menuid in("+menuids+") order by menutaxis asc",where); //二级菜单列表
				List<MenuJSItem> subJSMenuList = new ArrayList<MenuJSItem>();
				for(MenuItem menuItem:menuItemList1){
					String murl=menuItem.getMenuurl().replace("${rooturl}",request.getAttribute("rooturl").toString());
					if(murl.indexOf("?")!=-1){
						murl=murl+"&muid="+menuItem.getMenuid();
					}else{
						murl=murl+"?muid="+menuItem.getMenuid();
					}
					murl=murl+"&d="+System.currentTimeMillis();
					if($L(menuItem.getMenuname())!=null){
						String img = menuItem.getMenuimage().replace("${skin}",ConstVar.XMLCONST.get("skin")).replace("${rooturl}",request.getAttribute("rooturl").toString());
						subJSMenuList.add(new MenuJSItem(String.valueOf(menuItem.getMenuid()), $L(menuItem.getMenuname()), menuItem.getMenuurl(), img, menuItem.getMenutarget()));
					}
				}
				menuitem.setSub(subJSMenuList);
			}
			//--------------三级菜单---------------------------------------------------
			for(MenuJSItem menuitem:JSMenuList){
				if(null!=menuitem.getSub() && !menuitem.getSub().isEmpty()){
					for(MenuJSItem sub:menuitem.getSub()){//循环二级
						List<DataWhere> where = new ArrayList<DataWhere>();
						where.add(new DataWhere("menuid",Long.parseLong(sub.getMuid())));
						List<MenuItem> menuItemList1 = DataUtil.objectList("from MenuItem where  menupid=:menuid and menuid in("+menuids+")  order by menutaxis asc",where); //二级菜单列表
						List<MenuJSItem> subJSMenuList = new ArrayList<MenuJSItem>();
						for(MenuItem menuItem:menuItemList1){
							String murl=menuItem.getMenuurl().replace("${rooturl}",request.getAttribute("rooturl").toString());
							if(murl.indexOf("?")!=-1){
								murl=murl+"&muid="+menuItem.getMenuid();
							}else{
								murl=murl+"?muid="+menuItem.getMenuid();
							}
							murl=murl+"&d="+System.currentTimeMillis();
							if($L(menuItem.getMenuname())!=null){
								String img = menuItem.getMenuimage().replace("${skin}",ConstVar.XMLCONST.get("skin")).replace("${rooturl}",request.getAttribute("rooturl").toString());
								subJSMenuList.add(new MenuJSItem(String.valueOf(menuItem.getMenuid()), $L(menuItem.getMenuname()), murl, img, menuItem.getMenutarget()));
							}
						}
						sub.setSub(subJSMenuList);
					}
				}
			}
			//--------------初始化左边二、三级菜单---------------------------------------
			if(null!=JSMenuList && !JSMenuList.isEmpty()){
				List<MenuJSItem> mjitem = JSMenuList.get(0).getSub();
				for(MenuJSItem mjs:mjitem){
					JSMenuLeftList.add(mjs);
				}
			}
			Gson gson = new Gson();
			request.setAttribute("jsmenulist", gson.toJson(JSMenuList));
			request.setAttribute("JSMenuTopList", gson.toJson(JSMenuTopList));
			request.setAttribute("JSMenuLeftList", gson.toJson(JSMenuLeftList));
			request.setAttribute("useritem", sysuserItem);
		}catch(Exception se){
			se.printStackTrace();
		}
		return "main";
	}
//======================================================================================
	public String getMuid() {
		return muid;
	}
	public void setMuid(String muid) {
		this.muid = muid;
	}
}
