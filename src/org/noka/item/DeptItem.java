package org.noka.item;

import java.io.Serializable;
import java.util.Date;

// data table name:nk_sys_dept
public class DeptItem implements Serializable {
	private static final long serialVersionUID = -5819960499611140036L;
	private Long did = null;// DID BIGINT
	private Long dpid = null;// DPID BIGINT
	private String dname = null;// DNAME VARCHAR
	private String dtext = null;// DTEXT VARCHAR
	private Long dpx = null;// DPX BIGINT
	private Long duser = null;// DUSER BIGINT
	private Long ddept = null;// DDEPT BIGINT
	private Date ddate = null;// DDATE DATETIME
	private String durl = null;// DURL VARCHAR
	private String dlvie = null;// DLVIE VARCHAR

	

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getDtext() {
		return dtext;
	}

	public void setDtext(String dtext) {
		this.dtext = dtext;
	}

	public Long getDpx() {
		return dpx;
	}

	public void setDpx(Long dpx) {
		this.dpx = dpx;
	}

	public Long getDuser() {
		return duser;
	}

	public void setDuser(Long duser) {
		this.duser = duser;
	}

	public Long getDdept() {
		return ddept;
	}

	public void setDdept(Long ddept) {
		this.ddept = ddept;
	}

	public Date getDdate() {
		return ddate;
	}

	public void setDdate(Date ddate) {
		this.ddate = ddate;
	}

	public String getDurl() {
		return durl;
	}

	public void setDurl(String durl) {
		this.durl = durl;
	}

	public String getDlvie() {
		return dlvie;
	}

	public void setDlvie(String dlvie) {
		this.dlvie = dlvie;
	}

	public Long getDid() {
		return did;
	}

	public void setDid(Long did) {
		this.did = did;
	}

	public Long getDpid() {
		return dpid;
	}

	public void setDpid(Long dpid) {
		this.dpid = dpid;
	}
}