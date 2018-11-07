package com.milktea.milkteauser.util;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;
import sun.rmi.server.InactiveGroupException;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
    public static Map<String, Object> objectToMap(Object obj) {
        if(obj == null) {
            return null;
        }

        Map<String,Object> hashMap=new HashMap<>();
        BeanMap beanMap=new BeanMap(obj);
        beanMap.forEach((k,v)->{
            if (!"class".equals(k)){
               if (v!=null){
                   hashMap.put(k.toString(),v);
               }
            }
        });
        return hashMap;
    }


    /**
     * 使用时间戳+指定位数的随机码生成随机数
      * @param count 指定位数的随机数
     * @return
     */
   public static  String getRandomWithTime(int count){
       //
       LocalDateTime dateTime=LocalDateTime.now();
       DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyMMddHHmmss");
       String dateStr=dateTime.format(formatter);

       String randomStr=UUID.randomUUID().toString().substring(0,count);

       return  dateStr+randomStr;

   }

   public static void main(String[] args){

       //String randomStr=UUID.randomUUID().toString().replace("-","").substring(0,3);
       //System.out.println(randomStr);
       String randomStr=getRandomWithTime(4);
       System.out.println(randomStr);
   }
}
