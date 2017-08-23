package com.jk.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

public class JOLSample_11_ClassWord {

    /*
     * This is the example to have insight into object headers.
     *
     * In HotSpot, object header consists of two parts: mark word,
     * and class word. We can clearly see the class word by analysing
     * two syntactically equivalent instances of two distinct classes.
     * See the difference in headers, that difference is the reference
     * to class.
     * 
     * 两个不同类在相同语法的实例，对象头中只有引用不一样
     */

    public static void main(String[] args) throws Exception {
        System.out.println(VM.current().details());
        System.out.println(ClassLayout.parseInstance(new A()).toPrintable());
        System.out.println(ClassLayout.parseInstance(new B()).toPrintable());
    }

    public static class A {
        // no fields
    }

    public static class B {
        // no fields
    }

}
