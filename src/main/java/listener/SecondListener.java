package listener;

import listener.event.ICurdEvent;
import listener.event.IListener;
import org.springframework.stereotype.Component;

@Component("secondListener")
public class SecondListener implements IListener {
    @Override
    public void handle(ICurdEvent event) {
        String eventType = event.getEventType();
        if (ICurdEvent.Create_EVENT.equals(eventType)){
            System.out.println("Second添加操作");
        }else if (ICurdEvent.Delete_EVENT.equals(eventType)){
            System.out.println("删除操作");
        }else if (ICurdEvent.Update_EVENT.equals(eventType)){
            System.out.println("修改操作");
        }else if (ICurdEvent.Retrieve_EVENT.equals(eventType)){
            System.out.println("查找操作");
        }
    }
}
