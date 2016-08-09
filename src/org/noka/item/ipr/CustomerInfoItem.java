package org.noka.item.ipr;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 韩峰
 */
public class CustomerInfoItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5217793620805005258L;
	
	private Long cuid;
	private String cucrmnumber;
	private String cucustomername;
	private Long cufollowid;
	private String cufollowname;
	private String cuapplyname;
	private Integer cuapplytype;
	private String cudoccard;
	private String cuapplyadress;
	private String cuapplyadressdetail;
	private Date cuapplydate;
	private Date cucreatedate;
	
	
	public Long getCuid() {
		return cuid;
	}
	public void setCuid(Long cuid) {
		this.cuid = cuid;
	}
	public String getCucrmnumber() {
		return cucrmnumber;
	}
	public void setCucrmnumber(String cucrmnumber) {
		this.cucrmnumber = cucrmnumber;
	}
	public String getCucustomername() {
		return cucustomername;
	}
	public void setCucustomername(String cucustomername) {
		this.cucustomername = cucustomername;
	}
	public Long getCufollowid() {
		return cufollowid;
	}
	public void setCufollowid(Long cufollowid) {
		this.cufollowid = cufollowid;
	}
	public String getCufollowname() {
		return cufollowname;
	}
	public void setCufollowname(String cufollowname) {
		this.cufollowname = cufollowname;
	}
	public String getCuapplyname() {
		return cuapplyname;
	}
	public void setCuapplyname(String cuapplyname) {
		this.cuapplyname = cuapplyname;
	}
	public Integer getCuapplytype() {
		return cuapplytype;
	}
	public void setCuapplytype(Integer cuapplytype) {
		this.cuapplytype = cuapplytype;
	}
	public String getCudoccard() {
		return cudoccard;
	}
	public void setCudoccard(String cudoccard) {
		this.cudoccard = cudoccard;
	}
	public String getCuapplyadress() {
		return cuapplyadress;
	}
	public void setCuapplyadress(String cuapplyadress) {
		this.cuapplyadress = cuapplyadress;
	}
	public String getCuapplyadressdetail() {
		return cuapplyadressdetail;
	}
	public void setCuapplyadressdetail(String cuapplyadressdetail) {
		this.cuapplyadressdetail = cuapplyadressdetail;
	}
	public Date getCuapplydate() {
		return cuapplydate;
	}
	public void setCuapplydate(Date cuapplydate) {
		this.cuapplydate = cuapplydate;
	}
	public Date getCucreatedate() {
		return cucreatedate;
	}
	public void setCucreatedate(Date cucreatedate) {
		this.cucreatedate = cucreatedate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
