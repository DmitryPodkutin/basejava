package ru.javawebinar.basejava;

import java.io.File;

public class MainFile {
    public static void main(String[] args) {
     MainFile.recursivePrintFileNames(new File("/Users/Dmitry/Documents/JavaLessons/basejava"));
    }
    public static void recursivePrintFileNames(File dir) {
        File[] files = dir.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    recursivePrintFileNames(file);
                } else if (file.isFile()) {
                    System.out.println("File name: " + file.getName());
                }
            }
        }
    }

