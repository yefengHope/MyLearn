package org.noka.action;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.noka.item.WordbookItem;
import org.nokatag.system.DataUtil;

/**
 * 基础字典
 * @author xiefangjian
 *
 */
public class WordBookAction extends BaseAction{
	//-----------------------------------------------------------------------
	private static final long serialVersionUID = 4347359364608819110L;
	private WordbookItem wordBook=null;//字典实体类
	private String wordrows = null;//字典id
	/**
	 * 字典主操作界面
	 */
	@Action(value="wordbook",results={@Result(name="wordbook_input",location="wordbook/input.jsp")})
	public String wordBookForJsp(){
		HttpServletRequest request = ServletActionContext.getRequest();
		try{
			Long wordt=Long.parseLong(muid);
			String SQL="SELECT WID AS 标识号,WNAME AS 名称, WTEXT AS 备注,WCOMPOSITOR as 排序,WCWORKDEPT,WCDATE,WUSER FROM NK_SYS_WORDBOOK WHERE  WTYPE='"+wordt+"'";
			request.setAttribute("sql",  sqlFilter(SQL, "NK_SYS_WORDBOOK"));
		}catch(Exception se){
				
		}
		return "wordbook_input";
	}
	@Action("wordbookadd")
	public String wordBookadd(){
		try{
			if(wordBook!=null){
				wordBook.setWcdate(new Date());
				DataUtil.insert(wordBook);
				 $P("1");
			}else{
				$P("2");
			}
			
		}catch(Exception se){}
		return null;
	}
	@Action("wordBookdel")
	public String wordBookdel(){
		List<WordbookItem> wordbooks = new ArrayList<WordbookItem>();
		try{
		if(wordrows!=null){
			String[] wordids = wordrows.split(",");//1,2,3,4
			for(int i=0;i<wordids.length;i++){
				WordbookItem wordbookitem = new WordbookItem();
				Long srt = Long.parseLong(wordids[i]);
				wordbookitem.setWid(srt);
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
	@Action("wordBookupdate")
	public String wordBookupdate(){
		try{
		if(wordBook!=null && wordBook.getWid()!=null){
			wordBook.setWcdate(new Date());
			DataUtil.update(wordBook);
			$P("3");
		}else{
			$P("4");
		}
		}catch(Exception se){}
		return null;
	}
	
	
	
	
	//-----------------------------------------------------------------------
	
	public WordbookItem getWordBook() {
		return wordBook;
	}
	public void setWordBook(WordbookItem wordBook) {
		this.wordBook = wordBook;
	}
	//-----------------------------------------------------------------------

	

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getWordrows() {
		return wordrows;
	}

	public void setWordrows(String wordrows) {
		this.wordrows = wordrows;
	}
}
