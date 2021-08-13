package listener;

import listener.event.ICurdEvent;
import listener.event.IListenerable;

public class CurdEvent implements ICurdEvent {
    //事件
    private IListenerable iListenerable;
    //事件方法
    private String methodname;


    public CurdEvent(IListenerable iListenerable, String methodname) {
        this.iListenerable = iListenerable;
        this.methodname = methodname;
    }

    @Override
    public IListenerable getEventSource() {
        return iListenerable;
    }

    @Override
    public String getEventType() {
        String eventType = null;
        if (methodname.startsWith("save")){
            eventType = Create_EVENT;
        }else if (methodname.startsWith("remove")){
            eventType = Delete_EVENT;
        }else if (methodname.startsWith("modify")){//修改
            eventType = Update_EVENT;
        }else if (methodname.startsWith("find")){
            eventType = Retrieve_EVENT;
        }else {
            eventType = "have not this event type";
        }
        return eventType;
    }

}
