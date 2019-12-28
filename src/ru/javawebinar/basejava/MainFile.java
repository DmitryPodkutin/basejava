package ru.javawebinar.basejava;

import java.io.File;

public class MainFile {
    private static final String DIRECTORY = "/Users/Dmitry/Documents/JavaLessons/basejava/src";

    public static void main(String[] args) {
        System.out.println(DIRECTORY);
        showTree(DIRECTORY, 0);
    }

    public static void showTree(String dirName, int nest) {
        File dir = new File(dirName);
        StringBuilder whiteSpace = new StringBuilder();
        for (int i = 0; i < nest; i++) {
            whiteSpace.append('\t');
        }
        File[] files = dir.listFiles();
        for (File file : files) {
            System.out.println(whiteSpace.toString() + file.getName());
            if (file.isDirectory()) {
                showTree(file.getAbsolutePath(), nest + 1);
            }
        }
    }
}