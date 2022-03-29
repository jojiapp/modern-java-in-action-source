package modernjavainaction.chap05;

import java.util.Comparator;
import java.util.List;

public class TransactionTest {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactionList = List.of(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        System.out.println("1. 2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정렬");
        transactionList.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .forEach(System.out::println);

        System.out.println("2. 거래자가 근무하는 모든 도시를 중복 없이 나열");
        transactionList.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);

        System.out.println("3. 케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬");
        transactionList.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(System.out::println);

        System.out.println("4. 모든 거래자의 이름을 알파벳순으로 정렬해서 반환");
        transactionList.stream()
                .map(transaction -> transaction.getTrader().getName())
                .sorted(String::compareTo)
                .forEach(System.out::println);

        // 4. 정답
        String reduce = transactionList.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (s1, s2) -> s1 + s2);

        System.out.println("5. 밀라노에 거래자가 있는가?");
        transactionList.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Milan"))
                .findAny()
                .ifPresent(transaction -> System.out.println(true));

        // 더 좋은 방법
        transactionList.stream()
                .anyMatch(transaction ->
                        transaction.getTrader().getCity().equals("Milan")
                );


        System.out.println("6. 케임브리지에 거주하는 거래자의 모든 트랜잭션값을 출력");
        transactionList.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        System.out.println("7. 전체 트랜잭션 중 최댓값은 얼마인가");
        transactionList.stream()
                .reduce((t1, t2) -> t1.getValue() > t2.getValue() ? t1 : t2)
                .ifPresent(transaction -> System.out.println(transaction.getValue()));

        System.out.println("8. 전체 트랜잭션 중 최솟값을 얼마인가");
        transactionList.stream()
                .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2)
                .ifPresent(transaction -> System.out.println(transaction.getValue()));

        // 7, 8 더 깔끔한 방법
        transactionList.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max)
                .ifPresent(System.out::println);
    }
}
