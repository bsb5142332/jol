package com.jk.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

public class JOLSample_08_Class {

    /*
     * Another example of special treatment for some fields.
     *
     * If you run this example, you can see the large gap in instance field block.
     * There are no Java fields that could claim that block, hence there are no
     * "hidden" fields, like in the example before. This time, VM "injects" some
     * of the fields into the Class, to store some of the meta-information there.
     * 
     * 你可以看到一大块空隙在实例字段区域，没有任何Java字段能够访问这个区域，
     * 虚拟机注入了一些字段到Class类中，来存储一些描述信息。
     * 
     * See:
     *  http://hg.openjdk.java.net/jdk8/jdk8/hotspot/file/tip/src/share/vm/classfile/javaClasses.hpp
     *  http://hg.openjdk.java.net/jdk8/jdk8/hotspot/file/tip/src/share/vm/classfile/javaClasses.cpp
     */

    public static void main(String[] args) throws Exception {
        System.out.println(VM.current().details());
        System.out.println(ClassLayout.parseClass(Class.class).toPrintable());
    }

}