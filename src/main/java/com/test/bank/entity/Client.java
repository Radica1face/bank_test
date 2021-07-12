package com.test.bank.entity;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )


    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @NotEmpty(message = "Полное имя не должно быть пустым")
    @Size(min = 5, max = 100, message = "Полное имя должно быть не менее 5 и не более 100 символов")
    @Column(name = "fullname")
    private String fullName;

    @NotEmpty(message = "Номер телефона не должен быть пустым")
    @Size(min = 11, max = 11, message = "Номер телефона должен состоять из 11 цифр")
    @Column(name = "phone_number")
    private String phone;

    @Email(message = "Email должен быть действительным")
    @Column(name = "email")
    private String email;

    @NotEmpty(message = "Номер паспорта не должен быть пустым")
    @Size(min = 10, max = 10, message = "Номер паспорта должен состоять из 10 цифр")
    @Column(name = "passport_number")
    private String passportNumber;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToOne (cascade=CascadeType.ALL)
    @JoinColumn (name="bank_id")
    private Bank bank;

    @OneToMany (mappedBy="client", fetch=FetchType.EAGER)
    private List<CreditSupply> supplies;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public List<CreditSupply> getSupplies() {
        return supplies;
    }

    public void setSupplies(List<CreditSupply> supplies) {
        this.supplies = supplies;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", bank=" + bank +
                '}';
    }
}
