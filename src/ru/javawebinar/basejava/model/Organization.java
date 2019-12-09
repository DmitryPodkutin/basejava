package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.Objects;

public class Organization {
    private final String title;
    private final String titleLink;
    private final LocalDate beginDate;
    private final LocalDate endDate;
    private final String description;

    public Organization(String title, String titleLink, LocalDate beginDate, LocalDate endDate, String description) {
        Objects.requireNonNull(title, "title must not be null");
        Objects.requireNonNull(beginDate, "beginDate must not be null");
        Objects.requireNonNull(endDate, "endDate must not be null");
        this.title = title;
        this.titleLink = titleLink;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.description = description;
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
        if (!(o instanceof Organization)) return false;
        Organization that = (Organization) o;
        return title.equals(that.title) &&
                getTitleLink().equals(that.getTitleLink()) &&
                getBeginDate().equals(that.getBeginDate()) &&
                getEndDate().equals(that.getEndDate()) &&
                getDescription().equals(that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, getTitleLink(), getBeginDate(), getEndDate(), getDescription());
    }

    @Override
    public String toString() {
        return "\n{" +
                "title='" + title + '\'' +
                ", titleLink='" + titleLink + '\'' +
                ", yearOfBeginning='" + beginDate + '\'' +
                ", yearOfEnding='" + endDate + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
