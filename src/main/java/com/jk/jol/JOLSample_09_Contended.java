package com.jk.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

public class JOLSample_09_Contended {

    /*
     * This is an example of special annotations that can affect the field layout.
     * (This example requires JDK 8 to run, -XX:-RestrictContended should also be used)
     *
     * In order to dodge false sharing, users can put the @Contended annotation
     * on the selected fields/classes. The conservative effect of this annotation
     * is laying out the fields at sparse offsets, effectively providing the
     * artificial padding.
     * 
     * 不明白
     */

    public static void main(String[] args) throws Exception {
        System.out.println(VM.current().details());
        System.out.println(ClassLayout.parseClass(B.class).toPrintable());
    }

    public static class A {
                             int a;
                             int b;
        @sun.misc.Contended  int c;
                             int d;
    }

    public static class B extends A {
        int d;
    }

}