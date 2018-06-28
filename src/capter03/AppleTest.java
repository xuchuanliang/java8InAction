package capter03;

import java.util.Comparator;
import java.util.List;

public class AppleTest {

    public static void main(String[] args){

    }

    /**
     * 自建comparetor
     * @param appleList
     */
    public static void version1(List<Apple> appleList){
        appleList.sort(new AppleComparator());
    }

    /**
     * 匿名类
     * @param apples
     */
    public static void version2(List<Apple> apples){
        apples.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWight().compareTo(o2.getWight());
            }
        });
    }

    /**
     * 使用lambda表达式
     * @param apples
     */
    public static void version3(List<Apple> apples){
        apples.sort((Apple a1,Apple a2)->a1.getWight().compareTo(a2.getWight()));
    }

    /**
     * lambda表达式自动推断出参数类型
     * @param apples
     */
    public static void version4(List<Apple> apples){
        apples.sort((a1,a2)->a1.getWight().compareTo(a2.getWight()));
    }

    public static void version5(List<Apple> apples){
        Comparator<Apple> appleComparator = Comparator.comparing((Apple a)->a.getWight());
        apples.sort(appleComparator);
        apples.sort(Comparator.comparing((a)->a.getWight()));
        apples.sort(Comparator.comparing(Apple::getWight).reversed());
    }

}
