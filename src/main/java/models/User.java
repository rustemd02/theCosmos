package models;

public class User {
    private Long id;
    private Long cosmostarId;
    private String email;
    private String passwordHash;
    private String name;
    private int balance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCosmostarId() {
        return cosmostarId;
    }

    public void setCosmostarId(Long cosmostarId) {
        this.cosmostarId = cosmostarId;
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
