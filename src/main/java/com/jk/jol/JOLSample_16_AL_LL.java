package com.jk.jol;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;

import org.openjdk.jol.info.GraphLayout;
import org.openjdk.jol.vm.VM;

public class JOLSample_16_AL_LL {

    /*
     * The example of traversing the reachability graphs.
     * 
     * 遍历可达图的例子
     * 
     * In addition to introspecting the object internals, we can also
     * see the object externals, that is, the objects referenced from the
     * object in question. There are multiple ways to illustrate this,
     * the summary table seems to work well.
     *
     * 除了对象内部 分析外，我们还可以看到对象外部，也及时对象关联的对象
     *  
     * In this example, you can clearly see the difference between
     * the shadow heap (i.e. space taken by the reachable objects)
     * for ArrayList and LinkedList.
     *
     * 这个例子中，可以清楚的看到ArrayList and LinkedList在shadow heap中的不同
     * 
     * When several roots are handed over to JOL, it tracks the objects reachable
     * from either root, and also avoids double-counting.
     * 
     * 当多个roots被JOL处理时，它会跟踪每个root的可达路径，并且避免重复计算。（1000个Integer对象）
     * 
     * 通过输出可以看到，同样size，ArrayList和LinkedList的内存结构和消耗都不同，因为本身实现就不一样
     */

    public static void main(String[] args) throws Exception {
        System.out.println(VM.current().details());

        ArrayList<Integer> al = new ArrayList<Integer>();
        LinkedList<Integer> ll = new LinkedList<Integer>();

        for (int i = 0; i < 1000; i++) {
            Integer io = i; // box once
            al.add(io);
            ll.add(io);
        }

        al.trimToSize();

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(GraphLayout.parseInstance(al).toFootprint());
        pw.println(GraphLayout.parseInstance(ll).toFootprint());
        pw.println(GraphLayout.parseInstance(al, ll).toFootprint());
        pw.close();
    }

}
