import task.ForkJoinTaskImpl;
import task.MyForkJoinTask;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutionException;

/**
 * 分支归并排序
 * */
public class Merge1 {
    //数量
    static final int  max= 100000000;
    static int[] inits = new int[max];
    static int split = 2;

    static {
        Random random = new Random();
        for (int i = 0; i < max; i++) {
            inits[i]=random.nextInt(200000000);
        }
    }

    public static int[] forkint(int[] inits){
        int sourcelen = inits.length;
        if (sourcelen>split){
            int[] fork1 = forkint(Arrays.copyOf(inits, sourcelen / split));
            int[] fork2 = forkint(Arrays.copyOfRange(inits, sourcelen / split, sourcelen));
            return joinints(fork1, fork2);
        }else {
            //一个值或两个值
            if (sourcelen==1||inits[0]<inits[1]){
                return inits;
            }else {
                int temp = inits[0];
                inits[0] = inits[1];
                inits[1] = temp;
                return inits;
            }
        }
    }

    public static int[] joinints(int[] fork1,int[] fork2){
        //两个数组皆为有序数组
        int fork1len = fork1.length;
        int fork2len = fork2.length;
        int sumlen = fork1len+fork2len;
        int[] inits = new int[sumlen];

        for (int i = 0,fork1num = 0,fork2num=0; i < sumlen; i++) {
            int fork1Val = fork1num>=fork1len?Integer.MAX_VALUE:fork1[fork1num];
            int fork2Val = fork2num>=fork2len?Integer.MAX_VALUE:fork2[fork2num];

            //校验
            if (fork1Val < fork2Val){
                inits[i] = fork1Val;
                fork1num++;
            }else {
                inits[i] = fork2Val;
                fork2num++;
            }
        }
        return inits;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long millis = System.currentTimeMillis();
//        System.out.println();
//        System.out.println(Arrays.toString(inits));
//        System.out.println("---------------------------");
//        int[] forkint = forkint(inits);
        int[] forkint = MyForkJoinTask.initTask(new ForkJoinTaskImpl(inits));
        long millis2 = System.currentTimeMillis();
//        System.out.println(Arrays.toString(forkint));
        System.out.println("用了"+(millis2-millis)+"毫秒");
//        System.out.println(Arrays.toString(forkint));
    }
}
