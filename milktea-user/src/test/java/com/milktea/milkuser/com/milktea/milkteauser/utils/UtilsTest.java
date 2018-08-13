package com.milktea.milkuser.com.milktea.milkteauser.utils;

import com.milktea.milkteauser.util.HttpUtil;
import org.junit.Test;

import java.io.IOException;

public class UtilsTest {


    @Test
    public void httpTest(){
        try {
            String str=HttpUtil.get("http://192.168.0.105:8088/test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
