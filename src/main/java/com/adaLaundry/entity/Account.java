package com.adaLaundry.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@Entity
@Table(name = "Accounts")
public class Account {

    @Id
    @Column(name="Username")
    private String username;

    @JsonIgnore
    @Column(name="Password")
    private String password;

    @Column(name="Role")
    private String role;
}
