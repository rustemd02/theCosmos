package models;

import java.util.Objects;

public class Auth {
    private Long id;
    private User user;
    private String cookieValue;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auth auth = (Auth) o;
        return Objects.equals(id, auth.id) && Objects.equals(user, auth.user) && Objects.equals(cookieValue, auth.cookieValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, cookieValue);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCookieValue() {
        return cookieValue;
    }

    public void setCookieValue(String cookieValue) {
        this.cookieValue = cookieValue;
    }
}
