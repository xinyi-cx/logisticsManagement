package com.ruoyi.common.utils.bean;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Bean 工具类
 * 
 *
 */
public class BeanUtils extends org.springframework.beans.BeanUtils
{
    /** Bean方法名中属性名开始的下标 */
    private static final int BEAN_METHOD_PROP_INDEX = 3;

    /** * 匹配getter方法的正则表达式 */
    private static final Pattern GET_PATTERN = Pattern.compile("get(\\p{javaUpperCase}\\w*)");

    /** * 匹配setter方法的正则表达式 */
    private static final Pattern SET_PATTERN = Pattern.compile("set(\\p{javaUpperCase}\\w*)");

    /**
     * Bean属性复制工具方法。
     * 
     * @param dest 目标对象
     * @param src 源对象
     */
    public static void copyBeanProp(Object dest, Object src)
    {
        try
        {
            copyProperties(src, dest);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 获取对象的setter方法。
     * 
     * @param obj 对象
     * @return 对象的setter方法列表
     */
    public static List<Method> getSetterMethods(Object obj)
    {
        // setter方法列表
        List<Method> setterMethods = new ArrayList<Method>();

        // 获取所有方法
        Method[] methods = obj.getClass().getMethods();

        // 查找setter方法

        for (Method method : methods)
        {
            Matcher m = SET_PATTERN.matcher(method.getName());
            if (m.matches() && (method.getParameterTypes().length == 1))
            {
                setterMethods.add(method);
            }
        }
        // 返回setter方法列表
        return setterMethods;
    }

    /**
     * 获取对象的getter方法。
     * 
     * @param obj 对象
     * @return 对象的getter方法列表
     */

    public static List<Method> getGetterMethods(Object obj)
    {
        // getter方法列表
        List<Method> getterMethods = new ArrayList<Method>();
        // 获取所有方法
        Method[] methods = obj.getClass().getMethods();
        // 查找getter方法
        for (Method method : methods)
        {
            Matcher m = GET_PATTERN.matcher(method.getName());
            if (m.matches() && (method.getParameterTypes().length == 0))
            {
                getterMethods.add(method);
            }
        }
        // 返回getter方法列表
        return getterMethods;
    }

    /**
     * 检查Bean方法名中的属性名是否相等。<br>
     * 如getName()和setName()属性名一样，getName()和setAge()属性名不一样。
     * 
     * @param m1 方法名1
     * @param m2 方法名2
     * @return 属性名一样返回true，否则返回false
     */

    public static boolean isMethodPropEquals(String m1, String m2)
    {
        return m1.substring(BEAN_METHOD_PROP_INDEX).equals(m2.substring(BEAN_METHOD_PROP_INDEX));
    }

    /**
     * 根据字段名称存值
     * @param entity 实体类
     * @param fieldName 字段名称
     * @param value 值
     */
    public static void setAttribute(Object entity,String fieldName, Object value) {
        try {
            Field f = entity.getClass().getDeclaredField(fieldName);
            f.setAccessible(true);
            final Class<?> type = f.getType();
            if("java.math.BigDecimal".equals(type.getName())){
                f.set(entity, new BigDecimal(value.toString()));
            } else if ("java.lang.Integer".equals(type.getName())) {
                f.set(entity, new Integer(value.toString()));
            } else {
                f.set(entity, value);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据字段名称取值
     * @param entity 实体类
     * @param fieldName 字段名称
     */
    public static String getAttribute(Object entity,String fieldName) {
        String r = "";
        try {
            Field f = entity.getClass().getDeclaredField(fieldName);
            f.setAccessible(true);
            r =  f.get(entity).toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return r;
    }

//    https://blog.csdn.net/qq_40016813/article/details/124407414

//    public static void main(String[] args) {
//        ShopApplyRecord shopApplyRecord = new ShopApplyRecord();
//        setAttribute(shopApplyRecord,"shopApplyRecordId",1L);
//        setAttribute(shopApplyRecord,"actualName","王大力");
//        setAttribute(shopApplyRecord,"identityCard","5120");
//        setAttribute(shopApplyRecord,"type",3);
//        System.out.println(shopApplyRecord);
//
//        ShopApplyRecord shopApplyRecord2 = new ShopApplyRecord();
//        setAttribute(shopApplyRecord2,"shopApplyRecordId",1L);
//        setAttribute(shopApplyRecord2,"actualName","王大力");
//        setAttribute(shopApplyRecord2,"identityCard","51210");
//        setAttribute(shopApplyRecord2,"type",1);
//        System.out.println(shopApplyRecord2);
//        final String s = contrastEntity(shopApplyRecord, shopApplyRecord2);
//        System.out.println(s);
//        //输出结果[{"newValue":["51210"],"type":["identityCard"],"oldValue":["5120"]}
//        //,{"newValue":[1],"type":["type"],"oldValue":[3]}]  //如果其中一个字段值为空则不能被对比
//    }

}
