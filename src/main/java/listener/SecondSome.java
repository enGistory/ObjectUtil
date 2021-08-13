package listener;

import listener.event.ICurdEvent;
import listener.event.IListener;
import listener.event.IListenerable;
import org.springframework.beans.factory.annotation.Autowired;
import strategy.SecondTask;
import strategy.task.AbstractListenerStrategy;

import javax.annotation.Resource;

/**
 * 源事件类
 * */
public class SecondSome implements IListenerable {
    private IListener listener;
    @Resource(name = "secondTask")
    private AbstractListenerStrategy listenerStrategy;

    @Override
    public void setListener(IListener listener) {
        this.listener=listener;
    }

    @Override
    public void triggerListener(ICurdEvent event) {
        listener.handle(event);
    }


    public void saveStudent(){
        System.out.println("插入了一条数据");
        ICurdEvent event = new CurdEvent(this,"saveStudent");
        AbstractListenerStrategy.actionListener(new SecondTask(event));
//        this.triggerListener(event);
    }
    public void removeStudent(){
        System.out.println("删除了一条数据");
        ICurdEvent event = new CurdEvent(this,"removeStudent");
        this.triggerListener(event);
    }
    public void modifyStudent(){
        System.out.println("修改了一条数据");
        ICurdEvent event = new CurdEvent(this,"modifyStudent");
        this.triggerListener(event);
    }
    public void findStudent(){
        System.out.println("插入了一条数据");
        ICurdEvent event = new CurdEvent(this,"findStudent");
        this.triggerListener(event);
    }

}
