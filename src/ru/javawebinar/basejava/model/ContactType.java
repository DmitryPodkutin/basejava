package ru.javawebinar.basejava.model;

public enum ContactType {
    TEL("Телефон"),
    SKYPE("Скайп"),
    EMAIL("Электронная почта"),
    LINKED_IN("Профиль LinkedIn"),
    GIT_HUB("Профиль GitHub"),
    STACKOVER_FLOW("Профиль Stackoverflow"),
    HOME_PAGE("Домашняя Страница");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "\nContaсts{" +
                "title='" + title + '\'' +
                '}';
    }

}
