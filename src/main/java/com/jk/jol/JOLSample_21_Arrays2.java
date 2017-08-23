package com.jk.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

public class JOLSample_21_Arrays2 {

    /*
     * 数组的对象头是16个字节
     */

    public static void main(String[] args) throws Exception {
        System.out.println(VM.current().details());
        Integer[] arr2 = new Integer[10];
        arr2[0] = new Integer(1);
        System.out.println(ClassLayout.parseInstance(arr2).toPrintable());
        System.out.println(ClassLayout.parseInstance(arr2[0]).toPrintable());
    }

}
