package com.okamor.mmbeauty.model;

import com.okamor.mmbeauty.model.enums.OrderStatus;
import javax.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "Orders")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn
    private Client client;

    @OneToOne
    @JoinColumn
    private Course course;

    private String orderId;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    private Date dateBuy;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    private Date datePayment;

    private boolean payCheck;

    private String checkCode;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.ORDER_WAIT;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    private Date expireDate;

}
