package org.noka.constvar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConstVar {
	
	public static String CONTXMLPATH=null;//xml基础路径
	public static Map<String,String> XMLCONST=new HashMap<String,String>();//xml配置项
	public static String ROOTPATH=null;//系统实际根目录
	public static String SKINPATH=null;//皮肤文件路径
	public static String SKINPREFIX="nkskins/";//皮肤配置前缀
	@SuppressWarnings("rawtypes")
	public static Map<String,Map<String,Map>> LANGUAGES=new HashMap<String,Map<String,Map>>();
	public static Map<String,Map<String,String>> LANGUAGES_STR=new HashMap<String,Map<String,String>>();
	public static String PASSKEY="n%df*1^j&*~230+98_4=6+l(*gfdl~*erLK";//加密密钥
	public static String NOSESSION=null;//不需要session验证的路径
	public static Boolean CACHEFILE=true;//是否缓存文件
	public static Map<String,byte[]> FILE_CACHE=new HashMap<String,byte[]>();//文件缓存
	public static Map<String,Map<String,String>> PRINTS=new HashMap<String,Map<String,String>>();//印
	public static List<Map<String,String>> DBTABLE=new ArrayList<Map<String,String>>();//数据过率
	public static Map<Long,String> URLS=new HashMap<Long, String>();//所有特定用户可访问的URL
	public static String PUBLIC_URLS=null;//需要session才能访问的共公url
	public static Map<String,Long> FILE_LAST_UPDATE_TIME=new HashMap<String, Long>();//缓存文件最后修改时间
	public static Map<Long,List<Map<String,String>>> BUTTONS= new HashMap<Long,List<Map<String,String>>>();//按钮权限每一个list为一条记录
	public static Map<String,String> CACHE_FILES=new HashMap<String,String>();//浏览器缓存
	public static int BOX_SERVER_PORT = 9001;//tcp服务端口
	public static int SERVER_MAIN_ID = 1;//服务器主版本号
	public static int SERVER_SUB_ID=2;//服务器次版本号
	
	public final static String[] DBTABLE_SELECT = new String[]{
		"{value:'1',label:'$[org.noka.sys.alldept]'}",
		"{value:'2',label:'$[org.noka.sys.thedept]'}",
		"{value:'3',label:'$[org.noka.sys.thesubdept]'}",
		"{value:'4',label:'$[org.noka.sys.usermy]'}",
		"{value:'5',label:'$[org.noka.sys.appdept]'}"
	};
	@SuppressWarnings("serial")
	public final static List<String> FILE_EXIT=new ArrayList<String>(){//文件过滤器
		{
			 add(".css");
			 add(".js");
			 add(".jpg");
			 add(".jpeg");
			 add(".png");
			 add(".gif");
			 add(".htm");
			 add(".html");
			 add(".swf");
		}
	};
}
