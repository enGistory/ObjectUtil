package util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 默认obj跑龙套
 *
 * @author 懒惰的小王
 * @date 2021/08/02
 */
public class DefaultObjUtil {
    public static final String GET = "get";
    public static final String SET = "set";

    /**
     * 任意对象默认值赋值
     *
     * @param obj    原对象
     * @param result 需要指定某些属性的默认值集合  key: 属性名字   value: 属性值
     * @return {@link Object}
     */
    public static Object convertDefaultObj(Object obj, Map<String, Object> result) {
        try {
            Class<?> clzz = obj.getClass();
            //获取当前对象信息，并且向上迭代寻找父类，直到寻找到Object后中断
            do {
                //获取类的方法和属性信息
                Method[] declaredMethods = clzz.getDeclaredMethods();
                Field[] declaredFields = clzz.getDeclaredFields();
                setMethod(obj, clzz, declaredMethods, declaredFields,result);
            }while ((clzz=clzz.getSuperclass())!=null && clzz!=Object.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 为对象赋予默认值操作方法
     *
     * @param obj             原对象
     * @param clzz            对象的类信息
     * @param declaredMethods 类的方法集
     * @param declaredFields  类的属性集
     * @param map             需要指定某些属性的默认值集合  key: 属性名字   value: 属性值
     * @throws Exception 异常
     *///填充属性默认值
    public static void setMethod(Object obj, Class<?> clzz, Method[] declaredMethods, Field[] declaredFields,Map<String,Object> map) throws Exception {

        //便利方法
        for (Method method : declaredMethods) {
            String methodName = method.getName();
            //只获取get方法，并且忽略getClass方法
            if (methodName.equals("getClass") || !methodName.startsWith("get")) {
                continue;
            }

            //遍历属性,获取属性名
            for (Field field : declaredFields) {
                String fieldName = field.getName();
                String methodFieldName = fieldName.replace(fieldName.substring(0, 1), fieldName.substring(0, 1).toUpperCase());

                //方法与属性匹配对比
                if (!methodName.equals(GET + methodFieldName)) {
                    continue;
                }

                //获取属性值
                Object jg = method.invoke(obj);

                //null校验
                if (jg != null) {
                    break;
                }

                //获取属性类型
                String type = field.getType().getName();
                String[] split = type.split("\\.");
                Method methods = clzz.getDeclaredMethod(SET + methodFieldName, field.getType());
                //这里演示普通类型，特殊类型需要自己定义处理扩展
                switch (split[split.length - 1]){
                    case "String":
                        methods.invoke(obj, map.get(fieldName)==null?"":map.get(fieldName));
                        break;
                    case "Integer":
                        methods.invoke(obj, map.get(fieldName)==null?1:map.get(fieldName));
                        break;
                }
            }
        }
    }
}
