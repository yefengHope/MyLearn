package org.noka.tlg;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;
import org.noka.constvar.ConstVar;

public class LanugeTlg extends TagSupport{
	private static final long serialVersionUID = 4255378824665115594L;
	private String key = null;
	private String value=null;
	
	
	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		String bsr = null;
		try{
			bsr = ConstVar.LANGUAGES_STR.get(request.getAttribute("syslanuage")).get(key);
		}catch(Exception se){
		}
		if(bsr == null && StringUtils.isNotEmpty(key)){
			bsr = key;
		}else if(bsr == null || StringUtils.isEmpty(key)){
			bsr = "NoFind";
		}
		JspWriter jspOut = pageContext.getOut();
		StringBuilder bodystr = new StringBuilder();
		if(value!=null)
			request.setAttribute(value, bsr);
		else{
			bodystr.append(bsr);
			try {
				jspOut.write(bodystr.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return EVAL_BODY_INCLUDE;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
