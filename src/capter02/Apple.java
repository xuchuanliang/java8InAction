package capter02;

public class Apple implements Comparable<Apple>{

    private String color;

    private Integer weight;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public static boolean isGreen(Apple apple){
        return "green".equals(apple.getColor());
    }

    public static boolean isHeavy(Apple apple){
        return apple.getWeight()>120;
    }

    @Override
    public int compareTo(Apple o) {
        return 0;
    }
}


