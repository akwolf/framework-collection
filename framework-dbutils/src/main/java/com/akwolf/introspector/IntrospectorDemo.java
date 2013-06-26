package com.akwolf.introspector;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

// Introspector ---- getBeanInfo(BeanInfo) --->PropertyDescriptor
public class IntrospectorDemo{
	public static void main(String[] args)  throws Exception {
		Student stu = new Student() ;
		
		//1、 Introspector 取得BeanInfo
		BeanInfo beanInfo = Introspector.getBeanInfo(Student.class, Object.class) ;
		
		//2、取得PropertyDescriptor
		PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors() ;
		
		stu.setAge(15) ;
		
		// 3、descriptors取得setter/getter方法
		for (PropertyDescriptor propertyDescriptor : descriptors) {
			System.out.println(propertyDescriptor.getName());
			//System.out.println(propertyDescriptor.getDisplayName());
			//System.out.println(propertyDescriptor.getShortDescription());
			System.out.println(propertyDescriptor.getValue(propertyDescriptor.getName()));
			if("age".equals(propertyDescriptor.getName())){
				Method method = propertyDescriptor.getWriteMethod() ;
				method.invoke(stu, 12) ;
			}
		}
		System.out.println(stu.getAge());
	}

}
