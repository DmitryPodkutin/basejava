package ru.javawebinar.basejava;

import java.io.File;

public class MainFile {
    private static final File DIR = new File("/Users/Dmitry/Documents/JavaLessons/basejava/src");
    public static void main(String[] args) {
        System.out.println(DIR.getAbsolutePath());
        showTree(DIR, 0);
    }

    public static void showTree(File dir, int nest) {
        StringBuilder whiteSpace = new StringBuilder();
        for (int i = 0; i < nest; i++) {
            whiteSpace.append('\t');
        }
        File[] files = dir.listFiles();
        assert files != null;
        for (File file : files) {
            System.out.println(whiteSpace.toString() + file.getName());
            if (file.isDirectory()) {
                showTree(file, nest + 1);
            }
        }
    }
}