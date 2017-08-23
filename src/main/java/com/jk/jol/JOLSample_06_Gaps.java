package com.jk.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

public class JOLSample_06_Gaps {

    /*
     * This example shows another HotSpot layout quirk.
     *
     * HotSpot rounds up the instance field block up to reference size.
     * That unfortunately yields the artificial gaps at the end of the class.
     * 
     * HotSpot 将实例字段大小舍入到一个引用的大小，即4个字节。
     * 个人理解：对象之间的属性以4个字节为最小单位进行拼接
     * See also:
     *    https://bugs.openjdk.java.net/browse/JDK-8024912
     */

    public static void main(String[] args) throws Exception {
        System.out.println(VM.current().details());
        System.out.println(ClassLayout.parseClass(C.class).toPrintable());
    }

    public static class A {
        boolean a;
    }

    public static class B extends A {
        boolean b;
    }

    public static class C extends B {
        boolean c;
    }

}