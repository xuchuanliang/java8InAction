package capter02;

import java.util.ArrayList;
import java.util.List;

public class AppleMain {
    public static void main(String[] args){

    }

    /**
     *
     */
    public void flterAppleV1(List<Apple> oldAppls){
        List<Apple> result = new ArrayList<>();
        for(Apple apple:oldAppls){
            if("green".equals(apple.getColor())){
                result.add(apple);
            }
        }
        System.out.println(result);
    }

    public void flterAppleV2(List<Apple> oldAppls,String color){
        List<Apple> result = new ArrayList<>();
        for(Apple apple:oldAppls){
            if(color.equals(apple.getColor())){
                result.add(apple);
            }
        }
        System.out.println(result);
    }
    public void flterAppleV2(List<Apple> oldAppls,int weight){
        List<Apple> result = new ArrayList<>();
        for(Apple apple:oldAppls){
            if(apple.getWeight()>weight ){
                result.add(apple);
            }
        }
        System.out.println(result);
    }

    public void filterAppleV3(List<Apple> apples,ApplePredicate applePredicate){
        List<Apple> result = new ArrayList<>();
        for(Apple apple:apples){
            if(applePredicate.test(apple)){
                result.add(apple);
            }
        }
        System.out.println(result);
        filterAppliV4(apples,(Apple apple)->"read".equals(apple.getColor()));
    }

    public void filterAppliV4(List<Apple> apples,ApplePredicate applePredicate){
        List<Apple> result = new ArrayList<>();
        for(Apple apple:apples){
            if(applePredicate.test(apple)){
                result.add(apple);
            }
        }
        System.out.println(result);
        fileterAppliV5(apples,(Apple apple)->"color".equals(apple.getColor()));
    }

    public<T> void fileterAppliV5(List<T> ts,Predicate<T> predicate){
        List<T> result = new ArrayList<>();
        for(T t:ts){
            if(predicate.test(t)){
                result.add(t);
            }
        }
        System.out.println(result);
    }

    public void compaor(List<Apple> apples){
        apples.sort((Apple apple1,Apple apple2)->apple1.getWeight().compareTo(apple2.getWeight()) );
    }


}
