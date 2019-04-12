package com.aiteu.training.utils;

import android.text.TextUtils;

import java.util.List;

public final class Opts {

    public static boolean isEmpty(List list) {
        return (isNull(list) || list.isEmpty());
    }

    public static boolean isEmpty(String[] array){
        if(isNull(array)) return true;
        return (array.length == 0);
    }

    public static boolean isEmpty(String text){
        if(isNull(text) || TextUtils.isEmpty(text)) {
            return true;
        }
        return false;
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

    public static int optInt(String intValue){
        try{
            return Integer.parseInt(intValue);
        }catch(NumberFormatException e){
            return 0;
        }
    }

    public static boolean isEquals(String...params){
        String first = params[0];
        for(int i=1;i < params.length;i++){
            if(!first.equals(params[i])){
                return false;
            }
        }
        return true;
    }
}
