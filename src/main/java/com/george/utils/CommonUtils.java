package com.george.utils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by George on 2018/1/3.
 */
public class CommonUtils {

    public static boolean strIsEmpty(String str) {
        if (str == null || str.equals(""))
            return true;
        return false;
    }


    public static boolean belongCalendar(Date time, Date from, Date to) {
        Calendar date = Calendar.getInstance();
        date.setTime(time);

        Calendar after = Calendar.getInstance();
        after.setTime(from);

        Calendar before = Calendar.getInstance();
        before.setTime(to);

        if (date.after(after) && date.before(before)) {
            return true;
        } else {
            return false;
        }
    }


    public static Date stringToDate(String date, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);//yyyy-MM-dd
        try {
            Date date1 = df.parse(date);
            System.out.println();
            return date1;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * spring mvc将前端编码（URLEncode的URI）后台接收封装好的pojo重新解码
     */
    public static Object decodePojo(Object pojo) {
        Class userCla = pojo.getClass();
        Field[] fs = userCla.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            f.setAccessible(true); // 设置些属性是可以访问的
            Object val;
            try {
                val = f.get(pojo);
                String type = f.getType().toString();//得到此属性的类型
                if (type.endsWith("String") && val != null) {
                    f.set(pojo, new String(val.toString().getBytes("ISO-8859-1"), "UTF-8")/*URLDecoder.decode(val.toString(),"utf-8")*/);//给属性设值
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }
        return pojo;
    }


    /**
     * 单个对象的所有键值
     *
     * @param obj 单个对象
     * @return Map<String,Object> map 所有 String键 Object值
     */
    public static Map<String, Object> getKeyAndValue(Object obj) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 得到类对象
        Class userCla = obj.getClass();
        /* 得到类中的所有属性集合 */
        Field[] fs = userCla.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            f.setAccessible(true); // 设置些属性是可以访问的
            Object val;
            try {
                val = f.get(obj);
                // 得到此属性的值
                map.put(f.getName(), val);// 设置键值


                String type = f.getType().toString();//得到此属性的类型
                if (type.endsWith("String")) {
//                    System.out.println(f.getType()+"\t是String");
                    f.set(obj, URLDecoder.decode(val.toString(), "utf-8"));//给属性设值
                }/*else if(type.endsWith("int") || type.endsWith("Integer")){
                    System.out.println(f.getType()+"\t是int");
                    f.set(obj,12) ; //给属性设值
                }else{
                    System.out.println(f.getType()+"\t");
                }*/

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }
        return map;
    }


    /**
     * 判断字符串的编码
     *
     * @param str
     * @return
     */
    public static String getEncoding(String str) {
        String encode[] = new String[]{
                "UTF-8",
                "ISO-8859-1",
                "GB2312",
                "GBK",
                "GB18030",
                "Big5",
                "Unicode",
                "ASCII"
        };
        for (String anEncode : encode) {
            try {
                if (str.equals(new String(str.getBytes(anEncode), anEncode))) {
                    return anEncode;
                }
            } catch (Exception ignored) {
            }
        }

        return "";
    }


    /**
     * 生成32位md5码
     *
     * @param password
     * @return
     */
    public static String md5Password(String password) {
        try {
            // 得到一个信息摘要器
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(password.getBytes());
            StringBuffer buffer = new StringBuffer();
            // 把每一个byte 做一个与运算 0xff;
            for (byte b : result) {
                // 与运算
                int number = b & 0xff;// 加盐
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }

            // 标准的md5加密后的结果
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }

    }
}
