package com.gdsc.canigraduate.domain.user;

import com.gdsc.canigraduate.domain.Department;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserGraduationInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GRADUATION_ID")
    private Long id;


    public boolean basic;
    public boolean coreSociety;
    public boolean coreScience;
    public boolean culture;
    public boolean english;
    public boolean intern;
    public boolean consulting;

    @Builder
    public UserGraduationInfo(boolean basic, boolean coreSociety, boolean coreScience, boolean culture, boolean english, boolean intern, boolean consulting) {
        this.basic = basic;
        this.coreSociety = coreSociety;
        this.coreScience = coreScience;
        this.culture = culture;
        this.english = english;
        this.intern = intern;
        this.consulting = consulting;
    }
}
