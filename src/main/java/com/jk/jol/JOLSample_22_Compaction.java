package com.jk.jol;

import java.util.ArrayList;
import java.util.List;

import org.openjdk.jol.info.GraphLayout;
import org.openjdk.jol.vm.VM;

public class JOLSample_22_Compaction {

    /*
     * This is the example how VM compacts the objects.
     *
     * This example generates PNG images in your current directory.
     *
     * You can see the freshly allocated and populated list has quite
     * the sparse layout. It happens because many temporary objects are
     * allocated while populating the list. The subsequent GCs compact
     * the list into the one or few dense blocks.
     * 
     * 你可以看到新分配和填充的list具有相当稀疏的布局，这是因为在填充list时分配了许多临时对象，这些临时对象是什么呢？
     * 后续的GC会把list聚集成一个或几个密集块。
     */

    public static volatile Object sink;

    public static void main(String[] args) throws Exception {
        System.out.println(VM.current().details());

        // allocate some objects to beef up generations
        for (int c = 0; c < 1000000; c++) {
            sink = new Object();
        }
        System.gc();

        List<String> list = new ArrayList<String>();
        for (int c = 0; c < 1000; c++) {
            list.add("Key");
        }

        for (int c = 1; c <= 10; c++) {
            GraphLayout.parseInstance(list).toImage("list-" + c + ".png");
            System.gc();
        }
    }

}
