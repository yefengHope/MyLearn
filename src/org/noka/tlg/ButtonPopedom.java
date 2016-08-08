package org.noka.tlg;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.noka.item.UserItem;
import org.nokatag.tagjava.Inputsuper;
/**
 * 按钮权限
 * @author xiefangjian
 *
 */
public class ButtonPopedom extends Inputsuper{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2724252252280470791L;
	private String type="button";
	
	public int doStartTag()throws JspException{
		JspWriter jspOut = pageContext.getOut();
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		String menuid = request.getAttribute("muid").toString();
		String jsp = request.getRequestURI();
		int let = jsp.length();
		if(jsp.indexOf("?")!=-1)
			let = jsp.indexOf("?");
		if(jsp.indexOf(":")!=-1)
			let = jsp.indexOf(":");
		jsp=jsp.substring(jsp.lastIndexOf("/")+1,let);
		//---------------------------------------------------------------
		Boolean hide_button = false;//
		Boolean nk_sso = (request.getAttribute("noka_sso")==null?false:(Boolean)request.getAttribute("noka_sso"));//true sso load
		if(nk_sso && ("dept.jsp".equals(jsp) || "user.jsp".equals(jsp))){
			hide_button = true;
		}
		//---------------------------------------------------------------
		StringBuilder stringBuilder = new StringBuilder();
		if(!hide_button && request.getSession().getAttribute("user")!=null){
			UserItem user = (UserItem)request.getSession().getAttribute("user");//取得当前用户
			List<Map<String,String>> ButtonMap = ButtonUtil.buttons(user);//取得该用户所有按钮权限
			for(Map<String,String> map:ButtonMap){
				if(jsp.equals(map.get("jsp")) && getName().equals(map.get("enname")) && String.valueOf(menuid).equals(map.get("menuid"))){
					if(getValue()==null)
						setValue(map.get("zhname"));
					stringBuilder.append("<input type=\""+type+"\"  ");
					stringBuilder.append(getBaseStr());
					stringBuilder.append("  />");
					
					break;
				}
			}
		}
		try {
			jspOut.write(stringBuilder.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
