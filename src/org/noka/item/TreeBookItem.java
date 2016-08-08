package org.noka.item;
import java.io.Serializable;
import java.util.Date;
/**
* nk_sys_treebook
*/
public class TreeBookItem implements  Serializable {
   
	private static final long serialVersionUID = 8013802139952656825L;
	private Long tid = null;// bigint ;
     private Long tpid = null;// bigint ;
     private String tname = null;// varchar ;
     private String ttext = null;// varchar ;
     private Integer ttype = null;// int ;
     private Long tpx = null;// bigint ;
     private Long tuser = null;// bigint ;
     private Long tdept = null;//bigint
     private Date tdate = null;// datetime ;
     private String turl=null;//
     private String tlvie = null;
     
      public String getTlvie() {
		return tlvie;
	}
	public void setTlvie(String tlvie) {
		this.tlvie = tlvie;
	}
	public String getTurl() {
		return turl;
	}
	public void setTurl(String turl) {
		this.turl = turl;
	}
	public Long getTid() {
     	return tid;
     }
     public void setTid(Long  tid) {
     	this.tid = tid;
     }
      public Long getTpid() {
     	return tpid;
     }
     public void setTpid(Long  tpid) {
     	this.tpid = tpid;
     }
      public String getTname() {
     	return tname;
     }
     public void setTname(String  tname) {
     	this.tname = tname;
     }
      public String getTtext() {
     	return ttext;
     }
     public void setTtext(String  ttext) {
     	this.ttext = ttext;
     }
      public Integer getTtype() {
     	return ttype;
     }
     public void setTtype(Integer  ttype) {
     	this.ttype = ttype;
     }
      public Long getTpx() {
     	return tpx;
     }
     public void setTpx(Long  tpx) {
     	this.tpx = tpx;
     }
      public Long getTuser() {
     	return tuser;
     }
     public void setTuser(Long  tuser) {
     	this.tuser = tuser;
     }
      public Date getTdate() {
     	return tdate;
     }
     public void setTdate(Date  tdate) {
     	this.tdate = tdate;
     }
	public Long getTdept() {
		return tdept;
	}
	public void setTdept(Long tdept) {
		this.tdept = tdept;
	}
}
