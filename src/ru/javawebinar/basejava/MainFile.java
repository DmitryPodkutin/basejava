package ru.javawebinar.basejava;

import java.io.File;

public class MainFile {
    private static final File DIR = new File("/Users/Dmitry/Documents/JavaLessons/basejava/src");

    public static void main(String[] args) {
        System.out.println(DIR.getAbsolutePath());
        showTree(DIR, "");
    }

    public static void showTree(File dir, String indent) {
        File[] files = dir.listFiles();
        assert files != null;
        for (File file : files) {
            System.out.println(indent + file.getName());
            if (file.isDirectory()) {
                showTree(file, indent + "  ");
            }
        }
    }
}