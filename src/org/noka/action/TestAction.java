package org.noka.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.noka.item.TestItem;
import org.nokatag.system.DataUtil;

public class TestAction extends BaseAction{
	private static final long serialVersionUID = 4280006199493726238L;
	private TestItem wordBook=null;//测试
	private String wordrows = null;//测试id
	/**
	 * 字典主操作界面
	 */
	@Action(value="test",results={@Result(name="wordbook_input",location="test/test.jsp")})
	public String wordBookForJsp(){
		HttpServletRequest request = ServletActionContext.getRequest();
		try{
			String SQL="select TID AS IDS,TID,TNAME from nk_ope_test";
			request.setAttribute("sql",  sqlFilter(SQL, "nk_ope_test"));
		}catch(Exception se){
				
		}
		return "wordbook_input";
	}
	@Action("testadd")
	public String testadd(){
		try{
			if(wordBook!=null){
				DataUtil.insert(wordBook);
				 $P("1");
			}else{
				$P("2");
			}
			
		}catch(Exception se){}
		return null;
	}
	@Action("testdel")
	public String wordBookdel(){
		List<TestItem> wordbooks = new ArrayList<TestItem>();
		try{
		if(wordrows!=null){
			String[] wordids = wordrows.split(",");//1,2,3,4
			for(int i=0;i<wordids.length;i++){
				TestItem wordbookitem = new TestItem();
				Long srt = Long.parseLong(wordids[i]);
				wordbookitem.setTid(srt);
				wordbooks.add(wordbookitem);
			}
			if(!wordbooks.isEmpty()){
				DataUtil.deleteList(wordbooks);
				$P("5");
			}else{
				$P("6");
			}
		}
		}catch(Exception se){
			
		}
		return null;
	}
	@Action("testupdate")
	public String wordBookupdate(){
		try{
		if(wordBook!=null && wordBook.getTid()!=null){
			DataUtil.update(wordBook);
			$P("3");
		}else{
			$P("4");
		}
		}catch(Exception se){}
		return null;
	}
	public TestItem getWordBook() {
		return wordBook;
	}
	public void setWordBook(TestItem wordBook) {
		this.wordBook = wordBook;
	}
	public String getWordrows() {
		return wordrows;
	}
	public void setWordrows(String wordrows) {
		this.wordrows = wordrows;
	}
	
}
