package com.jk.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

public class JOLSample_05_InheritanceBarrier {

    /*
     * This example shows the HotSpot field layout quirk.
     * (Works best with 64-bit VMs)
     *
     * Even though we have the alignment gap before A.a field, HotSpot
     * does not claim it, because it does not track the gaps in the
     * already laid out superclasses. This yields the virtual
     * "inheritance barrier" between super- and sub-class fields blocks.
     *  
     * 尽管在A.a前面还有空隙，HotSpot并不能填充它，因为基于父类优先的原则，这个空隙已经确认了，后面的属性不能填充在父类前面已经确认的间隙中。
     * See also:
     *    https://bugs.openjdk.java.net/browse/JDK-8024913
     */

    public static void main(String[] args) throws Exception {
        System.out.println(VM.current().details());
        System.out.println(ClassLayout.parseClass(C.class).toPrintable());
    }

    public static class A {
        long a;
    }

    public static class B extends A {
        long b;
    }

    public static class C extends B {
        long c;
        int d;
    }

}

