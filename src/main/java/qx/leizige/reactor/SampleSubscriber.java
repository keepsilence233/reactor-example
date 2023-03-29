package qx.leizige.reactor;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

/**
 * SampleSubscriber继承类，BaseSubscriber推荐Subscribers在Reactor中自定义抽象类。
 * 该类提供了可以被覆盖以调整订阅者行为的挂钩。默认情况下，它会触发一个无限制的请求并且行为与subscribe().
 *
 * @param <T>
 */
public class SampleSubscriber<T> extends BaseSubscriber<T> {

    @Override
    protected void hookOnSubscribe(Subscription subscription) {
        System.out.println("Subscribed");
        request(1);
    }

    @Override
    protected void hookOnNext(T value) {
        System.out.println(value);
        request(1);
    }

    @Override
    protected void hookOnComplete() {
        System.out.println("Done");
    }
}
