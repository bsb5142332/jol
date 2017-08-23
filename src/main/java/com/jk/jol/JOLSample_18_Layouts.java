package com.jk.jol;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.openjdk.jol.info.GraphLayout;
import org.openjdk.jol.vm.VM;

public class JOLSample_18_Layouts {

    /*
     * This is the example of more verbose reachability graph.
     *
     * 这是更详细的可达图的示例
     * 
     * In this example, we see that under collisions, HashMap
     * degrades to the linked list. With JDK 8, we can also see
     * it further "degrades" to the tree.
     * 
     * 在这个例子中，我们看到在不断的hash碰撞下，HashMap的实现降级为LinkedList，在JDK8下，还会看到HashMap进一步降级到tree
     * 
     */

    public static void main(String[] args) throws Exception {
        System.out.println(VM.current().details());

        PrintWriter pw = new PrintWriter(System.out, true);

        Map<Dummy, Void> map = new HashMap<Dummy, Void>();

        map.put(new Dummy(1), null);
        map.put(new Dummy(2), null);

        System.gc();
        pw.println(GraphLayout.parseInstance(map).toPrintable());

        map.put(new Dummy(2), null);
        map.put(new Dummy(2), null);
        map.put(new Dummy(2), null);
        map.put(new Dummy(2), null);

        System.gc();
        pw.println(GraphLayout.parseInstance(map).toPrintable());

        for (int c = 0; c < 12; c++) {
            map.put(new Dummy(2), null);
        }

        System.gc();
        pw.println(GraphLayout.parseInstance(map).toPrintable());

        pw.close();
    }

    /**
     * Dummy class which controls the hashcode and is decently Comparable.
     */
    public static class Dummy implements Comparable<Dummy> {
        static int ID;
        final int id = ID++;
        final int hc;

        public Dummy(int hc) {
            this.hc = hc;
        }

        @Override
        public boolean equals(Object o) {
            return (this == o);
        }

        @Override
        public int hashCode() {
            return hc;
        }

        public int compareTo(Dummy o) {
            return (id < o.id) ? -1 : ((id == o.id) ? 0 : 1);
        }
        
    }

}
