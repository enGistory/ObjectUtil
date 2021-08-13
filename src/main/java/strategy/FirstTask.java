package strategy;

import listener.FirstListener;
import listener.event.ICurdEvent;
import listener.event.IListener;
import org.springframework.beans.factory.annotation.Autowired;
import strategy.task.AbstractListenerStrategy;

import javax.annotation.Resource;

public class FirstTask extends AbstractListenerStrategy {
//    @Resource(name = "firstListener")
    IListener firstListener = new FirstListener();

    ICurdEvent event;

    public FirstTask(ICurdEvent event){
        this.event = event;
    }

    @Override
    public void handle() {
        firstListener.handle(event);
    }
}
