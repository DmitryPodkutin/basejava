package ru.javawebinar.basejava.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@XmlRootElement
public class ListSection extends Section {
    private static final long serialVersion = 1L;
    private List<String> items;
    public static final ListSection DUMMY = new ListSection(Collections.singletonList(""));

    public ListSection(List<String> items) {
        Objects.requireNonNull(items, "items must not be null");
        this.items = items;
    }

    public ListSection(String[] items) {
        List<String> list = Arrays.stream(items)
                .filter(s -> s.trim().length()!=0)
                .collect(Collectors.toList());
        this.items = (list);
    }

    public List<String> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return items.equals(that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items);
    }

    @Override
    public String toString() {
        return items.toString() ;
    }
}
