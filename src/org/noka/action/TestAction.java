package org.noka.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.noka.dbutil.NLogUtil;
import org.nokatag.system.DataUtil;

public class TestAction extends BaseAction{
	private static final long serialVersionUID = 4280006199493726238L;
	/**
	 * 字典主操作界面
	 */
	@Action(value="test",results={@Result(name="test_test",location="test/test.jsp")})
	public String wordBookForJsp(){
		HttpServletRequest request = ServletActionContext.getRequest();
		try{
			String SQL="select TID AS IDS,TID,TNAME,TNAME AS F,TNAME AS D,TNAME AS QWW,TNAME AS RRE,TNAME AS FFE from nk_ope_test";
			request.setAttribute("sql",  sqlFilter(SQL, "nk_ope_test"));
			NLogUtil.log(this.getClass(),"test");
		}catch(Exception se){
				
		}

		return "test_test";
	}
}
