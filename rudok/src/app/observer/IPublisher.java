package app.observer;

public interface IPublisher {

    void addSubscriber(ISubscriber subscriber);
    void removeSubscriber(ISubscriber subscribers);
    void notifySubscribers(Object notification);

}
