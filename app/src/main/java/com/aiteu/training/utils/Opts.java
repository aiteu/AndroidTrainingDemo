package com.aiteu.training.utils;

import java.util.List;

public final class Opts {

    public static boolean isEmpty(List list) {
        return (isNull(list) || list.isEmpty());
    }

    public static boolean isEmpty(String[] array){
        if(isNull(array)) return true;
        return (array.length == 0);
    }

    public static boolean isNull(Object obj){
        return (null == obj);
    }

    public static Class optClass(String className) {
        try{
            return Class.forName(className);
        }catch(Exception e){
            return null;
        }
    }

    public static String opt(String str){
        return Opts.isNull(str) ? "" : str;
    }
}
