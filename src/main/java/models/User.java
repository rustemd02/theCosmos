package models;

import java.util.Objects;

public class User {
    private Long id;
    private Cosmostar cosmostar;
    private String email;
    private String passwordHash;
    private String name;
    private int balance;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return balance == user.balance && Objects.equals(id, user.id) && Objects.equals(cosmostar, user.cosmostar) && Objects.equals(email, user.email) && Objects.equals(passwordHash, user.passwordHash) && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cosmostar, email, passwordHash, name, balance);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cosmostar getCosmostar() {
        return cosmostar;
    }

    public void setCosmostar(Cosmostar cosmostar) {
        this.cosmostar = cosmostar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
