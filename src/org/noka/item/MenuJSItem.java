package org.noka.item;

import java.io.Serializable;
import java.util.List;

/**
 * <p>@Title 菜单实体类(主界面插件使用)</p>
 * <p>@Description </p>
 * <p>@Version 1.0.0</p>
 * <p>@author rebin</p>
 * <p>@date 2016年7月28日</p>
 * <p>xiefangjian@163.com</p>
 * <p>Copyright © Noka Laboratory.All Rights Reserved.</p>
 */
public class MenuJSItem implements  Serializable {
	private static final long serialVersionUID = -191704104028783069L;
	private String muid = null;//菜单ID
	private String name = null;//菜单名称
	private String url = null;//菜单URL
	private String img = null;//菜单图片
	private String target=null;//跳转目标
	private String onclick=null;//点击事件
	private List<MenuJSItem> sub= null;//子菜单
	
	public MenuJSItem(String muid,String name,String url,String img,String target,String onclick){
		this.muid = muid;
		this.name = name;
		this.url = url;
		this.img = img;
		this.target = target;
		this.onclick = onclick;
	}
	
	public MenuJSItem(String muid,String name,String url,String img,String target){
		this.muid = muid;
		this.name = name;
		this.url = url;
		this.img = img;
		this.target = target;
	}
	
	public String getMuid() {
		return muid;
	}
	public void setMuid(String muid) {
		this.muid = muid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public List<MenuJSItem> getSub() {
		return sub;
	}
	public void setSub(List<MenuJSItem> sub) {
		this.sub = sub;
	}

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}
	
	
}
