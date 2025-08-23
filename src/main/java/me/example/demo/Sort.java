package me.example.demo;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sort {
    public List<String> sortByLength(List<String> list){
        list.sort((o1, o2) -> o1.length() - o2.length());
        return list;
    }

    //public static void main(String[] args) {
    //    List<String> scores = Arrays.asList("java", "x", "ejb", "toby");
    //    Collections.sort(scores);
    //    scores.forEach(System.out::println);
    //
    //    Collections.sort(scores, new Comparator<String>() {
    //        @Override
    //        public int compare(String o1, String o2) {
    //            return Integer.compare(o1.length(), o2.length());
    //        }
    //    });
    //
    //    scores.forEach(System.out::println);
    //}

}
