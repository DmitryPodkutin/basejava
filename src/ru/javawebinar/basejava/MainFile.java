package ru.javawebinar.basejava;

import java.io.File;

public class MainFile {
    public static void main(String[] args) {
        showTree("/Users/Dmitry/Documents/JavaLessons/basejava/src", 0);
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