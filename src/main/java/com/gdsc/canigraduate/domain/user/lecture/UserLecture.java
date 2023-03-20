package com.gdsc.canigraduate.domain.user.lecture;

import com.gdsc.canigraduate.domain.BaseEntity;
import com.gdsc.canigraduate.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserLecture extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LECTURE_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private User user;

    private String semester;
    private Integer majorCredit;
    private Integer cultureCredit;
    private Integer normalCredit;

    @OneToMany(mappedBy = "userLecture", cascade = CascadeType.ALL)
    private List<UserLectureDetail> lectureDetails = new ArrayList<>();


    public UserLecture(User user){
        this.user = user;
    }


    public void InitLecture(String semester){
        this.semester = semester;
    }

    public void YearToString(Integer year, String semester){
        this.semester = year.toString() + semester;
    }

    public void addUserLectureDetail(UserLectureDetail userLectureDetail){
        lectureDetails.add(userLectureDetail);
    }


}
