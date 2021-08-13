package task;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public abstract class MyForkJoinTask<V> extends RecursiveTask<V> {
    final static int INT_SPLIT = 2;
    //公用方法调用
    public static<V> V initTask(MyForkJoinTask<V> task) throws ExecutionException, InterruptedException {
        ForkJoinPool forkPool = new ForkJoinPool();
        ForkJoinTask<V> submit = forkPool.submit(task);
        V invoke = submit.get();
        return invoke;
    }

}
