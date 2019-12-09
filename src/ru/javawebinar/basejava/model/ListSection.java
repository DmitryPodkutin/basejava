package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class ListSection extends Section {
    private final List<String> description;

    public ListSection(List<String> description) {
        Objects.requireNonNull(description, "description must not be null");
        this.description = description;
    }

    public List<String> getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListSection)) return false;
        ListSection that = (ListSection) o;
        return getDescription().equals(that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDescription());
    }

    @Override
    public String toString() {
        return "SrctionList{" +
                "discription=" + description +
                '}';
    }
}
