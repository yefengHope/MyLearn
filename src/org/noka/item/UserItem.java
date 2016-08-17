package org.noka.item;
import java.io.Serializable;
import java.util.Date;
/**
* nk_sys_userinfo
*/
public class UserItem implements  Serializable {
     
	private static final long serialVersionUID = 5551057058631551307L;
	private Long usid = null;// bigint identity ;
    private String usname = null;// varchar ;
    private String uspassword = null;// varchar ;
    private String usxname = null;// varchar ;
    private String ussex = null;// varchar ;
    private Date usborth = null;// datetime ;
    private Long usfugle = null;// bigint ;
    private String usmoble = null;// varchar ;
    private String ustel = null;// varchar ;
    private String ustext = null;// varchar ;
    private String usrole = null;// varchar ;
    private Date usdate = null;// datetime ;
    private String usemail = null;// varchar ;
    private Long uspost = null;// bigint ;
    private Integer uspass = null;// bit ;
    private Date userrtime = null;// int ;
    private Long uswork = null;// bigint ; 所属资源组
    private String usimage = null;// varchar ;USIMAGE
    private Long usgroup = null;// varchar ;所属部门 (组织机构)
      public Long getUsid() {
     	return usid;
     }
     public void setUsid(Long  usid) {
     	this.usid = usid;
     }
      public String getUsname() {
     	return usname;
     }
     public void setUsname(String  usname) {
     	this.usname = usname;
     }
      public String getUspassword() {
     	return uspassword;
     }
     public void setUspassword(String  uspassword) {
     	this.uspassword = uspassword;
     }
     
      public String getUsxname() {
     	return usxname;
     }
     public void setUsxname(String  usxname) {
     	this.usxname = usxname;
     }
      public String getUssex() {
     	return ussex;
     }
     public void setUssex(String  ussex) {
     	this.ussex = ussex;
     }
      public Date getUsborth() {
     	return usborth;
     }
     public void setUsborth(Date  usborth) {
     	this.usborth = usborth;
     }
     
      public Long getUsfugle() {
     	return usfugle;
     }
     public void setUsfugle(Long  usfugle) {
     	this.usfugle = usfugle;
     }
      public String getUsmoble() {
     	return usmoble;
     }
     public void setUsmoble(String  usmoble) {
     	this.usmoble = usmoble;
     }
      public String getUstel() {
     	return ustel;
     }
     public void setUstel(String  ustel) {
     	this.ustel = ustel;
     }
      public String getUstext() {
     	return ustext;
     }
     public void setUstext(String  ustext) {
     	this.ustext = ustext;
     }
      public String getUsrole() {
     	return usrole;
     }
     public void setUsrole(String  usrole) {
     	this.usrole = usrole;
     }
      public Date getUsdate() {
     	return usdate;
     }
     public void setUsdate(Date  usdate) {
     	this.usdate = usdate;
     }
      public String getUsemail() {
     	return usemail;
     }
     public void setUsemail(String  usemail) {
     	this.usemail = usemail;
     }
      public Long getUspost() {
     	return uspost;
     }
     public void setUspost(Long  uspost) {
     	this.uspost = uspost;
     }
      public Integer getUspass() {
     	return uspass;
     }
     public void setUspass(Integer  uspass) {
     	this.uspass = uspass;
     }
      public Date getUserrtime() {
     	return userrtime;
     }
     public void setUserrtime(Date  userrtime) {
     	this.userrtime = userrtime;
     }
	public Long getUswork() {
		return uswork;
	}
	public void setUswork(Long uswork) {
		this.uswork = uswork;
	}
	public String getUsimage() {
		return usimage;
	}
	public void setUsimage(String usimage) {
		this.usimage = usimage;
	}
	public Long getUsgroup() {
		return usgroup;
	}
	public void setUsgroup(Long usgroup) {
		this.usgroup = usgroup;
	}
  
     
}
