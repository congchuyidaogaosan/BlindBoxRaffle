package com.mysterybox.entity;

import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "user")
public class User extends BaseEntity {
    private String username;
    private String password;
    private String nickname;
    private String phone;
    private String role;
    private BigDecimal balance;
} 