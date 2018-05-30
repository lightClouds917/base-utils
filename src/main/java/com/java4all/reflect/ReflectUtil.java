package com.java4all.reflect;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Author: momo
 * Date: 2018/5/24
 * Description:反射的工具类
 *
 * 利用反射获取指定对象的指定属性
 * 利用反射获取指定对象里面的指定属性
 * 利用反射设置指定对象的指定属性为指定的值
 * 两者属性名一致时，拷贝source里的属性到dest里
 * 调用Getter方法
 * 直接调用对象方法, 无视private/protected修饰符
 * 循环向上转型, 获取对象的DeclaredMethod
 * 调用set方法把值set到对象当中
 * 通过class类型获取获取对应类型的值
 * 将对象中所有属性值为空字符串的，转换为null
 */
public class ReflectUtil {
	/**
	 * 利用反射获取指定对象的指定属性
	 * @param obj
	 *            目标对象
	 * @param fieldName
	 *            目标属性
	 * @return 目标属性的值
	 */
	public static Object getFieldValue(Object obj, String fieldName) {
		Object result = null;
		Field field = ReflectUtil.getField(obj, fieldName);
		if (field != null) {
			field.setAccessible(true);
			try {
				result = field.get(obj);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 利用反射获取指定对象里面的指定属性
	 * 
	 * @param obj
	 *            目标对象
	 * @param fieldName
	 *            目标属性
	 * @return 目标字段
	 */
	private static Field getField(Object obj, String fieldName) {
		Field field = null;
		for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz
				.getSuperclass()) {
			try {
				field = clazz.getDeclaredField(fieldName);
				break;
			} catch (NoSuchFieldException e) {
				// 这里不用做处理，子类没有该字段可能对应的父类有，都没有就返回null。
			}
		}
		return field;
	}

	/**
	 * 利用反射设置指定对象的指定属性为指定的值
	 * 
	 * @param obj
	 *            目标对象
	 * @param fieldName
	 *            目标属性
	 * @param fieldValue
	 *            目标值
	 */
	public static void setFieldValue(Object obj, String fieldName,
									 String fieldValue) {
		Field field = ReflectUtil.getField(obj, fieldName);
		if (field != null) {
			try {
				field.setAccessible(true);
				field.set(obj, fieldValue);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
     * 两者属性名一致时，拷贝source里的属性到dest里
     * 
     * @return void
	 * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @SuppressWarnings("unchecked")
    public static void copyPorperties(Object dest, Object source) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class srcCla = source.getClass();
        Field[] fsF = srcCla.getDeclaredFields();
 
        for (Field s : fsF)
        {
            String name = s.getName();
            Object srcObj = invokeGetterMethod(source, name);
            try
            {
                BeanUtils.setProperty(dest, name, srcObj);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            
        }
    }
    
    /**
     * 调用Getter方法.
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static Object invokeGetterMethod(Object target, String propertyName) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        String getterMethodName = "get" + StringUtils.capitalize(propertyName);
        return invokeMethod(target, getterMethodName, new Class[] {},
                new Object[] {});
    }
    
    /**
     * 直接调用对象方法, 无视private/protected修饰符.
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static Object invokeMethod(final Object object,
									  final String methodName, final Class<?>[] parameterTypes,
									  final Object[] parameters) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method method = getDeclaredMethod(object, methodName, parameterTypes);
        if (method == null)
        {
            throw new IllegalArgumentException("Could not find method ["
                    + methodName + "] parameterType " + parameterTypes
                    + " on target [" + object + "]");
        }
 
        method.setAccessible(true);
       return method.invoke(object, parameters);
    }
    
    /**
     * 循环向上转型, 获取对象的DeclaredMethod.
     * 
     * 如向上转型到Object仍无法找到, 返回null.
     */
    protected static Method getDeclaredMethod(Object object, String methodName,
											  Class<?>[] parameterTypes)
    {
        Assert.notNull(object, "object不能为空");
 
        for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass
                .getSuperclass())
        {
            try{
                return superClass.getDeclaredMethod(methodName, parameterTypes);
            }
            catch (NoSuchMethodException e)
            {// NOSONAR
                // Method不在当前类定义,继续向上转型
            }
        }
        return null;
    }


	/**
	 * 调用set方法把值set到对象当中
	 * @param obj 对象
	 * @param clazz 对象的class
	 * @param fieldName 对象的某个属性
	 * @param typeClass	此属性的type 如：User.class.getDeclaredField(fieldName).getType()
	 * @param value 需要set的值
	 */
	public void setValue(Object obj, Class<?> clazz, String fieldName, Class<?> typeClass, Object value){
		String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		try {
			Method method = clazz.getDeclaredMethod(methodName, new Class[]{typeClass});
			method.invoke(obj, new Object[]{getClassTypeValue(typeClass, value)});
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}

	/**
	 * 通过class类型获取对应类型的值
	 * @param typeClass class类型
	 * @param value 值
	 * @return Object
	 */
	private  Object getClassTypeValue(Class<?> typeClass, Object value){
		if(typeClass == int.class  || value instanceof Integer){
			if(null == value){
				return 0;
			}
			return value;
		}else if(typeClass == short.class){
			if(null == value){
				return 0;
			}
			return value;
		}else if(typeClass == byte.class){
			if(null == value){
				return 0;
			}
			return value;
		}else if(typeClass == double.class){
			if(null == value){
				return 0;
			}
			return value;
		}else if(typeClass == long.class){
			if(null == value){
				return 0;
			}
			return value;
		}else if(typeClass == String.class){
			if(null == value){
				return "";
			}
			return value;
		}else if(typeClass == boolean.class){
			if(null == value){
				return true;
			}
			return value;
		}else if(typeClass == BigDecimal.class){
			if(null == value){
				return new BigDecimal(0);
			}
			return new BigDecimal(value+"");
		}else {
			return typeClass.cast(value);
		}

	}


	public static void main(String[] args){
		User user = new User(13,"",23,"浙江省杭州市西湖区","浙江省","",new Date(),null);
	    dealWithNull(user);
	}

	/**
	 * 将对象中所有属性值为空字符串的，转换为null
	 * 常用于接口层的对象参数初步处理
	 * @param obj
	 */
	public static Object dealWithNull(Object obj){
		try {
			Field[] fields = obj.getClass().getDeclaredFields();
			List<Field> stringFieldList =
					Arrays.stream(fields).filter(field -> field.getGenericType().getTypeName().equals("java.lang.String")).collect(Collectors.toList());

			for (int i = 0 , length = stringFieldList.size(); i < length; i++) {
				stringFieldList.get(i).setAccessible(true);
				if(StringUtils.trim(stringFieldList.get(i).get(obj).toString()).equals("")){
					stringFieldList.get(i).set(obj,null);
				}
			}
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return obj;
	}

}

