package ru.javawebinar.basejava;

public class DeadlockTest {

    public static void main(String[] args) {
        ClassA classA = new ClassA();
        ClassB classB = new ClassB();
        new Thread(() -> {
            synchronized (classA) {
                sleep(100);
                classB.bar();
            }
        }).start();
        new Thread(() -> {
            synchronized (classB) {
                classA.foo();
            }
        }).start();

    }

    public static class ClassA {
        public synchronized void foo() {
        }
    }

    public static class ClassB {
        public synchronized void bar() {
        }
    }

    public static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
