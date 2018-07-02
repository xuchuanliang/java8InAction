package capter06;

import capter04.Dish;
import capter05.Trader;
import capter05.Transaction;

import java.util.*;
import java.util.stream.Collectors;

public class MainTest {
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
//        test4();
//        test5();
        test6();
    }

    /**
     * 统计一共有多少元素
     */
    public static void test1(){
        menu.stream().collect(Collectors.counting());
        menu.stream().count();
    }

    /**
     * 最大值和最小值
     */
    public static void test2(){
        Comparator<Dish> comparator = Comparator.comparingInt(Dish::getCalories);
        menu.stream().collect(Collectors.maxBy(comparator));
        menu.stream().collect(Collectors.minBy(comparator));
    }

    /**
     * 汇总
     */
    public static void test3(){
        //所有热量的和
        int totalCalories = menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        //所有热量的平均
        double avg = menu.stream().collect(Collectors.averagingInt(Dish::getCalories));
        //一次性统计多中类别的统计数据
        IntSummaryStatistics intSummaryStatistics = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        intSummaryStatistics.getAverage();
        intSummaryStatistics.getCount();
        intSummaryStatistics.getMax();
        intSummaryStatistics.getMin();
        intSummaryStatistics.getSum();
    }

    /**
     * 连接字符串
     */
    public static void test4(){
        String s1 = menu.stream().map(Dish::getName).collect(Collectors.joining());
        String s2 = menu.stream().map(Dish::getName).collect(Collectors.joining("->"));
        System.out.println(s1);//porkbeefchickenfrench friesriceseason fruitpizzaprawnssalmon
        System.out.println(s2);//pork->beef->chicken->french fries->rice->season fruit->pizza->prawns->salmon
    }

    /**
     * 分组
     */
    public static void test5(){
        Map<Dish.Type,List<Dish>> map = menu.stream().collect(Collectors.groupingBy(Dish::getType));
        System.out.println(map);
    }

    /**
     * 复杂分组
     */
    public static void test6(){
        Map<Dish.CaloricLevel,List<Dish>> map = menu.stream().collect(Collectors.groupingBy(d->{
            if(d.getCalories()<=400){
                return Dish.CaloricLevel.DIET;
            }else if(d.getCalories()<=700){
                return Dish.CaloricLevel.FAT;
            }else {
                return Dish.CaloricLevel.NORMAL;
            }
        }));
        System.out.println(map);
    }

    /**
     * 复杂分组 多级
     */
    public static void teat7(){
        Map<Dish.Type, Map<Dish.CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = menu.stream().collect(Collectors.groupingBy(Dish::getType,Collectors.groupingBy(d->{
            if(d.getCalories()<=400){
                return Dish.CaloricLevel.DIET;
            }else if(d.getCalories()<=700){
                return Dish.CaloricLevel.FAT;
            }else {
                return Dish.CaloricLevel.NORMAL;
            }
        })));
    }

    /**
     * 复杂分组，计算每类的个数
     */
    public static void test8(){
        Map<Dish.Type,Long> map = menu.stream().collect(Collectors.groupingBy(Dish::getType,Collectors.counting()));
    }

    /**
     * 复杂分组：获取每种类型中的最高热量的菜单
     */
    public static void test9(){
        Map<Dish.Type,Optional<Dish>> map = menu.stream().collect(Collectors.groupingBy(Dish::getType,Collectors.maxBy(Comparator.comparing(Dish::getCalories).reversed())));
    }

    /**
     * collectingAndThen工厂方法，将数据转换成另一种类型
     此时到这种程度已经非常复杂，也不易于阅读了
     */
    public static void test10(){
        Map<Dish.Type,Dish> map = menu.stream().collect(
                Collectors.groupingBy(
                        Dish::getType,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparing(Dish::getCalories)),Optional::get)));
    }

    /**
     * 复杂分组，对每一组热量进行求和
     */
    public static void test11(){
        menu.stream().collect(Collectors.groupingBy(Dish::getType,Collectors.summingInt(Dish::getCalories)));
    }

    /**
     * 复杂分组
     */
    public static void test12(){
        Map<Dish.Type,Set<Dish.CaloricLevel>> map = menu.stream().collect(Collectors.groupingBy(Dish::getType,Collectors.mapping(d->{
            if(d.getCalories()<=400){
                return Dish.CaloricLevel.DIET;
            }else if(d.getCalories()<=700){
                return Dish.CaloricLevel.FAT;
            }else {
                return Dish.CaloricLevel.NORMAL;
            }
        },Collectors.toSet())));
    }

    /**
     * 复杂映射:映射到指定类型
     */
    public static void test13(){
        Map<Dish.Type,Set<Dish.CaloricLevel>> map = menu.stream().collect(Collectors.groupingBy(Dish::getType,Collectors.mapping(d->{
            if(d.getCalories()<=400){
                return Dish.CaloricLevel.DIET;
            }else if(d.getCalories()<=700){
                return Dish.CaloricLevel.FAT;
            }else {
                return Dish.CaloricLevel.NORMAL;
            }
        },Collectors.toCollection(HashSet::new))));
    }

}
