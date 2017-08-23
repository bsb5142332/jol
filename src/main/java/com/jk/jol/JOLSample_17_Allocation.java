package com.jk.jol;

import java.io.PrintWriter;

import org.openjdk.jol.vm.VM;

public class JOLSample_17_Allocation {

    /*
     * The example of allocation addresses.
     *
     * This example shows the addresses of newly allocated objects
     * grow linearly in HotSpot. This is because the allocation in
     * parallel collectors is linear. We can also see it rewinds back
     * to the same offsets -- that's the start of some GC generation.
     * The address of the generation is changing, while GC adjusts
     * for the allocation rate.
     * 
     * 这个例子演示新对象的内存地址分配在虚拟机里是线性增加的,这是因为并行回收器的分配方式就是线性的。
     * 我们可以看到它经常回退到一个相同的位置--这是一些GC代的起始位置。
     * 当GC对对象分配率进行调整时，这些代的边界地址在变化。
     */

    public static void main(String[] args) throws Exception {
        System.out.println(VM.current().details());

        PrintWriter pw = new PrintWriter(System.out, true);

        long last = VM.current().addressOf(new Object());
        for (int l = 0; l < 1000 * 1000 * 1000; l++) {
            long current = VM.current().addressOf(new Object());

            long distance = Math.abs(current - last);
            if (distance > 16 * 1024) {
                pw.printf("Jumping from %x to %x (distance = %d bytes, %dK, %dM)%n",
                        last,
                        current,
                        distance,
                        distance / 1024,
                        distance / 1024 / 1024);
            }

            last = current;
        }

        pw.close();
    }

}
