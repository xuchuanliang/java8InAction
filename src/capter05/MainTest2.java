package capter05;

import capter04.Dish;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MainTest2 {
    static Trader raoul = new Trader("Raoul", "Cambridge");
    static Trader mario = new Trader("Mario","Milan");
    static Trader alan = new Trader("Alan","Cambridge");
    static Trader brian = new Trader("Brian","Cambridge");
    static List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );

    static List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH) );

    public static void main(String[] args){
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
//        test6();
//        test7();
//        test8();
//        test11();
        test12();
    }

    //找出2011年的所有交易并按交易额排序（从低到高）
    public static void test1(){
        List<Transaction> f = transactions.stream().filter(t->t.getYear()==2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());

        System.out.println(f);
    }

    //交易员都在哪些不同的城市工作过
    public static void test2(){
        Set<String> strings = transactions.stream().map(t->t.getTrader().getCity()).collect(Collectors.toSet());
        List<String> strings1 = transactions.stream().map(t->t.getTrader().getCity()).distinct().collect(Collectors.toList());
        System.out.println(strings);
        System.out.println(strings1);
    }

    //查找所有来自于剑桥的交易员，并按姓名排序
    public static void test3(){
        List<Transaction> s = transactions.stream()
                .filter(t->t.getTrader().getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(t->t.getTrader().getName()))
                .collect(Collectors.toList());
        System.out.println(s);
        List<Trader> tt = transactions.stream().map(Transaction::getTrader).filter(t->t.getCity().equals("Cambridge")).distinct().collect(Collectors.toList());
        System.out.println(tt);
    }

    //返回所有交易员的姓名字符串，按字母顺序排序
    public static void test4(){
        List<String> ss = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .sorted()
                .collect(Collectors.toList());
        System.out.println(ss);

        String name = transactions.stream()
                .map(t->t.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("",(n,m)->n+m);

        String name2 = transactions.stream()
                .map(t->t.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining());
        System.out.println(name);
        System.out.println(name2);
    }

    //有没有交易员是在米兰工作的
    public static void test5(){
        boolean result = transactions.stream().anyMatch(t->"Milan".equals(t.getTrader().getCity()));
        System.out.println(result);
    }

//    打印生活在剑桥的交易员的所有交易额
    public static void test6(){
        transactions.stream()
                .filter(t->"Cambridge".equals(t.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);
    }

    //所有交易中，最高的交易额是多少
    public static void test7(){
        //version 1
        transactions.stream().sorted(Comparator.comparing(Transaction::getValue).reversed()).limit(1).forEach(t->System.out.println(t.getValue()));

        //version2
        Optional<Integer> optionalInteger =transactions.stream().map(Transaction::getValue).reduce(Integer::max);
        optionalInteger.ifPresent(System.out::println);
    }

    //找到交易额最小的交易
    public static void test8(){
        //version 1
        Optional<Transaction> optionalTransaction = transactions.stream().sorted(Comparator.comparing(Transaction::getValue).reversed()).limit(1).findFirst();
        optionalTransaction.ifPresent(System.out::println);

        //version 2
        optionalTransaction = transactions.stream().reduce((n,m)->n.getValue()<m.getValue()?m:n);
        optionalTransaction.ifPresent(System.out::println);

        //version 3
        optionalTransaction = transactions.stream().max(Comparator.comparing(Transaction::getValue));
        optionalTransaction.ifPresent(System.out::println);
    }

    //流转换
    public static void test9(){

        menu.stream().mapToInt(Dish::getCalories).max();

        menu.stream().mapToInt(Dish::getCalories).boxed();
    }

    public static void test10(){
        OptionalInt optionalInt = menu.stream().mapToInt(Dish::getCalories).max();
        int max = optionalInt.orElse(1);
        System.out.println(max);
    }

    public static void test11(){
        //1到100中偶数的个数
        long total = IntStream.range(1,100).filter(n->n%2==0).count();
        System.out.println(total);
    }

    //创建流
    public static void test12(){
        Stream<String> stream = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);
    }
}
