package observer;

import lombok.Setter;
import observer.server.IObservable;
import observer.server.IObserve;

import java.util.ArrayList;
import java.util.List;

public class ObSome implements IObservable {
    //注入观察者

    List<IObserve> observeList=new ArrayList<>();


    @Override
    public void send() {

    }

    @Override
    public void addObserve(IObserve observable) {
        observeList.add(observable);
    }

    @Override
    public void removeObserve(IObserve observable) {
        observeList.remove(observable);
    }

    //发送通知
    @Override
    public void notifyObservers(String message) {
        observeList.forEach(ob->ob.apply(message));
    }

    public void secondSend(){
        System.out.println("secondSend");
        notifyObservers("secondSend");
    }

    public void firstSend(){
        System.out.println("firstSend");

    }

}
