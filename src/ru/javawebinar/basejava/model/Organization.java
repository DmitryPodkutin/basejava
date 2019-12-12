package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.Objects;

public class Organization {
    private final String title;
    private final String titleLink;
    private final LocalDate beginDate;
    private final LocalDate endDate;
    private final String position;
    private final String description;


    public Organization(String title, String titleLink, LocalDate beginDate, LocalDate endDate, String position, String description) {
        Objects.requireNonNull(title, "title must not be null");
        Objects.requireNonNull(beginDate, "beginDate must not be null");
        Objects.requireNonNull(endDate, "endDate must not be null");
        Objects.requireNonNull(position, "position must not be null");
        this.title = title;
        this.titleLink = titleLink;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.description = description;
        this.position = position;
    }

    public Organization(String title, String titleLink, LocalDate beginDate, LocalDate endDate, String position) {
        this(title, titleLink, beginDate, endDate, position, null);
    }

    public String getTitleLink() {
        return titleLink;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(titleLink, that.title) &&
                Objects.equals(beginDate, that.beginDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(position, that.position) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, titleLink, beginDate, endDate, position, description);
    }

    @Override
    public String toString() {
        return "\ntitle= " + title + '\'' + "\n" +
                "titleLink='" + titleLink + '\'' + "\n" +
                "beginDate=" + beginDate + "\n" +
                "endDate=" + endDate + "\n" +
                "position=" + position + "\n" +
                "description='" + description + '\'' +
                '}';
    }
}
