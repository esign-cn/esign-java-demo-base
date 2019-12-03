package cn.esign.demo.base.utils;

import cn.esign.demo.base.annotation.Model;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author zhexiu
 * @since 2019/7/19 上午11:39
 */
public class ModelObjectUtils {

    public static void fill(Object model, Map<String, String> propMap) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        Field[] fields = model.getClass().getDeclaredFields();
        for(Field field : fields ){
            field.setAccessible(true);

            // 得到此属性的类型
            String type = field.getType().toString();
            Model modelAnn = field.getAnnotation(Model.class);
            if(modelAnn == null){
                continue;
            }
            String val = propMap.get(modelAnn.value());
            if(StringUtils.isBlank(val)){
                continue;
            }
            if (type.endsWith("String")) {
                // 给属性设值
                field.set(model, val);
            } else if (type.endsWith("int") || type.endsWith("Integer")) {
                // 给属性设值
                field.set(model, Integer.parseInt(val));
            } else {
                continue;
            }
        }
    }


}
