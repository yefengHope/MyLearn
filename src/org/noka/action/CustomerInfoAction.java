/**
 * 
 */
package org.noka.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.noka.item.ipr.CustomerInfoItem;
import org.nokatag.system.DataUtil;

/**
 * 客户信息
 * @author 韩峰 - 2016年8月9日
 */
public class CustomerInfoAction extends BaseAction{
	
	private CustomerInfoItem customer;

	@Action(value = "customerinfolist" ,results = {@Result(name = "list" ,location = "customerinfo/list.jsp")})
	public String customerlist(){
		HttpServletRequest request = ServletActionContext.getRequest();
		StringBuffer SQL = new StringBuffer("SELECT cu.cuapplyadress,cu.cuapplyadressdetail,cu.cuapplydate,cu.cuapplyname,cu.cuapplytype,cu.cucreatedate FROM nk_ope_customerinfo cu");
		request.setAttribute("sql", sqlFilter(SQL.toString().toUpperCase(), "NK_OPE_CUSTOMERINFO"));
		System.out.println("SQL:"+SQL.toString().toUpperCase());
		return "list";
	}
	@Action(value = "customerinfoadd" /*,results = {@Result(name = "add" ,location = "")}*/)
	public String customeradd(){
		try {
			if(null != customer){
				DataUtil.insert(customer);
				$P("1");
			}else {
				$P("2");
			}
		}catch(Exception se){}
		return null;
	}
	
	@Action(value = "customerinfoupdate"/*, results = {@Result(name = "update" ,location = "")}*/)
	public String customerupdate(){
		
		return null;
	}
	
	@Action(value = "customerinfodelete" /*,results = {@Result(name = "delete" ,location = "")}*/)
	public String customerdelete(){
		return null;
	}
	
	
	
	public void validate(){
		if(null != getCustomer()){
			if(null == getCustomer().getCufollowid()){
	            addFieldError("followid", getText("followid.required"));
	        }
		}
        
    }
	
	public CustomerInfoItem getCustomer() {
		return customer;
	}
	
	public void setCustomer(CustomerInfoItem customer) {
		this.customer = customer;
	}
	
	
	
	
}
