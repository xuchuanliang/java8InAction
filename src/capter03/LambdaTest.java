package capter03;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class LambdaTest {
    public static void main(String[] args){
        test1();
        test2(Arrays.asList(1,2,3,4,5),(Integer i)->System.out.println(i));
    }

    public static void test1(){
        Thread thread = new Thread(()->System.out.println("这是一个lambda实现的runnable实现类"));
        thread.start();
    }

    public static String processFile() throws FileNotFoundException {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(""))){
            return bufferedReader.readLine();
        }catch (Exception e){
            return e.getMessage();
        }
    }

    public static String processFile(BufferedReaderProcessor bufferedReaderProcessor) throws IOException {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(""))){
            return bufferedReaderProcessor.process(bufferedReader);
        }catch (Exception e){
            return e.getMessage();
        }
    }

    public static String test() throws IOException {
       return processFile((BufferedReader bufferedReader)->bufferedReader.readLine());
    }

    public static<T> void test2(List<T> list, Consumer<T> consumer){
        for(T l:list){
            consumer.accept(l);
        }
    }

    public static void test3(){
        Runnable runnable = ()->System.out.println("hahahaha");
        BufferedReaderProcessor bufferedReaderProcessor = t->{
            System.out.println("hhah,一种新的操作方式");
            return "";
        };
        Thread thread = new Thread(runnable);
    }

    public static void test4(String s){
        System.out.println();

        List<String> list = Arrays.asList(new String[]{"1","g","e","c","x","fd","a","dsa","32",});
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        list.sort((o1,o2)->o1.compareToIgnoreCase(o2));
        list.sort(String::compareToIgnoreCase);

    }


}
