package capter01;

import java.io.File;

public class FileFilter {

    public static void main(String[] args){



    }

    /**
     * java8之前列出所有隐藏文件的方法
     */
    public static void oldFilter(){
        File[] files = new File(".").listFiles(new java.io.FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isHidden();
            }
        });
    }

    /**
     * java8函数式编程：java8中的方法引用::语法（即把这个方法作为值）传给listFiles方法
     */
    public static void newFilter(){
        File[] files = new File(".").listFiles(File::isHidden);
    }


}
