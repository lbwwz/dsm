package com.dsm.common.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

/**
 * 反射的 工具类
 *
 * @author lbwwz
 */
public class ReflectionUtils {

    /**
     * 通过反射, 获得定义 Class 时声明的父类的泛型参数的类型
     * 如: public EmployeeDao extends BaseDao<Employee, String>
     *
     * @param clazz 类的Class对象(EmployeeDao)
     * @param index 获取泛型参数的索引,要获取第几个参数，如上述例子，当index为1的时候，获取的泛型类型是java.lang.String
     * @return 泛型的参数类对象
     */
    public static Class<?> getSuperClassGenricType(Class<?> clazz, int index) {
        Type genType = clazz.getGenericSuperclass();

        // 判断获得的父类类型是不是带参数类型（带泛型）的
        if (!(genType instanceof ParameterizedType)) {
            /*
             * 若方法为不带泛型的
			 */
            return null;
        }

        // 是带泛型的，则获取泛型的参数类型数组
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index < 0 || index > params.length) {
            return null;
        }

        if (params[index] instanceof Class) {
            return (Class<?>) params[index];
        }

        return Object.class;
    }

    /**
     * 通过反射, 获得 Class 定义中声明的父类的泛型参数类型（仅有一个参数类型）
     * 如: public EmployeeDao extends BaseDao<Employee>
     *
     * @param clazz 类的Class对象
     * @return 泛型的参数类对象
     */
    @SuppressWarnings("unused")
    public static Class<?> getSuperGenericType(Class<?> clazz) {
        return getSuperClassGenricType(clazz, 0);
    }

    /**
     * 循环向上转型, 获取某个类中的 Method(涵盖父类的所有可使用的方法)
     *
     * @param clazz          某个类的Class对象
     * @param methodName     方法名
     * @param parameterTypes 方法的形参
     * @return 指定获取的method对象
     */
    public static Method getMethod(Class<?> clazz, String methodName, Class<?>... parameterTypes) {

        // 循环向上转型，直到Object类
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                return clazz.getDeclaredMethod(methodName, parameterTypes);
            } catch (NoSuchMethodException | SecurityException e) {
                // 不做处理，向上转型
            }
        }
        // 循环结束没有找到，返回空
        return null;
    }

    /**
     * 根据全类名获取该类的 Method(涵盖父类的所有可使用的方法)
     *
     * @param className      类的全类名
     * @param methodName     方法名
     * @param parameterTypes 方法的参数
     * @return 方法对象
     * @throws ClassNotFoundException 没有相应的class类存在
     */
    @SuppressWarnings("unused")
    public static Method getMethod(String className, String methodName, Class<?>... parameterTypes)
            throws ClassNotFoundException {
        return getMethod(Class.forName(className), methodName, parameterTypes);
    }

    /**
     * 获取具体对象的 Method(涵盖父类的所有可使用的方法)
     *
     * @param obj            对象
     * @param methodName     方法名称
     * @param parameterTypes 参数类型
     * @return 方法对象
     */
    public static Method getMethod(Object obj, String methodName, Class<?>... parameterTypes) {
        return getMethod(obj.getClass(), methodName, parameterTypes);
    }

    /**
     * 直接调用某个对象的某个方法, 而忽略修饰符(private, protected)
     *
     * @param obj            要调用方法的对象
     * @param methodName     方法名称
     * @param parameterTypes 传入参数的类型
     * @param parameters     传入的参数
     * @return 调用的方法的的返回值
     * @throws InvocationTargetException method对象不存在
     */
    public static Object invokeMethod(Object obj, String methodName, Class<?>[] parameterTypes, Object... parameters)
            throws InvocationTargetException {
        // 获取对象的方法
        Method method = getMethod(obj, methodName, parameterTypes);

        if (method != null) {
            method.setAccessible(true);

            try {
                return method.invoke(obj, parameters);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // 抛出方法不存在异常信息
            throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + obj + "]");
        }
        return null;
    }

    /**
     * 循环向上转型, 根据指定类的类对象获取指定类的 Field
     *
     * @param clazz     获取field的类
     * @param fieldName 属性名称
     * @return 具体的属性对象
     */
    public static Field getField(Class<?> clazz, String fieldName) {
        // 循环向上转型，直到Object类
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                return clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException | SecurityException e) {
                // 向上转型,不作处理
            }
        }
        return null;
    }

    /**
     * 根据指对象获取对象所在类的 Field
     *
     * @param obj       对象
     * @param fieldName 属性名称
     * @return 具体的属性对象
     */
    public static Field getField(Object obj, String fieldName) {
        return getField(obj.getClass(), fieldName);
    }

    /**
     * 根据全类名 获取对应类的 Field
     *
     * @param className 类的全类名
     * @param fieldName 属性名称
     * @return 具体的属性对象
     * @throws ClassNotFoundException 全类名对应的类不存在
     */
    @SuppressWarnings("unused")
    public static Field getField(String className, String fieldName) throws ClassNotFoundException {
        return getField(Class.forName(className), fieldName);
    }

    /**
     * 设置对象属性值, 忽略 private/protected 修饰符, 也不经过 setter
     *
     * @param obj       对象
     * @param fieldName 属性名称
     * @param value     属性值
     */
    @SuppressWarnings("unused")
    public static void setFieldValue(Object obj, String fieldName, Object value) {
        // 查询获取对象的属性值
        Field field = getField(obj, fieldName);
        if (field != null) {
            field.setAccessible(true);

            try {
                field.set(obj, value);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }

        } else {
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + obj + "]");
        }

    }

    /**
     * 获取某个类中指定方法的指定注解
     *
     * @param annotationClass 注解类的 Class 对象
     * @param clazz           使用注解的类
     * @param methodName      使用注解的方法
     * @param parameterTypes  方法对应的参数
     * @return 返回对应的注解类的对象，若不存在，则返回空值: java.lang.annotation.Annotation[]
     */
    @SuppressWarnings("unused")
    public static Annotation getAnnotationFromMethod(Class<? extends Annotation> annotationClass, Class<?> clazz,
                                                     String methodName, Class<?>... parameterTypes) {
        Method method = getMethod(clazz, methodName, parameterTypes);

        // 判断某个注解是否存在于特定的方法上
        assert method != null;
        if (method.isAnnotationPresent(annotationClass)) {
            // AnnotatedElement接口中的方法getAnnotation(),获取传入注解类型的注解
            return method.getAnnotation(annotationClass);
        }
        return null;
    }

    /**
     * 获取指定类中某个方法的全部注解
     *
     * @param clazz          类的Class对象
     * @param methodName     使用注解的方法
     * @param parameterTypes 方法对应的参数列表
     * @return 某个方法的一组注解: java.lang.annotation.Annotation[]
     */
    @SuppressWarnings("unused")
    public static Annotation[] getAnnotationListFromMethod(Class<?> clazz, String methodName,
                                                           Class<?>... parameterTypes) {

        Method m = getMethod(clazz, methodName, parameterTypes);
        if (m == null) {
            return null;
        }
        return m.getDeclaredAnnotations();
    }

    /**
     * 获取指定类的某个属性的指定注解
     *
     * @param annotationClass 注解类的 Class 对象
     * @param clazz           获取注解的操作类
     * @param fieldName       属性的名称
     * @return java.lang.annotation.Annotation
     */
    @SuppressWarnings("unused")
    public static Annotation getAnnotationFromField(Class<? extends Annotation> annotationClass, Class<?> clazz,
                                                    String fieldName) {
        Field field = getField(clazz, fieldName);

        if (field != null) {
            if (field.isAnnotationPresent(annotationClass)) {
                return field.getAnnotation(annotationClass);
            }
        }
        return null;
    }

    /**
     * 获取指定类的某个属性的全部注解
     *
     * @param clazz     获取注解的操作类
     * @param fieldName 属性名称
     * @return java.lang.annotation.Annotation[]
     */
    @SuppressWarnings("unused")
    public static Annotation[] getAnnotationListFromField(Class<?> clazz, String fieldName) {

        Field f = getField(clazz, fieldName);
        if (f == null) {
            return null;
        }
        return f.getDeclaredAnnotations();
    }

}