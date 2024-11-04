package com.nitsenkov.entity;

import com.nitsenkov.entity.enums.AccountStatus;
import com.nitsenkov.entity.enums.AccountType;
import com.nitsenkov.entity.enums.Currency;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@ToString(exclude = {"cards", "payments"})
@EqualsAndHashCode(exclude = {"cards", "payments"})
@AllArgsConstructor
@Builder
@Entity
public class Account implements BaseEntity<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Version
    private Long version;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    private String number;
    private BigDecimal amount;
    private Currency currency;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "users_id")
    private User user;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Card> cards = new ArrayList<>();

    @OneToMany(mappedBy = "senderAccount", cascade = CascadeType.ALL)
    private List<Payment> payments = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
        card.setAccount(this);
    }

    public void addPayment(Payment payment) {
        payments.add(payment);
        payment.setAccount(this);
    }
}
