package org.noka.item.ipr;

import java.io.Serializable;

/**
 * 联系人信息
 * @author 韩峰 - 2016年8月9日
 */
public class ContactsInfoItem implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7820158072649803052L;
	
	private Long caid;
	private String cacustomerinfoid;
	private String caname;
	private String caphone;
	private String caemail;
	private String caadress;
	private String cacreatedate;
	public Long getCaid() {
		return caid;
	}
	public void setCaid(Long caid) {
		this.caid = caid;
	}
	public String getCacustomerinfoid() {
		return cacustomerinfoid;
	}
	public void setCacustomerinfoid(String cacustomerinfoid) {
		this.cacustomerinfoid = cacustomerinfoid;
	}
	public String getCaname() {
		return caname;
	}
	public void setCaname(String caname) {
		this.caname = caname;
	}
	public String getCaphone() {
		return caphone;
	}
	public void setCaphone(String caphone) {
		this.caphone = caphone;
	}
	public String getCaemail() {
		return caemail;
	}
	public void setCaemail(String caemail) {
		this.caemail = caemail;
	}
	public String getCaadress() {
		return caadress;
	}
	public void setCaadress(String caadress) {
		this.caadress = caadress;
	}
	public String getCacreatedate() {
		return cacreatedate;
	}
	public void setCacreatedate(String cacreatedate) {
		this.cacreatedate = cacreatedate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
	
}
