package observer.server;

/**
 * 被观察者接口
 * */
public interface IObservable {
    void send();
    //    添加观察者
    void addObserve(IObserve observable);
    //    删除观察者
    void removeObserve(IObserve observable);
    //    向观察者发送信息
    void notifyObservers(String message);
}
