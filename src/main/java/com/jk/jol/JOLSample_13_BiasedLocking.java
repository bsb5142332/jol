package com.jk.jol;

import java.util.concurrent.TimeUnit;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

public class JOLSample_13_BiasedLocking {

    /*
     * This is the example of biased locking.
     *
     * In order to demonstrate this, we first need to sleep for >5 seconds
     * to pass the grace period of biased locking. Then, we do the same
     * trick as the example before. You may notice that the mark word
     * had not changed after the lock was released. That is because
     * the mark word now contains the reference to the thread this object
     * was biased to.
     * 
     * 为了演示这个，第一步我们需要sleep大于5秒来通过偏向锁的宽限期，然后我们做上一个例子同样的动作。
     * mark word的内容在释放锁后没有发生变化，这是因为mark word现在包含了当前对象偏向线程的引用
     */

    public static void main(String[] args) throws Exception {
        System.out.println(VM.current().details());

        TimeUnit.SECONDS.sleep(6);

        final A a = new A();

        ClassLayout layout = ClassLayout.parseInstance(a);

        System.out.println("**** Fresh object");
        System.out.println(layout.toPrintable());

        synchronized (a) {
            System.out.println("**** With the lock");
            System.out.println(layout.toPrintable());
        }

        System.out.println("**** After the lock");
        System.out.println(layout.toPrintable());
    }

    public static class A {
        // no fields
    }

}
