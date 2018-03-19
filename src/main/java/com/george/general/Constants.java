package com.george.general;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by George on 2017/12/12.
 */
public final class Constants {
    public static final String ROOT_PATH;//项目根路径
    public static final String PACKAGE_NAME = "com.george";

    static {
        StringBuilder test = new StringBuilder(Constants.class.getClassLoader().getResource(".").getPath());
        String s = "";
        try {
            s = URLDecoder.decode(test.substring(1, test.indexOf("/target")), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ROOT_PATH = s;
    }
}
