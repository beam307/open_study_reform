package org.project.study.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "`user`")
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String pwd;

    private String name;

    private String nickname;

    private Integer birth;

    private String gender;

    private Integer admin;
}
