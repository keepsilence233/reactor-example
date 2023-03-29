package qx.leizige.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Reactor test
 * {@link <a href="https://projectreactor.io/docs/core/release/reference/">...</a>}
 */
public class ReactorTest {
    public static void main(String[] args) {

//        createFluxOrMono();

//        fluxExample();

//        subScribeExample();

//        baseSubscriberExample();


    }


    /**
     * 创建 Flux 或 Mono 并订阅它的简单方法
     * 使用Flux和的最简单方法Mono是使用各自类中的众多工厂方法之一。
     */
    public static void createFluxOrMono() {
        Flux<String> seq1 = Flux.just("foo", "bar", "foobar");
        List<String> iterable = Arrays.asList("foo", "bar", "foobar");
        Flux<String> seq2 = Flux.fromIterable(iterable);


        Mono<Object> noData = Mono.empty();
        Mono<String> data = Mono.just("foo");
        Flux<Integer> numbersFromFiveToSeven = Flux.range(5, 3);
    }

    /**
     * 最简单形式Flux是通过generate 方法，它采用生成器函数。
     */
    public static void fluxExample() {
//        Flux<String> flux = Flux.generate(
//                () -> 0,
//                (state, sink) -> {
//                    sink.next("3 x " + state + " = " + 3 * state);
//                    if (state == 10) sink.complete();
//                    return state + 1;
//                }
//        );
//        flux.subscribe(System.out::println);

        //通过AtomicLong作为状态进行重写
        Flux<String> flux = Flux.generate(
                AtomicInteger::new,
                (state, sink) -> {
                    int i = state.incrementAndGet();
                    sink.next("3 x " + i + " = " + 3 * i);
                    if (i == 10) sink.complete();
                    return state;
                }
        );
        flux.subscribe(System.out::println);
    }

    /**
     * subscribe方法示例
     */
    public static void subScribeExample() {
        Flux<Integer> ints = Flux.range(1, 5);
//                .map(i -> {
//                    if (i <= 4)
//                        return i;
//                    throw new RuntimeException("Got to 5");
//                });
        ints.subscribe(System.out::println,     //	订阅将打印值的订阅者
                error -> System.out.println("Error:" + error),  //使用包含错误处理程序的订阅者进行订阅
                () -> System.out.println("Done")    //使用包含完成事件处理程序的订阅者进行订阅
        );
    }


    /**
     * Lambdas 的替代品：BaseSubscriber
     */
    public static void baseSubscriberExample() {
        SampleSubscriber<Integer> sampleSubscriber = new SampleSubscriber<>();
        Flux<Integer> ints = Flux.range(1, 4);
        ints.subscribe(sampleSubscriber);
    }
}
