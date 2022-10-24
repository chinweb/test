package com.itheima.test;

import org.junit.jupiter.api.Test;

import javax.jws.soap.SOAPBinding;

public class UploadFileTest {

    @Test
    public void test1(){
        String fileName = "ererewe.jpg";
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        System.out.println(suffix);

    }
}
