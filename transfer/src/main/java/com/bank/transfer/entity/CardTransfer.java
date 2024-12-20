package com.bank.transfer.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "card_transfer", schema = "transfer")
@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CardTransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "card_number", unique = true)
    private Long cardNumber;

    @NotNull
    private BigDecimal amount;

    private String purpose;

    @NotNull
    @Column(name = "card_details_id")
    private Long cardDetailsId;


    public CardTransfer(Long cardNumber, BigDecimal amount, String purpose, Long cardDetailsId) {
        this.cardNumber = cardNumber;
        this.amount = amount;
        this.purpose = purpose;
        this.cardDetailsId = cardDetailsId;
    }

}
