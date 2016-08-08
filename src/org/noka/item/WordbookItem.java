package org.noka.item;
import java.io.Serializable;
import java.util.Date;
/**
* nk_sys_wordbook
*/
public class WordbookItem implements  Serializable {
   
	private static final long serialVersionUID = 5995819613372503528L;
	private Long wid = null;// bigint identity ;
     private Long wtype = null;// bigint ;
     private String wname = null;// varchar ;
     private String wtext = null;// varchar ;
     private Date wcdate = null;// datetime ;
     private Integer wcompositor = null;// int ;
     private Long wuser = null;//bigint
     private Long wcworkdept = null;// bigint ;
     
      public Long getWid() {
     	return wid;
     }
     public void setWid(Long  wid) {
     	this.wid = wid;
     }
      public Long getWtype() {
     	return wtype;
     }
     public void setWtype(Long  wtype) {
     	this.wtype = wtype;
     }
      public String getWname() {
     	return wname;
     }
     public void setWname(String  wname) {
     	this.wname = wname;
     }
      public String getWtext() {
     	return wtext;
     }
     public void setWtext(String  wtext) {
     	this.wtext = wtext;
     }
      public Date getWcdate() {
     	return wcdate;
     }
     public void setWcdate(Date  wcdate) {
     	this.wcdate = wcdate;
     }
      public Integer getWcompositor() {
     	return wcompositor;
     }
     public void setWcompositor(Integer  wcompositor) {
     	this.wcompositor = wcompositor;
     }
	public Long getWcworkdept() {
		return wcworkdept;
	}
	public void setWcworkdept(Long wcworkdept) {
		this.wcworkdept = wcworkdept;
	}
	public Long getWuser() {
		return wuser;
	}
	public void setWuser(Long wuser) {
		this.wuser = wuser;
	}

}
