package org.noka.function;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ReflectParseUtil {

	/**
	 * 将id 数组字符串 获取list
 	 * @param ids
	 * @return
	 */
	public static <T> List<T> getOperEntityList(String pid,String ids,Class<?> fromClass){
		List<T> list = new ArrayList<T>();
		try{
			List<Object> idsl = BaseUtil.getListByIds(ids);
			Object entity = null;
			for(Object id : idsl){
			entity = fromClass.newInstance();
//			entity = copyBean(obj,entity);
			invokeSet(entity,pid,(String)id);
			list.add((T)entity);
			}
		}catch(Exception e ){
			e.printStackTrace();
		}
		return list;
	}
	private static Object copyBean(Object from,Object ints) throws Exception {
		// 取得拷贝源对象的Class对象
		Class<?> fromClass = from.getClass();
		// 取得拷贝源对象的属性列表
		Field[] fromFields = fromClass.getDeclaredFields();
		// 取得拷贝目标对象的Class对象
		for (Field fromField : fromFields) {
			// 设置属性的可访问性
			fromField.setAccessible(true);
			// 将拷贝源对象的属性的值赋给拷贝目标对象相应的属性
			fromField.set(ints, fromField.get(from));
		}
		return ints;
	}

	/**
	 * 将源对象不为null的值赋值给目标对象
	 * @param yobj 源对象
	 * @param mboj 目标对象
     */
	public static void copyPropertyIsNotNull(Object yobj,Object mboj){
		Class<?> clzz = yobj.getClass();
		Field[] fields = clzz.getDeclaredFields();
		for(Field fid : fields){
			Object value = invokeGet(yobj,fid.getName());
			if(value != null){//将不为NULL的字段 赋值给mboj 目标对象
				invokeSet(mboj,fid.getName(),value);
			}
		}
	}

	public static void paramsDispose(Object obj){
		Class<?> clzz = obj.getClass();
		Field[] fields =  clzz.getDeclaredFields();

	}

	/**
	 * 取指定属性的值
	 * @param entity 源实体
	 * @param propertyName 属性名
     * @return
     */
	public static Object invokeGet(Object entity,String propertyName) {
		try {
		   propertyName = propertyName.substring(0,1).toUpperCase()+propertyName.substring(1);
           Method method = entity.getClass().getMethod("get"+propertyName);
           Object value= method.invoke(entity);  
           return value;
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
		return null;
    }

	/**
	 * 给对象指定属性赋值
	 * @param entity 源实体
	 * @param propertyName 属性名
	 * @param value	 值
     */
	public static void invokeSet(Object entity,String propertyName,Object value) {  
		try {
			Field[] fields = entity.getClass().getDeclaredFields();
			for(Field field :fields){
				if(propertyName.equals(field.getName())){
					propertyName = propertyName.substring(0,1).toUpperCase()+propertyName.substring(1);
					Method method = entity.getClass().getMethod("set"+propertyName,field.getType());
					method.invoke(entity, value);  
				}
			}
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }
}
