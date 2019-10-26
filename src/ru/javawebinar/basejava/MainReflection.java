package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;

import java.lang.reflect.Field;


public class MainReflection {

    public static void main(String[] args) throws IllegalAccessException {
        Resume r = new Resume();
        MainReflection mr = new MainReflection();
        mr.reflectionToString(r);


    }

    // TODO : invoke r.toString via reflection
    public void reflectionToString(Object object) throws IllegalAccessException {
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        System.out.print(clazz.getSimpleName() + " { ");
        for (Field field : fields) {
            field.setAccessible(true);
            System.out.print(field.getName() + ":" + field.get(object) + ", ");
        }
        System.out.println("}");
    }
}




