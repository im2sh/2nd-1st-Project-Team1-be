package com.gdsc.canigraduate.domain.user;

import com.gdsc.canigraduate.domain.BaseEntity;
import com.gdsc.canigraduate.domain.BooleanToYNConverter;
import com.gdsc.canigraduate.domain.Department;
import com.gdsc.canigraduate.domain.user.lecture.UserLecture;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by im2sh
 */

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

    private Integer admissionYear;

    private Integer graduationCredit;

    private Integer presentCredit;

    private Integer semester;

    private String token;

    @Convert(converter = BooleanToYNConverter.class)
    private boolean isUpload;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserLecture> userLectureList = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Builder
    public User(String classId, String userPw, String name, String token, Department department, Integer admissionYear) {
        this.classId = classId;
        this.userPw = userPw;
        this.name = name;
        this.token = UUID.randomUUID().toString();
        this.department = department;
        this.admissionYear = admissionYear;
    }

    public void pwUpdate(String pw) {
        this.userPw = pw;
    }

    public void setProfile(Integer credit, Integer semester) {
        this.presentCredit = credit;
        this.semester = semester;
    }

    public void addLecture(UserLecture userLecture){
        this.userLectureList.add(userLecture);
    }


    public void setDepartment(Department department) {
        if (department.equals("심화컴퓨터공학전공"))
            this.department = department;
        else if (department.equals("글로벌SW융합전공"))
            this.department = department;
    }

    public void userUpload(boolean yn){
        if(yn == true)
            this.isUpload = true;
        else
            this.isUpload = false;
    }

    public void subCredit(Integer credit){
        this.presentCredit -= credit;
    }
    public void addCredit(Integer credit){
        this.presentCredit += credit;
    }

    public void addSemester(Integer semester){
        this.semester += semester;
    }

    public void subSemester(Integer semester){
        this.semester -= semester;
    }
}
