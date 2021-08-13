package observer;

import observer.server.IObserve;

public class ObTest {
    public static void main(String[] args) {
        IObserve first = new FirstObserve();
        IObserve second = new SecondObserve();
        ObSome obSome = new ObSome();
        obSome.addObserve(first);
        obSome.addObserve(second);
        obSome.firstSend();
        obSome.secondSend();
    }
}
