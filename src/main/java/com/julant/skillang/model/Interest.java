package com.julant.skillang.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "interests")
public class Interest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte interest_id;

    @Column(name = "interest_name",nullable = false, unique = true)
    private String interest_name;

    public Interest() {

    }

    public Interest(Byte interest_id, String interest_name) {
        this.interest_id = interest_id;
        this.interest_name = interest_name;
    }

    public Byte getInterest_id() {
        return interest_id;
    }

    public void setInterest_id(Byte interest_id) {
        this.interest_id = interest_id;
    }

    public String getInterest_name() {
        return interest_name;
    }

    public void setInterest_name(String interest_name) {
        this.interest_name = interest_name;
    }

    @Override
    public String toString() {
        return "Interest{" +
                "interest_id=" + interest_id +
                ", interest_name='" + interest_name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interest interest = (Interest) o;
        return Objects.equals(interest_id, interest.interest_id) && Objects.equals(interest_name, interest.interest_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(interest_id, interest_name);
    }
}
