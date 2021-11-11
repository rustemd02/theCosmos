package models;

import java.util.Date;
import java.util.Objects;

public class Seance {
    private Long id;
    private Movie movie;
    private Date time;
    private int ticketsAmount;
    private int price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seance seance = (Seance) o;
        return ticketsAmount == seance.ticketsAmount && price == seance.price && Objects.equals(id, seance.id) && Objects.equals(movie, seance.movie) && Objects.equals(time, seance.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movie, time, ticketsAmount, price);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getTicketsAmount() {
        return ticketsAmount;
    }

    public void setTicketsAmount(int ticketsAmount) {
        this.ticketsAmount = ticketsAmount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
