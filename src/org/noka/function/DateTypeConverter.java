package org.noka.function;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.util.StrutsTypeConverter;

/**
 * @author jolestar
 * 
 */
public class DateTypeConverter extends StrutsTypeConverter {

	private static final Logger log = Logger.getLogger(DateTypeConverter.class);
	// 暂时只考虑这几种日期格式
	public static final DateFormat[] ACCEPT_DATE_FORMATS = {
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
			new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"),
			new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss"),

			new SimpleDateFormat("yyyy-MM-dd HH:mm"),
			new SimpleDateFormat("yyyy/MM/dd HH:mm"),
			new SimpleDateFormat("yyyy年MM月dd日 HH:mm"),

			new SimpleDateFormat("yyyy-MM-dd"),
			new SimpleDateFormat("yyyy/MM/dd"),
			new SimpleDateFormat("yyyy年MM月dd日"),
			new SimpleDateFormat("HH:mm:ss"),
			new SimpleDateFormat("HH:mm")};

	/**  
     *   
     */
	public DateTypeConverter() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.struts2.util.StrutsTypeConverter#convertFromString(java.util
	 * .Map, java.lang.String[], java.lang.Class)
	 */
	@SuppressWarnings("rawtypes")
	public Object convertFromString(Map context, String[] values, Class toClass) {
		if (values[0] == null || values[0].trim().equals(""))
			return null;
		for (DateFormat format : ACCEPT_DATE_FORMATS) {
			try {
				return format.parse(values[0]);
			} catch (ParseException e) {
				continue;
			} catch (RuntimeException e) {
				continue;
			}
		}
		log.debug("can not format date string:" + values[0]);

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.struts2.util.StrutsTypeConverter#convertToString(java.util
	 * .Map, java.lang.Object)
	 */
	@SuppressWarnings("rawtypes")
	public String convertToString(Map context, Object o) {
		if (o instanceof Date) {
			for (DateFormat format : ACCEPT_DATE_FORMATS) {
				try {
					return format.format((Date) o);
				} catch (RuntimeException e) {
				}
			}
			return "";
		}
		return "";
	}

}