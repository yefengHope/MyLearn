package org.noka.function;

import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WangXiong on 2016/5/13.
 */
public class BaseUtil {

    /**
     * 逗号隔开格式的字符串转list
     * @param ids 格式 逗号隔开
     * @return
     */
    @SuppressWarnings({ "unchcked", "rawtypes" })
    public static <T>List<T> getListByIds(String ids){
        List<T> list = new ArrayList<T>();
        String d[] = ids.split(",");
        for (int i = 0; i < d.length; i++) {
            if(d[i]!=null &&  !"".equals(d[i])){
                list.add((T)d[i]);
            }
        }
        return list;
    }

    /**
     * 根据 数组格式的字符串 获取map
     * @param splitStr 分割符
     * @param variables 数组格式的字符串
     * @return
     */
    public static Map<String,Object> getMapByArr(String splitStr,String variables){
        Map<String,Object> map = new HashMap<String, Object>();
        String[] varr =variables.split(",");
        for(int i = 0 ; i < varr.length ; i++){
            String[] mapstr = varr[i].split(splitStr);
            map.put(mapstr[0],mapstr[1]);
        }
        return map;
    }
    /**
     * 返回HttpServletRequest用户的IP地址
     *
     * @return
     */
    public static String getIp() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
