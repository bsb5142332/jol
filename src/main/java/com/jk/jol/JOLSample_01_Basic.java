package com.jk.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

public class JOLSample_01_Basic {

    /*
     * This sample showcases the basic field layout.
     * You can see a few notable things here:
     *   a) how much the object header consumes; 对象头有多大
     *   b) how fields are laid out; 字段如何输出
     *   c) how the external alignment beefs up the object size 对齐如何造成的对象变大
     */

    public static void main(String[] args) throws Exception {
        System.out.println(VM.current().details());
        System.out.println(ClassLayout.parseClass(A.class).toPrintable());
    }

    public static class A {
        boolean f;
    }
    
}