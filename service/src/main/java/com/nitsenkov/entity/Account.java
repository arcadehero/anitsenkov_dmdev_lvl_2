package com.nitsenkov.entity;

import com.nitsenkov.entity.enums.AccountType;
import com.nitsenkov.entity.enums.Currency;
import com.nitsenkov.entity.enums.AccountStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@ToString(exclude = {"cards", "payments"})
@EqualsAndHashCode(exclude = {"cards", "payments"})
@AllArgsConstructor
@Builder
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    private String number;
    private BigDecimal amount;
    private Currency currency;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "users_id")
    private User user;

    @Builder.Default
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Card> cards = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "senderAccount", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Payment> payments = new HashSet<>();

    public void addCard(Card card) {
        cards.add(card);
        card.setAccount(this);
    }

    public void addPayment(Payment payment) {
        payments.add(payment);
        payment.setAccount(this);
    }
}
