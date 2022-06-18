package com.zkyc.nalwallet.sdk.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


//request参数获取转map，对象与map互转
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MyMapUtil {

	/**
	 * 方法描述:实现bean转换为map集合<br/>
	 * 注意:实体类必须要有set方法,否则不能转换
	 *
	 *            obj
	 * @return Map<String, String>
	 */
	public static Map<String, Object> getValueMap(Object obj) {
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] fields = obj.getClass().getDeclaredFields();
		Field[] declaredFields = obj.getClass().getSuperclass().getDeclaredFields();
		findFields(map,fields,obj);
		findFields(map,declaredFields,obj);
		return map;
	}

	private static void findFields(Map<String, Object> map, Field[] fields, Object obj){
		for (int i = 0, len = fields.length; i < len; i++) {
			String varName = fields[i].getName();
			try {
				// 获取原来的访问控制权限
				boolean accessFlag = fields[i].isAccessible();
				// 修改访问控制权限
				fields[i].setAccessible(true);
				// 获取在对象f中属性fields[i]对应的对象中的变量
				Object o = fields[i].get(obj);
				if (o != null)
					map.put(varName, o.toString());
				// 恢复访问控制权限
				fields[i].setAccessible(accessFlag);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}





	// 日期类型的转换
	private static SimpleDateFormat simpleDateFormate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static Date convertSTD(String date) {
		try {
			return simpleDateFormate.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String convertDTS(Date date) {
		return simpleDateFormate.format(date);
	}

	/**
	 * 无用 将一个 Map 对象转化为一个 JavaBean
	 * 
	 * @param type
	 *            要转化的类型
	 * @param map
	 *            包含属性值的 map
	 * @return 转化出来的 JavaBean 对象
	 * @throws IntrospectionException
	 *             如果分析类属性失败
	 * @throws IllegalAccessException
	 *             如果实例化 JavaBean 失败
	 * @throws InstantiationException
	 *             如果实例化 JavaBean 失败
	 * @throws InvocationTargetException
	 *             如果调用属性的 setter 方法失败
	 * 
	 *
	 */
	public static Object convertMap(Class type, Map map) throws Exception {
		BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
		Object obj = type.newInstance(); // 创建 JavaBean 对象

		// 给 JavaBean 对象的属性赋值
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			if (map.containsKey(propertyName)) {
				// 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
				Object value = null;
				try {
					value = map.get(propertyName);
				} catch (Exception e) {
					e.printStackTrace();
				}
				Object[] args = new Object[1];
				args[0] = value;
				descriptor.getWriteMethod().invoke(obj, args);
			}
		}
		return obj;
	}

	/**
	 * test main方法中要想使用log4j必须加载属性配置文件,才可以打印
	 */
	public static void main(String[] args) {
		Map<String, Object> parame = new HashMap<String, Object>();
		parame.put("u_userid", "001");
		parame.put("u_loginUser", "Kill");
		parame.put("u_password", "izhonghong");
		parame.put("u_createTime", "admin0.");
		// t_sys_User user = getBean(parame, t_sys_User.class);
		//
		// Map<String,Object> map=getValueMap(user);
		// //main方法中要想使用log4j必须加载属性配置文件,才可以打印
		// PropertyConfigurator.configure("E:\\myproject\\pmserver\\config\\property\\log4j.properties");
		// logger.debug(user.toString());
		//
		// logger.debug(map.get("u_loginUser"));

	}
}
