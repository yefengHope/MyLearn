/**
 * 
 */
package org.noka.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.noka.item.ipr.CustomerInfoItem;

/**
 * 客户信息
 * @author 韩峰 - 2016年8月9日
 */
public class CustomerInfoAction extends BaseAction{
	
	private CustomerInfoItem customerInfo;

	@Action(value = "list" /*,results = {@Result(name = "list" ,location = "")}*/)
	public String customerlist(){
		HttpServletRequest request = ServletActionContext.getRequest();
		StringBuffer SQL = new StringBuffer("SELECT cu.cuapplyadress,cu.cuapplyadressdetail,cu.cuapplydate,cu.cuapplyname,cu.cuapplytype,cu.cucreatedate FROM nk_ope_customerinfo cu;");
		request.setAttribute("sql", sqlFilter(SQL.toString(), "NK_OPE_CUSTOMERINFO"));
		return null;
	}
	@Action(value = "update"/*, results = {@Result(name = "update" ,location = "")}*/)
	public String customerupdate(){
		return null;
	}
	@Action(value = "delete" /*,results = {@Result(name = "delete" ,location = "")}*/)
	public String customerdelete(){
		return null;
	}
	@Action(value = "add" /*,results = {@Result(name = "add" ,location = "")}*/)
	public String customeradd(){
		return null;
	}
	
	
	public CustomerInfoItem getCustomerInfo() {
		return customerInfo;
	}
	public void setCustomerInfo(CustomerInfoItem customerInfo) {
		this.customerInfo = customerInfo;
	}
	
	
}
