package org.noka.item;

import java.io.Serializable;

/**
 * 操作按钮
 * @author xiefangjian
 *
 */
public class ButtonItem implements  Serializable {
	
	private static final long serialVersionUID = 7930534596590398018L;
	private String page=null;//页面
	private String text=null;//中文说明
	private String bname=null;//按钮名
	private String bstr=null;//字符
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getBstr() {
		return bstr;
	}
	public void setBstr(String bstr) {
		this.bstr = bstr;
	}
}
