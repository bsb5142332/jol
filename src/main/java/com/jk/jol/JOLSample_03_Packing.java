package com.jk.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

public class JOLSample_03_Packing {

    /*
     * This is the example how VM packs the fields.
     *
     * JVMs pack the fields to minimize the memory footprint. Run
     * this example and see the fields are densely packed, and gaps
     * are minimal. It is achieved by aligning fields in 8->4->2->1
     * order, because it can not break the initial alignment, once we
     * align the 8-byte field. The gap resulted in the initial 8-byte
     * align can be taken by one or few smaller-sized fields.
     * 
     * JVM组装字段时会尽量减少内存占用，运行这个例子可以看出字段是紧密排列的， 保证空白（间隙）是最小的。
     * 它是按照8->4->2->1的顺序来实现排列字段的，一旦我们需要排列8字节的字段，如果前一个8字节未排满的话，会先从后面找一个小一些的字段来排。
     *  
     * Note that the actual field order is very different from the
     * declared order. Nothing in the JVM spec requires otherwise.
     */

    public static void main(String[] args) throws Exception {
        System.out.println(VM.current().details());
        System.out.println(ClassLayout.parseClass(A.class).toPrintable());
    }

    public static class A {
        boolean bo1, bo2;
        byte b1, b2;
        char c1, c2;
        double d1, d2;
        float f1, f2;
        int i1, i2;
        long l1, l2;
        short s1, s2;
    }

}