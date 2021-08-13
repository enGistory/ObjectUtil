package strategy.task;

/**
 * 监听器策略计划
 * */
public abstract class AbstractListenerStrategy {


    //统一策略调用入口方法
    public static void actionListener(AbstractListenerStrategy listener){
        listener.handle();
    }

    //实际调用
    public abstract void handle();



}
