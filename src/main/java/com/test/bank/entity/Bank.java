package com.test.bank.entity;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "banks")
public class Bank {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "title")
    private String title;

    @OneToMany (mappedBy="bank", fetch=FetchType.EAGER)
    private List<Client> clients;

    @OneToMany (mappedBy="bank", fetch=FetchType.EAGER)
    private List<Credit> credits;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Credit> getCredits() {
        return credits;
    }

    public void setCredits(List<Credit> credits) {
        this.credits = credits;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return Objects.equals(id, bank.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Bank{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
