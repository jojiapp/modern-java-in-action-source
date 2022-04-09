package modernjavainaction.chap06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

import static modernjavainaction.chap06.PartitionPrimeNumbers.isPrime;

public class MyCollectorHarness {
    public static void main(String[] args) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            IntStream.rangeClosed(2, 1_000_000)
                    .boxed()
                    .collect(
                            () -> new HashMap<Boolean, List<Integer>>() {{
                                put(true, new ArrayList<>());
                                put(false, new ArrayList<>());
                            }},
                            (acc, candidate) -> {
                                acc.get(isPrime(acc.get(true), candidate))
                                        .add(candidate);
                            },
                            (map1, map2) -> {
                                map1.get(true).addAll(map2.get(true));
                                map1.get(false).addAll(map2.get(false));
                            }
                    );
            long duration = (System.nanoTime() - start) / 1_000_000;
            if (duration < fastest) fastest = duration;
        }
        System.out.println("Fastest execution done in " + fastest + " msecs");
    }
}
