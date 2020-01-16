package com.pagantis.demo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Document(collection = "wallet")
public class Wallet {

    @Id
    private String id;
    @NotNull
    @NotEmpty
    private String userMail;

    private Long balance;

//    Constructor
    public Wallet() {
    }

    public Wallet(@NotNull @NotEmpty String userMail, Long balance) {
        this.userMail = userMail;
        this.balance = balance;
    }

    //    Getter & Setter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }
}
