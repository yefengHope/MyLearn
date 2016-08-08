package org.noka.pro;

import java.util.ArrayList;
import java.util.List;

import org.noka.item.UserItem;
import org.nokatag.system.DataUtil;
import org.nokatag.system.DataWhere;

public class SysPro {
	/**
	 * 为用户分配角色
	 * @param roleids
	 * @param usid
	 * @return
	 */
	public static String userproledo(String roleids,String usid){
		if(roleids!=null && usid!=null){
			List<DataWhere> userdwlist = new ArrayList<DataWhere>();
			userdwlist.add(new DataWhere("usid",usid));//用户名
			UserItem userItem = (UserItem)DataUtil.object("from UserItem where usid=:usid",userdwlist);
			userItem.setUsrole(roleids);
			if(DataUtil.update(userItem))
				return "1";//分配成功
			else
				return "0";
		}
		return "0";//分配失败
	}
}
