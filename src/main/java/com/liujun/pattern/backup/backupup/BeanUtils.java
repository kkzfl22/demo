package com.liujun.pattern.backup.backupup;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BeanUtils
{
	/**
	 * 获取属性值
	 * 
	 * @param obj
	 *            当前的bean对象信息
	 * @return 获取到的键值对信息
	 */
	public static Map<String, Object> getBeanValue(Object obj)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			// 通过工具类获得bean对象
			BeanInfo beaninfo = Introspector.getBeanInfo(obj.getClass());

			// 获取属性描述
			PropertyDescriptor[] propertys = beaninfo.getPropertyDescriptors();

			// 遍历所有属性
			for (PropertyDescriptor propertyItem : propertys)
			{
				// 属性名字
				String name = propertyItem.getName();
				// 属性的方法
				Method getMethod = propertyItem.getReadMethod();
				// 获取属性值
				Object value = getMethod.invoke(obj, new Object[]
				{});

				// 检查是否为class
				if (!"class".equalsIgnoreCase(name))
				{
					map.put(name, value);
				}
			}

		} catch (IntrospectionException e)
		{
			e.printStackTrace();
		} catch (IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return map;
	}

	public static void setBeanValue(Object obj, Map<String, Object> parMap)
	{
		try
		{
			// 通过工具类获得bean对象
			BeanInfo beaninfo = Introspector.getBeanInfo(obj.getClass());

			// 获取属性描述
			PropertyDescriptor[] propertys = beaninfo.getPropertyDescriptors();

			// 遍历所有属性
			for (PropertyDescriptor propertyItem : propertys)
			{
				// 属性名字
				String name = propertyItem.getName();

				// 检查是否包含属性
				if (parMap.containsKey(name))
				{
					// 写属性的方法
					Method setMethod = propertyItem.getWriteMethod();
					// 获取属性值
					setMethod.invoke(obj, new Object[]
					{ parMap.get(name) });
				}
			}

		} catch (IntrospectionException e)
		{
			e.printStackTrace();
		} catch (IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
