package ru.javawebinar.basejava;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamTest {
    public static void main(String[] args) {
        int[] values = {1, 2, 3, 2, 5};
        List<Integer> integers = Arrays.asList(1, 2, 7, 9, 4, 6);
        System.out.println(mainValue(values));
        System.out.println(oddOrEven(integers));
    }
    public static int mainValue(int[] values) {
        return Arrays.stream(values).distinct().sorted().reduce(0, (a, b) -> 10 * a + b);
    }
    public static List<Integer> oddOrEven(List<Integer> integers) {
        return (integers.stream()
                .reduce(0, Integer::sum) % 2 == 0
                ? filter(integers, p -> p % 2 == 0)
                : filter(integers, p -> p % 2 != 0));
    }
    private static List <Integer> filter(List <Integer> integers, Predicate<Integer> predicate) {
        return integers.stream().filter(predicate).collect(Collectors.toList());
    }
}

