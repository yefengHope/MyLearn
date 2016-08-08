package org.noka.function;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.nokatag.dbgrid.SubGrid;

public class TestGrid implements SubGrid {
	@SuppressWarnings({ "unused", "rawtypes" })
	public String getBody(Integer i, Map<String, Object> map,List<String> Cellname) {
		String datarow="";
		Integer row=i;//行号
		//---------取得行所有数据------------------------
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
		Map.Entry entry = (Map.Entry) it.next();
		String key = entry.getKey().toString();//列名
		String value = entry.getValue().toString();//值
		datarow+=key+"="+value+",";
		}
		datarow+="<br>";
		///--------取得所有列名------------
		for(String cell:Cellname){
		datarow+=","+cell;
		}
		String sn=datarow;
		return sn;
	}

}
