package com.milktea.milkteauser.util;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

/**
 * Cteated by cxy on 2018/9/19
 */
public class Utils {


    /**
     * Map转换成Object
     * @param map
     * @param beanClass
     * @return
     * @throws Exception
     */
    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null)
            return null;

        Object obj = beanClass.newInstance();

        BeanUtils.populate(obj, map);

        return obj;
    }

    /**
     * Object转换为Map
     * @param obj
     * @return
     */
    public static Map<String, String> objectToMap(Object obj) {
        if(obj == null)
            return null;

        return new BeanMap(obj);
    }


}
