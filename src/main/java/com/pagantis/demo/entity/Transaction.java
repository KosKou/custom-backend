package com.pagantis.demo.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Document(collection = "transaction")
public class Transaction {
    @Id
    private String id;

    @NotEmpty
    @NotNull
    private String from;
    @NotEmpty
    @NotNull
    private String to;
    private Long amount;
    @NotEmpty
    private String state;

    private String message;

    @CreatedDate
    private Date createdDate;
//    Constructor
    public Transaction() {
    }

    public Transaction(@NotEmpty @NotNull String from, @NotEmpty @NotNull String to, Long amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    //Methods
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
