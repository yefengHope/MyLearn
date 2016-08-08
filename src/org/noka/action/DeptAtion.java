package org.noka.action;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.noka.item.DeptApiItem;
import org.noka.item.DeptItem;
import org.nokatag.system.DataUtil;
import org.nokatag.system.DataWhere;


public class DeptAtion extends BaseAction{

	private static final long serialVersionUID = 5724998352585258045L;
	private DeptItem deptItem = null;
	private String deptids = null;//部门ids
	private String id=null;
	private String dlvie=null;//级别号
	
	@Action("getdeptjson")
	public String getDeptS(){
		HttpServletRequest request = ServletActionContext.getRequest();
		try{
			String pid = request.getParameter("id");
			if(null==pid){
				pid="-1";
			}
			List<DataWhere> wl = new ArrayList<DataWhere>();
			wl.add(new DataWhere("dpid",pid));
			List<DeptItem>  listDeps =  DataUtil.objectList("from DeptItem where dpid=:dpid",wl);
			List<DeptApiItem> listDepts = new ArrayList<DeptApiItem>();
			for(DeptItem dl:listDeps){
				DeptApiItem da = new DeptApiItem();
				da.setId(String.valueOf(dl.getDid()));
				da.setPid(String.valueOf(dl.getDpid()));
				da.setName(dl.getDname());
				da.setOpen(false);
				da.setTitle(dl.getDtext());
				da.setParent(true);
				listDepts.add(da);
			}
			$PJson(listDepts);
		}catch(Exception se){
			
		}
		return null;
	}
	//--------主界面--------------
	@Action(value="deptmain",results={@Result(name="dept_dept",location="dept/dept.jsp")})
	public String deptmain(){
		HttpServletRequest request = ServletActionContext.getRequest();
		try{
			//----------------树形sql-----------------------------------------------------------------------------------------------
			String tSQL="SELECT DID AS ID ,DPID AS PID,DNAME AS NAME,DTEXT AS TITLE,DLVIE as LEVE,DTEXT,DPX FROM NK_SYS_DEPT";
			request.setAttribute("tsql", tSQL);
			//----------------------------------------------------------------------------------------------------------------------
			String SQL="SELECT DID AS "+$L("org.word.jsp.id")+",DPID AS PID,DNAME AS "+$L("org.word.jsp.name_"+muid)+", DTEXT AS "+$L("org.word.jsp.notes")+",DPX AS "+$L("org.word.jsp.pxun")+",DLVIE AS "+$L("org.treeword.jsp.fj")+",DLVIE as LEVE FROM NK_SYS_DEPT WHERE 1=1 ";		
			if(id!=null && !id.equalsIgnoreCase("0")){
				Long tid=Long.parseLong(id);
				DeptItem tb = (DeptItem)DataUtil.object("from DeptItem where did="+tid+" ");
				if(tb!=null){
					SQL+=" AND DLVIE LIKE  '"+tb.getDlvie()+"_"+tb.getDid()+"%' ";
					request.setAttribute("dept",tb);
					request.setAttribute("isrro","no");
				}
			}else{
				DeptItem tb = new DeptItem();
				tb.setDid(-1l);
				tb.setDpid(-1l);
				tb.setDlvie("0");
				request.setAttribute("dept",tb);
				request.setAttribute("isrro","yes");
			}
			request.setAttribute("sql", SQL);
		}catch(Exception se){
			se.printStackTrace();
		}
		if(msg!=null)
			request.setAttribute("msg", $L(msg));
		return "dept_dept";
	}
	//-----------添加---------------
	@Action("deptadd")
	public String deptadd(){
		try{
		if(deptItem!=null){
			deptItem.setDlvie(deptItem.getDlvie()+"_"+deptItem.getDpid());
			deptItem.setDdate(new Date());
			DataUtil.insert(deptItem);
			$P("1");
		}else{
			$P("2");
		}
		}catch(Exception se){}
		return null;
	}
	//------------修改------------------
	@Action("deptupdate")
	public String deptupdate(){
		try{
		if(deptItem!=null && deptItem.getDid()!=null){
			deptItem.setDdate(new Date());
			DataUtil.update(deptItem);
			$P("3");
		}else{
			$P("4");
		}
		}catch(Exception se){}
		return null;
	}
	//-----------删除----------------------
	@Action("deptdel")
	public String deptdel(){
		List<DeptItem> deptItemList = new ArrayList<DeptItem>();
		if(deptids!=null){
			try{
				String[] wordids = deptids.split(",");
			for(int i=0;i<wordids.length;i++){
				DeptItem deptItems = new DeptItem();
				Long srt = Long.parseLong(wordids[i]);
				deptItems.setDid(srt);
				deptItemList.add(deptItems);
			}
			if(!deptItemList.isEmpty()){
				DataUtil.deleteList(deptItemList);
				$P("5");
			}else{
				$P("6");
			}
			}catch(Exception se){
				se.printStackTrace();
			}
		}
		return null;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public DeptItem getDeptItem() {
		return deptItem;
	}
	public void setDeptItem(DeptItem deptItem) {
		this.deptItem = deptItem;
	}
	public String getDeptids() {
		return deptids;
	}
	public void setDeptids(String deptids) {
		this.deptids = deptids;
	}
	public String getDlvie() {
		return dlvie;
	}
	public void setDlvie(String dlvie) {
		this.dlvie = dlvie;
	}
	

}
