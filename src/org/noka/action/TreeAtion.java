package org.noka.action;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.noka.item.TreeBookItem;
import org.nokatag.system.DataUtil;


public class TreeAtion extends BaseAction{

	private static final long serialVersionUID = 5724998352585258045L;
	private TreeBookItem treeBook = null;
	private String treeids = null;//字典id
	private String id=null; 
	private String tlvie=null;
	//--------主界面--------------
	@Action(value="treemain",results={@Result(name="treebook_main",location="treebook/main.jsp")})
	public String treemain(){
		HttpServletRequest request = ServletActionContext.getRequest();
		try{
			Long wordt=Long.parseLong(muid);
			//----------------树形sql-----------------------------------------------------------------------------------------------
			String tSQL="SELECT TID AS ID ,TPID AS PID,TNAME AS NAME,TTEXT AS TITLE,TLVIE as leve,ttext,tpx FROM NK_SYS_TREEBOOK WHERE TTYPE='"+wordt+"'";
			request.setAttribute("tsql", tSQL);
			//----------------------------------------------------------------------------------------------------------------------
			String SQL="SELECT TID AS "+$L("org.word.jsp.id")+",TPID AS PID,TNAME AS "+$L("org.word.jsp.name_"+muid)+", TTEXT AS "+$L("org.word.jsp.notes")+",TPX AS "+$L("org.word.jsp.pxun")+",TLVIE AS "+$L("org.treeword.jsp.fj")+",TLVIE as leve FROM NK_SYS_TREEBOOK WHERE TTYPE="+wordt;		
			if(id!=null && !id.equalsIgnoreCase("0")){
				Long tid=Long.parseLong(id);
				TreeBookItem tb = (TreeBookItem)DataUtil.object("from TreeBookItem where tid="+tid+" ");
				if(tb!=null){
					SQL+=" AND TLVIE LIKE  '"+tb.getTlvie()+"_"+tb.getTid()+"%' ";
					request.setAttribute("treebook",tb);
					request.setAttribute("isrro","no");
				}
			}else{
				TreeBookItem tb = new TreeBookItem();
				tb.setTid(-1l);
				tb.setTpid(-1l);
				tb.setTlvie("0");
				request.setAttribute("treebook",tb);
				request.setAttribute("isrro","yes");
			}
			request.setAttribute("sql", SQL);
		}catch(Exception se){
			se.printStackTrace();
		}
		if(msg!=null)
			request.setAttribute("msg", $L(msg));
		return "treebook_main";
	}
	//-----------添加---------------
	@Action("treeadd")
	public String treeadd(){
		try{
		if(treeBook!=null){
			treeBook.setTlvie(treeBook.getTlvie()+"_"+treeBook.getTpid());
			treeBook.setTdate(new Date());
			DataUtil.insert(treeBook);
			$P("1");
		}else{
			$P("2");
		}
		}catch(Exception se){}
		return null;
	}
	//------------修改------------------
	@Action("treeupdate")
	public String treeupdate(){
		try{
		if(treeBook!=null && treeBook.getTid()!=null){
			treeBook.setTdate(new Date());
			DataUtil.update(treeBook);
			$P("3");
		}else{
			$P("4");
		}
		}catch(Exception se){}
		return null;
	}
	//-----------删除----------------------
	@Action("treedel")
	public String treedel(){
		List<TreeBookItem> treebooks = new ArrayList<TreeBookItem>();
		if(treeids!=null){
			try{
				String[] wordids = treeids.split(",");
			for(int i=0;i<wordids.length;i++){
				TreeBookItem treebookitem = new TreeBookItem();
				Long srt = Long.parseLong(wordids[i]);
				treebookitem.setTid(srt);
				treebooks.add(treebookitem);
			}
			if(!treebooks.isEmpty()){
				DataUtil.deleteList(treebooks);
				$P("5");
			}else{
				$P("6");
			}
			}catch(Exception se){}
		}
		return null;
	}
	public TreeBookItem getTreeBook() {
		return treeBook;
	}
	public void setTreeBook(TreeBookItem treeBook) {
		this.treeBook = treeBook;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTlvie() {
		return tlvie;
	}
	public void setTlvie(String tlvie) {
		this.tlvie = tlvie;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getTreeids() {
		return treeids;
	}
	public void setTreeids(String treeids) {
		this.treeids = treeids;
	}
	

}
