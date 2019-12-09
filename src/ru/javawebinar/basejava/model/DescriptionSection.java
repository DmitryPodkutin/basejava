package ru.javawebinar.basejava.model;

import java.util.Objects;

public class DescriptionSection extends Section {
    private final String description;

    public DescriptionSection(String description) {
        Objects.requireNonNull(description, "description must not be null");
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "SectionDescription{" +
                "description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DescriptionSection)) return false;
        DescriptionSection that = (DescriptionSection) o;
        return getDescription().equals(that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDescription());
    }
}
