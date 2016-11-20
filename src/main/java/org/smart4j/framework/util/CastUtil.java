package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 转型操作工具类
 * @author zhangrh by 2016/11/19
 */
public class CastUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(CastUtil.class);

    /**
     * 转为String型 (提供默认值)
     * @param obj
     * @param defaultValue
     * @return
     */
    public static String castString(Object obj,String defaultValue){
        return obj != null ? String.valueOf(obj) :defaultValue;
    }

    public static String castString(Object obj){
        return castString(obj,"");
    }


    /**
     * 转为double型
     * @param obj
     * @param defaultValue
     * @return
     */
    public static double castDouble(Object obj , double defaultValue){
        double value = defaultValue;
        if(obj !=null){
            String strValue = castString(obj);
            if(StringUtil.isNotEmpty(strValue)){
                try{
                    value = Double.parseDouble(strValue);
                }catch (NumberFormatException e){
                    LOGGER.error("castDouble failure");
                    value = defaultValue;
                }
            }
        }
        return value;
    }

    public static double castDouble(Object obj){
        return castDouble(obj,0);
    }

    /**
     * 转为int型
     * @param obj
     * @param defaultValue
     * @return
     */
    public static int castInt(Object obj , int defaultValue){
        int value = defaultValue;
        if(obj !=null){
            String strValue = castString(obj);
            if(StringUtil.isNotEmpty(strValue)){
                try{
                    value = Integer.parseInt(strValue);
                }catch (NumberFormatException e){
                    LOGGER.error("castInt failure");
                    value = defaultValue;
                }
            }
        }
        return value;
    }

    public static int castInt(Object obj){
        return castInt(obj,0);
    }

    /**
     * 转为long型
     * @param obj
     * @param defaultValue
     * @return
     */
    public static long castLong(Object obj , long defaultValue){
        long value = defaultValue;
        if(obj !=null){
            String strValue = castString(obj);
            if(StringUtil.isNotEmpty(strValue)){
                try{
                    value = Long.parseLong(strValue);
                }catch (NumberFormatException e){
                    LOGGER.error("castLong failure");
                    value = defaultValue;
                }
            }
        }
        return value;
    }

    public static long castLong(Object obj){
        return castLong(obj,0);
    }


    /**
     * 转为boolean型
     * @param obj
     * @param defaultValue
     * @return
     */
    public static boolean castBoolean(Object obj , boolean defaultValue){
        boolean value = defaultValue;
        if(obj !=null){
            value = Boolean.parseBoolean(castString(obj));
        }
        return value;
    }

    public static boolean castBoolean(Object obj){
        return castBoolean(obj,false);
    }

}
