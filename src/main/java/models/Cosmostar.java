package models;

import java.util.Objects;

public class Cosmostar {
    private long id;
    private int points;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cosmostar cosmostar = (Cosmostar) o;
        return id == cosmostar.id && points == cosmostar.points;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, points);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
