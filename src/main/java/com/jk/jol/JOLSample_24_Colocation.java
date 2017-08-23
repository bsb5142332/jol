package com.jk.jol;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.openjdk.jol.info.GraphLayout;
import org.openjdk.jol.vm.VM;

public class JOLSample_24_Colocation {

    /*
     * This is the example how VM colocates the objects allocated
     * by different threads.
     *
     * In this example, the ConcurrentHashMap is being populated
     * by several threads. We can see that after a few GCs it is
     * densely packed, regardless of the fact it was allocated by
     * multiple threads.
     *
     * 这个例子中，ConcurrentHashMap对多个线程一起填充，我们可以看到在进行一些GC后他是密集包装的，而不管它是由多个线程分配的事实。
     * 
     * This example generates PNG images in your current directory.
     */

    public static volatile Object sink;

    public static void main(String[] args) throws Exception {
        System.out.println(VM.current().details());

        // allocate some objects to beef up generations
        for (int c = 0; c < 1000000; c++) {
            sink = new Object();
        }
        System.gc();

        final int COUNT = 1000;

        ConcurrentHashMap<Object, Object> chm = new ConcurrentHashMap<Object, Object>();

        addElements(COUNT, chm);

        GraphLayout.parseInstance(chm).toImage("chm-1-new.png");

        for (int c = 2; c <= 5; c++) {
            GraphLayout.parseInstance(chm).toImage("chm-" + c + "-gc.png");
            System.gc();
        }

        addElements(COUNT, chm);

        for (int c = 6; c <= 10; c++) {
            GraphLayout.parseInstance(chm).toImage("chm-" + c + "-more-gc.png");
            System.gc();
        }

    }

    private static void addElements(final int count, final Map<Object, Object> chm) throws InterruptedException {
        ExecutorService pool = Executors.newCachedThreadPool();

        Runnable task = new Runnable() {
            public void run() {
                for (int c = 0; c < count; c++) {
                    Object o = new Object();
                    chm.put(o, o);
                }
            }
        };

        for (int t = 0; t < Runtime.getRuntime().availableProcessors() * 2; t++) {
            pool.submit(task);
        }

        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.DAYS);
    }

}

