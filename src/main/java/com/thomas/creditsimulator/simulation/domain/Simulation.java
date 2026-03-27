package com.thomas.creditsimulator.simulation.domain;

import com.thomas.creditsimulator.customer.domain.DocumentType;
import com.thomas.creditsimulator.product.domain.Product;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "simulations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Simulation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name ="product_id",nullable = false)
    private Product product;

    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    private String documentNumber;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private BigDecimal monthlyIncome;

    private BigDecimal amount;

    private Integer termMonths;

    private BigDecimal interestRate;

    private BigDecimal monthlyPayment;

    private BigDecimal totalInterest;

    private BigDecimal totalPayment;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
