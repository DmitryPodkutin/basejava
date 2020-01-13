package ru.javawebinar.basejava.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement
public class DescriptionSection extends Section {
    private static final long serialVersion = 1L;
    private String description;

    public DescriptionSection(String description) {
        Objects.requireNonNull(description, "description must not be null");
        this.description = description;
    }

    public DescriptionSection() {
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
        if (o == null || getClass() != o.getClass()) return false;
        DescriptionSection that = (DescriptionSection) o;
        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}
