package task;

import java.util.Arrays;

public class ForkJoinTaskImpl extends MyForkJoinTask<int[]> {

    private int[] inits;
    public ForkJoinTaskImpl(int[] inits){
        this.inits = inits;
    }

    @Override
    protected int[] compute() {
        int sourcelen = inits.length;
//        System.out.println(Thread.currentThread().getName() + "");
        if (sourcelen>INT_SPLIT){
            ForkJoinTaskImpl fork1 = new ForkJoinTaskImpl(Arrays.copyOf(inits, sourcelen / INT_SPLIT));
            ForkJoinTaskImpl fork2 = new ForkJoinTaskImpl(Arrays.copyOfRange(inits, sourcelen / INT_SPLIT, sourcelen));
            fork1.fork();
            fork2.fork();
            return joinints(fork1.join(), fork2.join());
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
            int fork1Val = fork1num>=fork1len?java.lang.Integer.MAX_VALUE:fork1[fork1num];
            int fork2Val = fork2num>=fork2len?java.lang.Integer.MAX_VALUE:fork2[fork2num];

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
}
