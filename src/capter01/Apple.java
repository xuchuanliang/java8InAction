package capter01;

import jdk.nashorn.internal.objects.annotations.Function;

import java.util.ArrayList;
import java.util.List;

public class Apple {

    private String color;

    private int weight;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public static boolean isGreen(Apple apple){
        return "green".equals(apple.getColor());
    }

    public static boolean isHeavy(Apple apple){
        return apple.getWeight()>120;
    }

    public static List<Apple> filterApple(List<Apple> apples,Predicate<Apple> p){
        List<Apple> result = new ArrayList<>();
        for(Apple apple:apples){
            if(p.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }
}

interface Predicate<T>{
    @Function
    boolean test(T t);
}

class Test{
    public static void main(String[] args){
        Apple.filterApple(new ArrayList<>(),Apple::isGreen);
    }
}


