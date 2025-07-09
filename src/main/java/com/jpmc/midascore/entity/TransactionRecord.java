package com.jpmc.midascore.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.*;


@Entity
public class TransactionRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float amount;

    @Column
    private float incentive;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private UserRecord sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private UserRecord recipient;

    protected TransactionRecord() {}

    public TransactionRecord(float amount, UserRecord sender, UserRecord recipient) {
        this.amount = amount;
        this.sender = sender;
        this.recipient = recipient;
    }

    public float getAmount() {
        return amount;
    }

    public UserRecord getSender() {
        return sender;
    }

    public UserRecord getRecipient() {
        return recipient;
    }

    public void setIncentive(float incentive) {
        this.incentive = incentive;
    }
}

