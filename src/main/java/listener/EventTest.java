package listener;

import listener.event.IListener;

import java.util.ArrayList;
import java.util.List;

public class EventTest {
    public static void main(String[] args) {
//        IListener listener = new SecondListener();
        FirstSome firstSome = new FirstSome();
//        some.setListener(listener);
        firstSome.saveStudent();
        SecondSome secondSome = new SecondSome();
//        some.setListener(listener);
        secondSome.saveStudent();
        List list = new ArrayList<>();
        list.add("ss");
    }
}
