package strategy;

import listener.SecondListener;
import listener.event.ICurdEvent;
import listener.event.IListener;
import strategy.task.AbstractListenerStrategy;

import javax.annotation.Resource;

public class SecondTask extends AbstractListenerStrategy {
//    @Resource(name = "secondListener")
    IListener secondListener = new SecondListener();

    ICurdEvent event;

    public SecondTask(ICurdEvent event){
        this.event = event;
    }

    @Override
    public void handle() {
        secondListener.handle(event);
    }

}
