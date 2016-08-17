package org.noka.dbutil;


import org.apache.struts2.ServletActionContext;
import org.noka.function.BaseUtil;
import org.noka.item.LogItem;
import org.noka.item.UserItem;
import org.nokatag.system.DataUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * <p>@Title 日志写入接口 </p>
 * <p>@Description 日志写入接口</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author Hom</p>
 * <p>@date  2016/8/17 创建日期</p>
 * <p>tanghom@qq.com</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public class NLogUtil {

    /**
     * 日志写入
     *
     * @param text
     */
    public static void log(String text) {
        HttpServletRequest request = ServletActionContext.getRequest();
        UserItem user = (UserItem) request.getSession().getAttribute("user");//获取用户信息
        String userAgent = request.getHeader("User-Agent");//获取浏览器信息

        LogItem logItem = new LogItem();
        logItem.setLotext(text);
        logItem.setLoaddr(BaseUtil.getIp());
        logItem.setLodate(new Date());
        logItem.setLouserid(user.getUsid());
        logItem.setLobinfo(userAgent);
        DataUtil.insert(logItem);//插入日志

    }

    /**
     * 日志写入
     *
     * @param cla
     * @param text
     */
    public static void log(Class<?> cla, String text) {
        NLogUtil.log(cla.getName()+":"+text);
    }
}
