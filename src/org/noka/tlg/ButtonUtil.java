package org.noka.tlg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;
import org.apache.struts2.ServletActionContext;
import org.noka.constvar.ConstVar;
import org.noka.item.PopedomswardItem;
import org.noka.item.UserItem;
import org.nokatag.system.DataUtil;

public class ButtonUtil {
	/**
	 * 
	 * @param cd input.jsp[add&&update || dd && cc]or
	 * @param re aaaaa:bbbbb
	 * @param user
	 * @return
	 */
	public static String ButIf(String expression,String re){
		HttpServletRequest request = ServletActionContext.getRequest();
		UserItem user = (UserItem)request.getSession().getAttribute("user");
		String muid = request.getAttribute("muid").toString();
		String jsp = request.getRequestURI();
		int let = jsp.length();
		if(jsp.indexOf("?")!=-1)
			let = jsp.indexOf("?");
		if(jsp.indexOf(":")!=-1)
			let = jsp.indexOf(":");
		jsp=jsp.substring(jsp.lastIndexOf("/")+1,let);
		List<Map<String,String>> ButtonMap = ButtonUtil.buttons(user);//取得该用户所有按钮权限
		//--------------------------------------------------------------------
		Map<String,String> ma=new HashMap<String, String>();
		for(Map<String,String> map:ButtonMap){//取出当前页面所有按钮
			if(jsp.equals(map.get("jsp")) && String.valueOf(muid).equals(map.get("menuid")))
				ma.put(map.get("enname"), map.get("enname"));
		}
		//--------------------组织判断条件------------------------------------
		Boolean ispopedom=false;
		JexlContext jexlContext = new MapContext();
		 String[] a1=expression.split("&&");//add    update || dd    cc
		 for(String a:a1){
			 for(String b:a.split("\\|\\|")){
				 jexlContext.set(b, ma.get(b)!=null);
				 if(("popedom").equals(b))
					 ispopedom = true;
			 }
		 }
		 Expression e = new JexlEngine().createExpression(expression);
         Boolean num = (Boolean) e.evaluate(jexlContext);
         String su="";
         String fu="";
         
         if(re!=null && re.indexOf(":")!=-1){
        	 su=re.split(":")[0];
        	 fu=re.split(":")[1];
         }else if(re!=null && re.indexOf(":")==-1){
        	 su=re;
         }
       //---------------------------------------------------------------
        Boolean hide=false;
 		Boolean nk_sso = (request.getAttribute("noka_sso")==null?false:(Boolean)request.getAttribute("noka_sso"));//true sso load
 		if(nk_sso && ("dept.jsp".equals(jsp) || "user.jsp".equals(jsp)) && !ispopedom){
 			hide = true;
 		}
 		//--------------------------------------------------------------
		 if(!hide && num){
			return su;
		 }else{
			return fu;
		 }
	}
	/**
	 * 返回按钮
	 * @param request
	 * @param jsp
	 * @param menuid
	 * @param buts
	 * @return
	 */
	public static String GetBut(String but,String  value,String onclick){
		StringBuilder stringBuilder = new StringBuilder();
		HttpServletRequest request = ServletActionContext.getRequest();
		UserItem user = (UserItem)request.getSession().getAttribute("user");
		String menuid = request.getAttribute("muid").toString();
		String jsp = request.getRequestURI();
		int let = jsp.length();
		if(jsp.indexOf("?")!=-1)
			let = jsp.indexOf("?");
		if(jsp.indexOf(":")!=-1)
			let = jsp.indexOf(":");
		jsp=jsp.substring(jsp.lastIndexOf("/")+1,let);
		//---------------------------------------------------------------
		Boolean nk_sso = (request.getAttribute("noka_sso")==null?false:(Boolean)request.getAttribute("noka_sso"));//true sso load
		if(nk_sso && ("dept.jsp".equals(jsp) || "user.jsp".equals(jsp)) && !"popedom".equals(but)){
			return "";
		}
		//--------------------------------------------------------------
		List<Map<String,String>> ButtonMap = ButtonUtil.buttons(user);//取得该用户所有按钮权限
		//--------------------------------------------------------------------
		for(Map<String,String> map:ButtonMap){
			if(jsp.equals(map.get("jsp")) && but.equals(map.get("enname")) && String.valueOf(menuid).equals(map.get("menuid"))){
				stringBuilder.append("<span style='float:left;' class=\"nspan\"  id=\""+but+"_nspan\"><input id=\""+but+"\"  type=\"button\"  ");
				stringBuilder.append(" class=\"but\"   onclick= \""+onclick+"\"  value= \""+value+"\" ");
				stringBuilder.append(" /></span>");
				break;
			}
		}
		return stringBuilder.toString();
	}
	
	
	/**
	 * 取得用户下的所有按钮权限
	 * @param user
	 * @return
	 */
	public static List<Map<String,String>> buttons(UserItem user){
		List<Map<String,String>> ButtonMap=ConstVar.BUTTONS.get(user.getUsid());
		if(ButtonMap==null || ButtonMap.isEmpty()){
			ButtonMap = new ArrayList<Map<String,String>>();//每一个list为一条记录
			List<PopedomswardItem>  popedoms = DataUtil.objectList(" from PopedomswardItem where psroleid in("+user.getUsrole()+") ");//取得当前用户的角色
			for(PopedomswardItem popedom:popedoms){
				if(popedom.getPswork()!=null && popedom.getPswork().trim().length()>1){
					String[] buttons = popedom.getPswork().split(",");//取得每个按扭
					for(String button:buttons){
						Map<String,String> map = new HashMap<String,String>();//每一个map为一个按钮
						map.put("jsp",button.split("@")[0]);//jsp页面名字
						map.put("zhname",button.split("@")[1]);//中文件名字
						map.put("enname",button.split("@")[2]);//英文名字
						map.put("menuid",String.valueOf(popedom.getPsmenuid()));//菜菜单id
						ButtonMap.add(map);
					}
				}
			}
			ConstVar.BUTTONS.put(user.getUsid(), ButtonMap);//加入缓存
		}
		return ButtonMap;
	}
}
