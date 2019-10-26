package ru.javawebinar.basejava;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.SortedArrayStorage;
import ru.javawebinar.basejava.storage.Storage;

/**
 * Test for your ru.javawebinar.basejava.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    private static final Storage ARRAY_STORAGE = new SortedArrayStorage ();

    public static void main(String[] args) {
        Resume r1 = new Resume();
        Resume r2 = new Resume();
        Resume r3 = new Resume();
        Resume r4 = new Resume();


        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r4);
        ARRAY_STORAGE.save(r3);
        try {
            System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        }
        catch (NullPointerException e){

        }
        System.out.println("Size: " + ARRAY_STORAGE.size());
        try {
            System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));
        }
        catch (NotExistStorageException e){
            System.out.println("Resume "+e.getUuid()+" Not Exist ");
        }
        catch (NullPointerException ex){

        }
        printAll();

        ARRAY_STORAGE.delete(r3.getUuid());
        try {
             ARRAY_STORAGE.delete("dummy");
        }
        catch (NotExistStorageException e){
            System.out.println("Resume "+e.getUuid()+" Not Exist ");
        }
        printAll();

        ARRAY_STORAGE.update(r2);
        printAll();
        //ARRAY_STORAGE.update(r4);

        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());


    }

    private static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
