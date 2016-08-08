package org.noka.item;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
* nk_sys_menu
*/
public class MenuItem implements  Serializable {
     /**
	 * 
	 */
	private static final long serialVersionUID = 8660188258485624429L;
	private Long menuid = null;// bigint ;
     private Long menupid = null;// bigint ;
     private String menuname = null;// varchar ;
     private String menuurl = null;// varchar ;
     private String menuoperate = null;// varchar ;
     private String menuimage = null;// varchar ;
     private Integer menutaxis = null;// int ;
     private String menutarget = null;// varchar ;
     private String menunburl=null;// MENUNBURL=null;
     private String menuleve = null;//级别号
     
     private List<Map<String,String>> menupageid=null;//用于页面
      public List<Map<String,String>> getMenupageid() {
		return menupageid;
	}
	public void setMenupageid(List<Map<String,String>> menupageid) {
		this.menupageid = menupageid;
	}
	public Long getMenuid() {
     	return menuid;
     }
     public void setMenuid(Long  menuid) {
     	this.menuid = menuid;
     }
      public Long getMenupid() {
     	return menupid;
     }
     public void setMenupid(Long  menupid) {
     	this.menupid = menupid;
     }
      public String getMenuname() {
     	return menuname;
     }
     public void setMenuname(String  menuname) {
     	this.menuname = menuname;
     }
      public String getMenuurl() {
     	return menuurl;
     }
     public void setMenuurl(String  menuurl) {
     	this.menuurl = menuurl;
     }
      public String getMenuoperate() {
     	return menuoperate;
     }
     public void setMenuoperate(String  menuoperate) {
     	this.menuoperate = menuoperate;
     }
      public String getMenuimage() {
     	return menuimage;
     }
     public void setMenuimage(String  menuimage) {
     	this.menuimage = menuimage;
     }
      public Integer getMenutaxis() {
     	return menutaxis;
     }
     public void setMenutaxis(Integer  menutaxis) {
     	this.menutaxis = menutaxis;
     }
      public String getMenutarget() {
     	return menutarget;
     }
     public void setMenutarget(String  menutarget) {
     	this.menutarget = menutarget;
     }
	public String getMenunburl() {
		return menunburl;
	}
	public void setMenunburl(String menunburl) {
		this.menunburl = menunburl;
	}
	public String getMenuleve() {
		return menuleve;
	}
	public void setMenuleve(String menuleve) {
		this.menuleve = menuleve;
	}
}
