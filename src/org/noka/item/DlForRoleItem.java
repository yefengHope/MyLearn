package org.noka.item;

import java.io.Serializable;

/**
* nk_sys_dlforrole
*/
public class DlForRoleItem implements  Serializable {
     /**
	 * 
	 */
	private static final long serialVersionUID = -1654284303972795239L;
	private Long drid = null;//本表id bigint ;
    private Long droleid = null;//角色ID  bigint ;
    private String drtablename = null;//表名 varchar ;
    private String druserfield = null;//过滤字段-用户  varchar ;
    private String drdeptfield = null;//过滤字段-部门  varchar ;
    private Integer drtype	= null;//过滤类型(1所有部门|2本部门|3本部门及下属部门|4用户自己|5指定部门)	
    private String drdept = null;//指定部门(当过滤类型为5时使用该字段)
    
    
	public Long getDrid() {
		return drid;
	}
	public void setDrid(Long drid) {
		this.drid = drid;
	}
	public Long getDroleid() {
		return droleid;
	}
	public void setDroleid(Long droleid) {
		this.droleid = droleid;
	}
	public String getDrtablename() {
		return drtablename;
	}
	public void setDrtablename(String drtablename) {
		this.drtablename = drtablename;
	}
	public String getDruserfield() {
		return druserfield;
	}
	public void setDruserfield(String druserfield) {
		this.druserfield = druserfield;
	}
	public String getDrdeptfield() {
		return drdeptfield;
	}
	public void setDrdeptfield(String drdeptfield) {
		this.drdeptfield = drdeptfield;
	}
	public Integer getDrtype() {
		return drtype;
	}
	public void setDrtype(Integer drtype) {
		this.drtype = drtype;
	}
	public String getDrdept() {
		return drdept;
	}
	public void setDrdept(String drdept) {
		this.drdept = drdept;
	}
     
     
}
