package ru.javawebinar.basejava.model;

import util.DateUtil;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Organization implements Serializable {
    private final Link homepage;
    private final List<Position> positions;

    public Organization(String name, String url, Position... position) {
        this(new Link(name, url), Arrays.asList(position));
    }

    public Organization(String name, Position... position) {
        this(name, null, position);
    }

    public Organization(Link homepage, List<Position> positions) {
        this.homepage = homepage;
        this.positions = positions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return homepage.equals(that.homepage) &&
                positions.equals(that.positions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homepage, positions);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "homepage=" + homepage +
                ", positions=" + positions +
                '}';
    }

    public static class Position implements Serializable {
        private static final long serialVersion = 1L;
        private final LocalDate beginDate;
        private final LocalDate endDate;
        private final String position;
        private final String description;

        public Position(LocalDate beginDate, LocalDate endDate, String position, String description) {
            Objects.requireNonNull(beginDate, "beginDate must not be null");
            Objects.requireNonNull(endDate, "endDate must not be null");
            Objects.requireNonNull(position, "position must not be null");
            this.beginDate = beginDate;
            this.endDate = endDate;
            this.position = position;
            this.description = description;
        }

        public Position(LocalDate beginDate, LocalDate endDate, String position) {
            this(beginDate, endDate, position, null);
        }

        public LocalDate getBeginDate() {
            return beginDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public String getPosition() {
            return position;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return "Position{" +
                    "beginDate=" + DateUtil.dateFormat(beginDate) +
                    ", endDate=" + DateUtil.dateFormat(endDate) +
                    ", position='" + position + '\'' +
                    ", description='" + description + '\'' +
                    '}' + "\n";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position1 = (Position) o;
            return beginDate.equals(position1.beginDate) &&
                    endDate.equals(position1.endDate) &&
                    position.equals(position1.position) &&
                    description.equals(position1.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(beginDate, endDate, position, description);
        }
    }
}

