package com.aiteu.training.demo1.biz;

import com.aiteu.training.utils.Opts;

import java.io.Serializable;

public class Person implements Serializable {

    public String name;
    public String gender;
    public String[] likes;
    public boolean isMarried;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(format("姓名", Opts.opt(name)));
        builder.append(format("性别", Opts.opt(gender)));
        StringBuffer buf = new StringBuffer();
        if(!Opts.isEmpty(likes)) {
            for(String str : likes){
                buf.append(str);
                buf.append(",");
            }
            buf.deleteCharAt(buf.length()-1);
        }
        builder.append(format("性别", Opts.opt(buf.toString())));
        builder.append(format("婚姻状况", isMarried ? "已婚" : "未婚"));
        return builder.toString();
    }

    private String format(String key, String value){
        return String.format("%s：%s\n", key, value);
    }
}
