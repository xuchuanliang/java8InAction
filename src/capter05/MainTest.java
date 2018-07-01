package capter05;

import capter04.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MainTest {

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
//        test5();
//        test6();
//        test7();
//        test8();
//        test9();
//        test10();
//        test11();
        test12();
    }

    /**
     * 使用过滤流、截断流
     */
    public static void test1(){
        List<Dish> dishes =  menu.stream().filter(d->!d.isVegetarian()).distinct().skip(1).collect(Collectors.toList());
    }

    public static void test2(){
        List<String> strings = menu.stream().map(Dish::getName).collect(Collectors.toList());
    }

    public static void test3(){
        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<Integer> list = words.stream().map(String::length).collect(Collectors.toList());
    }

    /**
     * 菜名长度
     */
    public static void test4(){
        List<Integer> list = menu.stream().map(Dish::getName).map(String::length).collect(Collectors.toList());
    }

    /**
     * 流的扁平化
     */
    public static void test5(){
        String[] s = new String[]{"Hello","World"};
        List<String> strings = Arrays.asList(s);
        List<String[]> list = strings.stream().map(w->w.split("")).distinct().collect(Collectors.toList());
        for(String[] sar:list){
            System.out.println(sar[0]);
        }
    }

    /**
     * 流的扁平化
     */
    public static void test6(){
        String[] s = new String[]{"Hello","World"};
        List<String> strings = Arrays.asList(s);
        List<String> list = strings.stream().map(w->w.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
        System.out.println(list);
    }

    public static void test7(){
        List<Integer> list = Arrays.asList(new Integer[]{1,2,3,4,5});
        List<Integer> list1 = list.stream().map(n->n*n).collect(Collectors.toList());
        System.out.println(list1);
    }

    public static void test8(){
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs = numbers1.stream().
                flatMap(i->numbers2.stream().filter(j->(i+j)%3==0).map(j->new int[]{i,j})).
                collect(Collectors.toList());
        System.out.println(pairs);

    }

    /////////////////////////////////////////////////////////////////////
    public static void test9(){
        if(menu.stream().anyMatch(Dish::isVegetarian)){
            System.out.println("true");
        }
    }


    public static void test10(){
        if(menu.stream().allMatch(d->d.getCalories()>300)){
            System.out.println("true");
        }
    }

    public static void test11(){
        Optional<Dish> optionalDish = menu.stream().filter(Dish::isVegetarian).findAny();
        menu.stream().filter(Dish::isVegetarian).findAny().ifPresent(System.out::println);
    }

    public static void test12(){
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        someNumbers.stream().map(x->x*x).filter(x->x%3==0).findFirst().ifPresent(System.out::println);
    }

    public static void test13(){
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        int sum1 = someNumbers.stream().reduce(0,(a,b)->a+b);
        Optional<Integer> optionalInteger = someNumbers.stream().reduce(Integer::sum);
        Optional<Integer> optionalInteger1 = someNumbers.stream().reduce(Integer::max);

    }
}
