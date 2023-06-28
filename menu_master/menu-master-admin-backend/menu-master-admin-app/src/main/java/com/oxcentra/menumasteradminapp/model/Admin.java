package com.oxcentra.menumasteradminapp.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="admin")
public class Admin {
    @Id
    @Column(name="id")
    private int id;

    @Column(name="user_name")
    private String userName;

    @Column(name="phone")
    private String phone;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="profile_picture")
    private String profilePicture;

    @Column(name="type")
    private String userType;
}
