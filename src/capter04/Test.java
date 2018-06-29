package capter04;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.stream().filter(a->"1".equals(a));
        arrayList.forEach(a->{

        });
    }
}
