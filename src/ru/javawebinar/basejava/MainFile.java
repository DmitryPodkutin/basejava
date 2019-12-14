package ru.javawebinar.basejava;

import java.io.File;
import java.util.Objects;

public class MainFile {
    public static void main(String[] args) {
        MainFile.recursivePrintFileNames(new File("/Users/Dmitry/Documents/JavaLessons/basejava"));
    }

    public static void recursivePrintFileNames(File dir) {
        File[] files = dir.listFiles();
        for (File file : Objects.requireNonNull(files)) {
            if (file.isDirectory()) {
                recursivePrintFileNames(file);
            } else if (file.isFile()) {
                System.out.println("File name: " + file.getName());
            }
        }
    }
}

