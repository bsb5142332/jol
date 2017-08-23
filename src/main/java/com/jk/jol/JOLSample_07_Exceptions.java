package com.jk.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

public class JOLSample_07_Exceptions {

    /*
     * This example shows some of the fields are treated specially in VM.
     *
     * See the suspicious gap in Throwable class. If you look in the Java
     * source, you will see the Throwable.backtrace field, which is not
     * listed in the dump. This is because this field handles the VM internal
     * info which should not be accessible to users under no conditions.
     * 
     * Throwable.backtrace字段不在堆中，因为这个字段处理虚拟机内部信息，所以不应该无条件对用户开放。
     * 和transient修饰符无关
     * 
     * See also:
     *    http://bugs.openjdk.java.net/browse/JDK-4496456
     */

    public static void main(String[] args) throws Exception {
        System.out.println(VM.current().details());
        System.out.println(ClassLayout.parseClass(Throwable.class).toPrintable());
        System.out.println(ClassLayout.parseClass(A.class).toPrintable());
    }

    static class A {
        transient int i;
    }
    
}
