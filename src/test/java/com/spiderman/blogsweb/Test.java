package com.spiderman.blogsweb;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        int c = 2,s = 20;
        List<String> a = new ArrayList<>();
        for (int i=1;i<=100;i++){
            a.add(String.valueOf(i));
        }
        System.out.println(a);
        a = a.stream().skip((c-1)*s).limit(s).collect(Collectors.toList());
        System.out.println(a);
    }
}
