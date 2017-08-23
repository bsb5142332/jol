package com.jk.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

public class JOLSample_15_IdentityHashCode {

    /*
     * The example for identity hash code.
     *
     * The identity hash code, once computed, should stay the same.
     * HotSpot opts to store the hash code in the mark word as well.
     * You can clearly see the hash code bytes in the header once
     * it was computed.
     * 
     * hash code 一经计算，就是保持不变，HotSpot可配置的将hash code 存储在mark word中。
     * 实验发现，为A增加属性，并重写了hashCode()方法后，貌似就不存储了，猜测是hash code会根据属性值变化而变化，存储没有意义。
     */

    public static void main(String[] args) throws Exception {
        System.out.println(VM.current().details());

        final A a = new A();

        ClassLayout layout = ClassLayout.parseInstance(a);

        System.out.println("**** Fresh object");
        System.out.println(layout.toPrintable());

        System.out.println("hashCode: " + Integer.toHexString(a.hashCode()));
        System.out.println();

        System.out.println("**** After identityHashCode()");
        System.out.println(layout.toPrintable());
    }

    public static class A {
        // no fields
    }

}