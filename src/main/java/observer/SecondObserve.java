package observer;

import observer.server.IObserve;

//货单
public class SecondObserve implements IObserve {
    @Override
    public void apply(String message) {
        System.out.println("订单模块正在校验信息是否完整");
    }
}
