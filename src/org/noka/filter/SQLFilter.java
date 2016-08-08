package org.noka.filter;

import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.noka.constvar.ConstVar;
import org.noka.function.Function;
import org.noka.item.MenuItem;
import org.noka.item.PopedomswardItem;
import org.noka.item.UserItem;
import org.nokatag.system.DataUtil;
import org.nokatag.system.SQLCheck;

/**
 * SQL全局过滤
 * @author xiefangjian
 */
public class SQLFilter {
/**
* SQL验证
* @param request
* @return 成功返加true
*/
@SuppressWarnings("rawtypes")
public static Boolean isCheckSuc(HttpServletRequest request){
	Enumeration   e=request.getParameterNames(); 
     while(e.hasMoreElements()){ 
           String parname=(String)e.nextElement(); 
           String parvalue =request.getParameter(parname); 
           if(parvalue!=null && parvalue.trim().length()>0){
	           if(!SQLCheck.sql_inj(parvalue)){
	        	   return false;//验证非法参数
	           }
           }
     }
	return true;
}
/**
 * Session验证
 * @param request
 * @return 验证成功还回true
 */
public static Boolean isSessionSuc(HttpServletRequest request){
	String resourcePath = Function.getFileName(request);
    if(resourcePath==null){
    	return true;
    }
    for(String s:ConstVar.FILE_EXIT){
       String exp=resourcePath.toLowerCase();
	   if(exp.endsWith(s)){//不需要验证session的文件
		   return true;
	   }
    }
    if(ConstVar.NOSESSION.indexOf(resourcePath.substring(1))==-1){//不验证session
    	UserItem userItem=null;
    	try{userItem =(UserItem)request.getSession().getAttribute("user");}catch(Exception se){se.printStackTrace();}
    	if(userItem==null || userItem.getUsname()==null){
    		return false;//验证失败
    	}else if(UrlChcek(resourcePath,userItem)){//session验证成功，并且URS验证成功
    		request.setAttribute("sessionuser", userItem);
    		return true;//验证成功
    	}else{
    		return false;//URL验证失败
    	}
    }
    return true;//不验证session
}
/**
 * URL权限验证
 * @param filepath
 * @param request
 * @return
 */

public static Boolean UrlChcek(String filepath,UserItem user){
	try{
		//------------------重新生成URLS------------------------------------
		String Urlsn=ConstVar.URLS.get(user.getUsid());
		String muids=new String("0");
		if(Urlsn==null){
			StringBuffer urls=new StringBuffer();//URLs
			//-------------按钮URLS------------------------------------------
			List<PopedomswardItem>  popedoms = DataUtil.objectList(" from PopedomswardItem where psroleid in("+user.getUsrole()+") ");//取得当前用户的角色
			for(PopedomswardItem popedom:popedoms){//循环每个角色的记录
				if(popedom.getPswork()!=null && popedom.getPswork().trim().length()>1){
					String[] buttons = popedom.getPswork().split(",");//取得每个按扭
					for(String button:buttons){
						if(button.split("@").length>3){
							String burl=button.split("@")[3];//xx.nk|xxx.nk
							urls.append(burl.replace("|", ",")+",");
						}
					}
				}
				muids+=","+popedom.getPsmenuid();//菜单ID
			}
			//------------菜单URLS------------------------------------------
			List<MenuItem>  menus = DataUtil.objectList(" from MenuItem where menuid in("+muids+") ");//取得所有菜单
			for(MenuItem m:menus){
				if(m.getMenuurl()!=null && m.getMenuurl().trim().length()>3)
					urls.append(Function.getFileName(m.getMenuurl()).replace("|", ",")+",");//菜单自身的url
				if(m.getMenunburl()!=null && m.getMenunburl().trim().length()>3)
					urls.append(Function.getFileName(m.getMenunburl()).replace("|", ",")+",");//菜单下无操作公共URLS
			}
			//------------公共urls------------------------------------------
			urls.append(ConstVar.PUBLIC_URLS);
			//------------所有该用户能访问的URLS----------------------------
			ConstVar.URLS.put(user.getUsid(), urls.toString());
			Urlsn=ConstVar.URLS.get(user.getUsid());
		}
		//----------------检测URLS----------------------------------------
		if(Urlsn!=null){
			String[] urlsList = Urlsn.split(",");
			for(String us:urlsList){
				 Pattern pattern = Pattern.compile(us.replace("*","\\w*\\W*"));
				 Matcher matcher = pattern.matcher(filepath);
				 if (matcher.find()) {
					 return true;
				 }
			}
			return false;
		}
	}catch(Exception se){
		se.printStackTrace();
	}
	return false;
	
}
}
