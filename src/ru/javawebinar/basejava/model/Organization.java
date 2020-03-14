package ru.javawebinar.basejava.model;

import util.DateUtil;
import util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Organization implements Serializable {
    private Link homepage;
    private List<Position> positions;
    public static final Organization DUMMY = new Organization("","",Position.DUMMY);

    public Organization(String name, String url, Position... position) {
        this(new Link(name, url), Arrays.asList(position));
    }

    public Organization(Link homepage, List<Position> positions) {
        this.homepage = homepage;
        this.positions = positions;
    }

    public Organization(String name, Position... position) {
        this(name, null, position);
    }

    public Organization() {
    }

    public Link getHomepage() {
        return homepage;
    }

    public List<Position> getPositions() {
        return positions;
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

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Position implements Serializable {
        private static final long serialVersion = 1L;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate beginDate;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate endDate;
        private String position;
        private String description;
        public static final Position DUMMY = new Position();

        public Position(LocalDate beginDate, LocalDate endDate, String position, String description) {
            Objects.requireNonNull(beginDate, "beginDate must not be null");
            Objects.requireNonNull(endDate, "endDate must not be null");
            Objects.requireNonNull(position, "position must not be null");

            this.beginDate = beginDate;
            this.endDate = endDate;
            this.position = position;
            if (Objects.isNull(description)) {
                this.description = "";
            } else this.description = description;
        }

        public Position(LocalDate beginDate, LocalDate endDate, String position) {
            this(beginDate, endDate, position, null);

        }

        public Position() {
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

