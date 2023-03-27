package com.gdsc.canigraduate.domain.user;

import com.gdsc.canigraduate.domain.BaseEntity;
import com.gdsc.canigraduate.domain.user.lecture.UserLecture;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Table(name = "users")
public class User extends BaseEntity {

    private String name;

    private String classId; //학번

    private String userPw;

    @Enumerated(EnumType.STRING)
    private Department department;

    private LocalDate admissionYear;

    private Integer graduationCredit;

    private Integer presentCredit;

    private Integer semester;

    private String token;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserLecture> userLectureList = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Builder
    public User(String classId, String userPw, String name,String token, Department department) {
        this.classId = classId;
        this.userPw = userPw;
        this.name = name;
        this.token = UUID.randomUUID().toString();
        this.department = department;
    }

    public void pwUpdate(String pw) {
        this.userPw = pw;
    }
    public void setProfile(Integer credit, Integer semester){
        this.presentCredit = credit;
        this.semester = semester;
    }

    public void setDepartment(Department department){
        if(department.equals("심화컴퓨터공학전공"))
            this.department = department;
        else if(department.equals("글로벌SW융합전공"))
            this.department = department;
    }
}
