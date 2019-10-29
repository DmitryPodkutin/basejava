package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class MainReflection {

    public static void main(String[] args) {
        Resume resume = new Resume();
        Class<? extends Resume> resumeClass = resume.getClass();
        Method metod = null;
        try {
            metod = resumeClass.getMethod("toString");
        } catch (NoSuchMethodException ex) {
            ex.printStackTrace();
        }
        try {
            Object result = metod.invoke(resume);
            System.out.println(result);
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}




