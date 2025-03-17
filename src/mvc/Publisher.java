package mvc;

import java.util.List;
import java.util.ArrayList;

public class Publisher implements Subscriber{
    private List<Subscriber> subscribers = new ArrayList<>();

    public void notifySubscribers() {
        update();
    }

    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void update() {
        for(Subscriber subscriber : subscribers) {
            subscriber.update();
        }
    }
}
