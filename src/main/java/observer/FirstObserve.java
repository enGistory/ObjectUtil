package observer;

import observer.server.IObserve;

//订单的观察者
public class FirstObserve implements IObserve {



    @Override
    public void apply(String message) {
        System.out.println("退单模块正在校验信息是否完整");
    }
}
