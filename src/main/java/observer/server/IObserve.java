package observer.server;

/**
 * 观察者接口
 * */
public interface IObserve {
    //触发事件
    void apply(String message);
}
