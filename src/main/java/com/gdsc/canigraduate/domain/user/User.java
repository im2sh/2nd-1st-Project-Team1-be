package com.gdsc.canigraduate.domain.user;

import com.gdsc.canigraduate.domain.BaseEntity;
import com.gdsc.canigraduate.domain.Department;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User extends BaseEntity {

    private String name;

    private String user_id;

    private String user_pw;

    @Enumerated(EnumType.STRING)
    private Department department;

    private LocalDate admissionYear;

    private Integer graduationCredit;

    private Integer presentCredit;

    private Integer semester;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
