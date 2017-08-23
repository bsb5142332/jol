package com.jk.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

public class JOLSample_04_Inheritance {

    /*
     * This is the example how VM lays out the fields in the hierarchy.
     *
     * The important invariant for JVM to maintain is laying out the
     * accessible fields at the same offsets regardless of the class
     * the field is being accessed through. That is, for classes B and C
     * below the field A.a should reside on the same offset. This prompts
     * VM to lay out the superclass fields first.
     * 
     * 前面一大段没看懂，总之，JVM保证父类的字段排在前面
     * 
     */

    public static void main(String[] args) throws Exception {
        System.out.println(VM.current().details());
        System.out.println(ClassLayout.parseClass(C.class).toPrintable());
    }

    public static class A {
        int a;
    }

    public static class B extends A {
        int b;
    }

    public static class C extends B {
        int c;
    }

}