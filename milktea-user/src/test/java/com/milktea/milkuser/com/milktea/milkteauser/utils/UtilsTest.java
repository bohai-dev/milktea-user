package com.milktea.milkuser.com.milktea.milkteauser.utils;

import com.milktea.milkteauser.util.HttpUtil;
import org.junit.Test;

import java.io.IOException;

public class UtilsTest {

    public int testException(int j){
        int count=1;
        int i=0;
        try {
            count++;
            i=10/j;
        } catch (Exception e) {
            e.printStackTrace();
            if (count<=2){
                j++;
                i=10/j;
            }
        }

        return i;

    }

    @Test
    public  void testRepeat(){
        System.out.println(2%50==0?2/50:2/50+1);
        System.out.println(50%50==0?50/50:50/50+1);
        System.out.println(51%50==0?51/50:51/50+1);

    }

    @Test
    public void testPrint(){
        StaticTest.printNum();
        StaticTest.printNum();
    }

    @Test
    public void httpTest(){
        try {
            String str=HttpUtil.get("http://192.168.0.105:8088/test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
