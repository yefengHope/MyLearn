package org.noka.printItem;

import javax.servlet.http.HttpServletRequest;

import org.noka.print.PrintUtil;

public class TestPrint implements PrintUtil{

	public String GetPrint(HttpServletRequest request) {
		StringBuffer xml=new StringBuffer();
		xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		xml.append("<roots>\n");
		xml.append("<djs>\n");
		xml.append("<dj>\n");
		for(int i=0;i<5;i++){
			xml.append("<dj-body>\n");
			xml.append("<dj-n1>状态1</dj-n1>\n");
			xml.append("<dj-n2>状态1</dj-n2>\n");
			xml.append("<dj-n3>状态1</dj-n3>\n");
			xml.append("<dj-n4>状态1</dj-n4>\n");
			xml.append("<dj-n5>状态1</dj-n5>\n");
			xml.append("<dj-n6>状态1</dj-n6>\n");
			xml.append("<dj-n7>状态1</dj-n7>\n");
			xml.append("<dj-n8>状态1</dj-n8>\n");
			xml.append("<dj-n9>状态1</dj-n9>\n");
			xml.append("</dj-body>\n");
		}
		xml.append("</dj>\n");
		xml.append("</djs>\n");
		xml.append("</roots>\n");
		return xml.toString();
	}

}
