package io.github.bael.part2.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

public class CollectingAndThenExample2 {
    public static void main(String[] args) {
        String example = "    Functional interfaces often represent abstract concepts like functions, actions, or predicates  .    ";
        String example2 = " In documenting functional interfaces, or referring to variables typed as functional interfaces, it is common to refer directly to those abstract concepts, for example using \"this function\" instead of \"the function represented by this object\". When an API method is said to accept or return a functional interface in this manner, such as \"applies the provided function to...\", this is understood to mean a non-null reference to an object implementing the appropriate functional interface, unless potential nullity is explicitly specified. ";

        List<String> list = Arrays.asList(example.split("\\s+"));
        List<String> list2 = Arrays.asList(example2.split("\\s+"));

        List<String> censured = Arrays.asList("functional");
        int count;
        List<String> concat = Stream.concat(list.stream(), list2.stream())
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));


        Map<Integer, List<String>> groupMap = Stream.concat(list.stream(), list2.stream())
                .collect(Collectors.groupingBy((String s) -> s.length()));
        System.out.println(groupMap);
        Map<Integer, Set<String>> groupMap2 = Stream.concat(list.stream(), list2.stream())
                .collect(Collectors.groupingBy((String s) -> s.length(), toSet()));
        System.out.println(groupMap2);


//        Map<Integer, List<String>> partMap = Stream.concat(list.stream(), list2.stream())
//                .collect(Collectors.partitioningBy(s -> s.startsWith("func")));
//        System.out.println(partMap);


    }
}
