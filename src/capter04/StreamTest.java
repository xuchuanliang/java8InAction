package capter04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {
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
        test6();
    }
    public static void test1(){
        List<String> threeHighCaloricDishNames = menu.stream().
                filter(d->d.getCalories()>30).
                map(Dish::getName).
                limit(3).
                collect(Collectors.toList());
        System.out.println(threeHighCaloricDishNames);
    }

    public static void test2(){
        //外部迭代方式1 for-each
        List<String> name1 = new ArrayList<>();
        for(Dish d:menu){
            name1.add(d.getName());
        }

        //外部迭代方式2 迭代器
        Iterator<Dish> iterator = menu.iterator();
        while(iterator.hasNext()){
            name1.add(iterator.next().getName());
        }

        //内部迭代，流形式
        name1.addAll(menu.stream().map(Dish::getName).collect(Collectors.toList()));

        System.out.println(name1);
    }

    public static void test3(){
        List<String> l = new ArrayList<>(10000);
        for(int i=0; i<10000; i++){
            l.add(i+"");
        }

        System.out.println(System.nanoTime());
        List<String> l2 = new ArrayList<>();
        for(String s:l){
            l2.add(s);
        }
        System.out.println(System.nanoTime());
        List<String> l3 = l.parallelStream().collect(Collectors.toList());
        System.out.println(System.nanoTime());
    }

    public static void test4(){
        List<String> names = menu.stream().filter(d->{
            System.out.println("filter"+d.getName());
            return d.getCalories()>300;
        }).map(d->{
            System.out.println("map"+d.getName());
            return d.getName();
        }).collect(Collectors.toList());
    }

    public static void test5(){
        menu.stream().forEach(System.out::println);
    }

    public static void test6(){
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream().filter(d->d%2==0).distinct().forEach(System.out::println);
    }

    public static void test7(){
        menu.stream().filter(d->d.getCalories()>100).skip(2).collect(Collectors.toList());
    }
}
