package org.noka.test;

import java.io.OutputStream;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.noka.csv.CsvWriter;
import org.noka.itextpdf.text.Document;
import org.noka.itextpdf.text.Element;
import org.noka.itextpdf.text.Font;
import org.noka.itextpdf.text.PageSize;
import org.noka.itextpdf.text.Paragraph;
import org.noka.itextpdf.text.pdf.BaseFont;
import org.noka.itextpdf.text.pdf.PdfPTable;
import org.noka.itextpdf.text.pdf.PdfWriter;
import org.nokatag.dbgrid.NDBOutFileInterface;
import org.nokatag.jxl.Workbook;
import org.nokatag.jxl.write.Label;
import org.nokatag.jxl.write.WritableSheet;
import org.nokatag.jxl.write.WritableWorkbook;
import org.nokatag.system.BugInfoWrint;
import org.nokatag.system.Concest;


public class Outfile implements NDBOutFileInterface{
	//==========================CSV文件======================================================
	public void outCsvFile(OutputStream os, List<List<String>> row,List<String> cellName, HttpServletRequest request) {
		CsvWriter wr = null;
		try{
			String showfide=request.getParameter("showfide");
			wr =new CsvWriter(os,',',Charset.forName(Concest.ENCODING));  
			//-------------------写入主标题栏-----------------------------------------------
			os.write(new byte[]{(byte)0xEF,(byte)0xBB,(byte)0xBF});
			String nokatag_excel_title =  request.getParameter("nokatag_excel_title");
			if(nokatag_excel_title!=null && nokatag_excel_title.trim().length()>0)
				wr.writeRecord(new String[]{nokatag_excel_title});//写入标题
			//-------------------写入列标题栏------------------------------------------------
			List<String> titles = new ArrayList<String>();
			for (int s = 0; s < cellName.size(); s++) {
				if(isShowCell(showfide,s)){
					String tite = cellName.get(s);
					titles.add(tite);
				}
			}
	         wr.writeRecord(titles);//写入标题
			//--------------------写入数据--------------------------------------------------
			for(int r=0;r<row.size();r++){//行
				List<String> m=row.get(r);
				List<String> cell = new ArrayList<String>();
				for(int s = 0; s < m.size(); s++){//列
					if(isShowCell(showfide,s)){
						cell.add(m.get(s));
					}
				}
				wr.writeRecord(cell);
			}//行
		} catch (Exception e1) {
			BugInfoWrint.Print(e1);
		}finally{
			try{
				if (wr != null)
					wr.close();
				if (os != null)
					os.close();
			}catch (Exception e2) {
				BugInfoWrint.Print(e2);
			}
		}
	}
   //==============================Excel文件================================================================================
	public void outExcelFile(OutputStream os, List<List<String>> row,List<String> cellName, HttpServletRequest request) {
		WritableWorkbook wwb=null;
		WritableSheet ws=null;
		try{
			String showfide=request.getParameter("showfide");
			wwb = Workbook.createWorkbook(os);// 创建可写工作薄
			ws = wwb.createSheet("sheet1", 0);// 创建可写工作表
			//-------------------写入主标题栏-----------------------------------------------
			String nokatag_excel_title =  request.getParameter("nokatag_excel_title");
			int start=0;
			if(nokatag_excel_title!=null && nokatag_excel_title.trim().length()>0){
				ws.mergeCells(0, 0, showfide.split(",").length/2-1, 0);// 列总数
				Label labelCF0 = new Label(0, 0, nokatag_excel_title);// 写入主标题
				ws.addCell(labelCF0);// 将Label写入sheet中
				start=1;
			}
			//-------------------写入列标题栏------------------------------------------------
			int a=0;
			for (int s = 0; s < cellName.size(); s++) {
				if(isShowCell(showfide,s)){
					String tite = cellName.get(s);
					Label labelCF0i = new Label(a, start, tite);// 写入列标题
					ws.addCell(labelCF0i);// 将Label写入sheet中
					a++;
				}
			}
			//--------------------写入数据--------------------------------------------------
			for(int r=0;r<row.size();r++){//行
				List<String> m=row.get(r);
				int c=0;
				for(int s = 0; s < m.size(); s++){//列
					if(isShowCell(showfide,s)){
					//-------------写入--------------------------------------------------
						try {
							SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							Date date = bartDateFormat.parse(m.get(s));
							org.nokatag.jxl.write.DateTime labelDT = new org.nokatag.jxl.write.DateTime(c, r+2, date);
							ws.addCell(labelDT);
						} catch (Exception seb) {
							Label labelCF0i = new Label(c, r+2,m.get(s));// 写入标题
							ws.addCell(labelCF0i);// 将Label写入sheet中
						}
						c++;
					}
				}
			}//行
			//------------------------------------------------------------------------------
			wwb.write();
		} catch (Exception e1) {
			BugInfoWrint.Print(e1);
		}finally{
			try{
				if (wwb != null)
					wwb.close();
				if (os != null)
					os.close();
			}catch (Exception e2) {
				BugInfoWrint.Print(e2);
			}
		}
	}
	//========================PDF文件=============================================================
	public void outPDFFile(OutputStream os, List<List<String>> row,List<String> cellName, HttpServletRequest request) {
		Document document = null;
		try{
			//String wdname = request.getParameter("wdname");//通过request获取页面名为wdname的查询输入项
			document = new Document(PageSize.A4);
			PdfWriter. getInstance(document,os);
			document.open();
			BaseFont bfComic0= BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
			Font title_font = new Font(bfComic0, 16);
			Font body_font = new Font(bfComic0, 9);
			//---------------计算列数----------------------------------------------
			String showfide=request.getParameter("showfide");
			Integer CellCount= 0;
			{
				for (int s = 0; s < cellName.size(); s++) {
					if(isShowCell(showfide,s)){
						CellCount+=1;
					}
				}
			}
			//---------------大标题------------------------------------------------
			{
				String nokatag_excel_title =  request.getParameter("nokatag_excel_title");
				if(nokatag_excel_title!=null && nokatag_excel_title.trim().length()>0){
					Paragraph paragraph = new Paragraph(nokatag_excel_title,title_font);
					paragraph.setAlignment(Element.ALIGN_CENTER);
					paragraph.setSpacingAfter(20);
					document.add(paragraph);
				}
			}
			CellCount=CellCount<1?1:CellCount;
			PdfPTable table = new PdfPTable(CellCount);//建立表格
			table.setWidthPercentage(99);
			//--------------标题栏-------------------------------------------------
			{
				for (int s = 0; s < cellName.size(); s++) {
					if(isShowCell(showfide,s)){
						String tite = cellName.get(s);
						table.addCell(new Paragraph(tite,body_font));
					}
				}
			}
			//--------------内容-------------------------------------------------
			{
				for(int r=0;r<row.size();r++){//行
					List<String> m=row.get(r);
					for(int s = 0; s < m.size(); s++){//列
						if(isShowCell(showfide,s)){
							table.addCell(new Paragraph(m.get(s),body_font));
						}
					}
				}
			}
			document.add(table);
			//--------------写入日期---------------------------------------------
			{
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				Paragraph paragraph = new Paragraph(df.format(new Date()),body_font);
				paragraph.setAlignment(Element.ALIGN_RIGHT);
				paragraph.setSpacingAfter(20);
				document.add(paragraph);
			}
		}catch(Exception e1){
			BugInfoWrint.Print(e1);
		}finally{
			try{
				if (document!= null)
					document.close();
				if(os!=null)
					os.close();
			}catch (Exception e2) {
				BugInfoWrint.Print(e2);
			}
		}
	}
	
	/**
	 * 判断是否在显示列里面
	 * @param showcell
	 * @param cell
	 * @return
	 */
	private  Boolean isShowCell(String showcell,int cell){
		if(showcell==null)
			return false;
		if(showcell.indexOf(","+cell+",")!=-1)
			return true;
		return false;
	}

}
