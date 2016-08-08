package org.noka.item;

import java.io.Serializable;

/**
* nk_sys_popedomsward
*/
public class PopedomswardItem implements  Serializable {
     /**
	 * 
	 */
	private static final long serialVersionUID = -6124100530555247945L;
	private Long psid = null;// bigint identity ;
     private Long psurid = null;// bigint ;
     private Long psmenuid = null;// bigint ;
     private String pswork = null;// varchar ;
     private Long psroleid = null;// bigint ;
      public Long getPsid() {
     	return psid;
     }
     public void setPsid(Long  psid) {
     	this.psid = psid;
     }
      public Long getPsurid() {
     	return psurid;
     }
     public void setPsurid(Long  psurid) {
     	this.psurid = psurid;
     }
      public Long getPsmenuid() {
     	return psmenuid;
     }
     public void setPsmenuid(Long  psmenuid) {
     	this.psmenuid = psmenuid;
     }
      public String getPswork() {
     	return pswork;
     }
     public void setPswork(String  pswork) {
     	this.pswork = pswork;
     }
      public Long getPsroleid() {
     	return psroleid;
     }
     public void setPsroleid(Long  psroleid) {
     	this.psroleid = psroleid;
     }
}
