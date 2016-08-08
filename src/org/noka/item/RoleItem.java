package org.noka.item;

import java.io.Serializable;

/**
* nk_sys_role
*/
public class RoleItem implements  Serializable {
    
	 private static final long serialVersionUID = -3631523067089556662L;
	 private Long roleid = null;// bigint identity ;
     private String rolename = null;// varchar ;
     private String roletext = null;// varchar ;
     private Integer rolecompositor = null;// int ;
      public Long getRoleid() {
     	return roleid;
     }
     public void setRoleid(Long  roleid) {
     	this.roleid = roleid;
     }
      public String getRolename() {
     	return rolename;
     }
     public void setRolename(String  rolename) {
     	this.rolename = rolename;
     }
      public String getRoletext() {
     	return roletext;
     }
     public void setRoletext(String  roletext) {
     	this.roletext = roletext;
     }
      public Integer getRolecompositor() {
     	return rolecompositor;
     }
     public void setRolecompositor(Integer  rolecompositor) {
     	this.rolecompositor = rolecompositor;
     }
}
