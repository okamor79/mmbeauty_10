package com.okamor.mmbeauty.model;

import com.okamor.mmbeauty.model.enums.UserRole;
import com.okamor.mmbeauty.model.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "Client")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@ToString
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String phone;

    private String name;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    private Date registered;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    private Date lastEdit;

}
