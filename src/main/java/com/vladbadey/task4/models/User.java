package com.vladbadey.task4.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
//    @GeneratedValue(strategy=GenerationType.IDENTITY)

    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "regDate")
    private LocalDate regDate;

    @Column(name = "lastLogIn")
    private LocalDate lastLogIn;

    @Column(name = "status")
    private boolean status = true;

    public User() {

    }

    public User(int id) {
        this.id = id;
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public LocalDate getLastLogIn() {
        return lastLogIn;
    }

    public void setLastLogIn(LocalDate lastLogIn) {
        this.lastLogIn = lastLogIn;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (status != user.status) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (regDate != null ? !regDate.equals(user.regDate) : user.regDate != null) return false;
        return lastLogIn != null ? lastLogIn.equals(user.lastLogIn) : user.lastLogIn == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (regDate != null ? regDate.hashCode() : 0);
        result = 31 * result + (lastLogIn != null ? lastLogIn.hashCode() : 0);
        result = 31 * result + (status ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", regDate=" + regDate +
                ", lastLogIn=" + lastLogIn +
                ", status=" + status +
                '}';
    }
}
