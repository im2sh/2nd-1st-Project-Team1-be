package com.gdsc.canigraduate.domain.lecture;

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
public class Lecture extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "LECTURE_ID")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private User user;

    private String semester;

    private Integer majorCredit;
    private Integer cultureCredit;
    private Integer normalCredit;

    @OneToMany(mappedBy = "lecture",cascade = CascadeType.ALL)
    private List<LectureDetail> lectureDetails = new ArrayList<>();

    @Builder
    public Lecture(String semester, Integer majorCredit, Integer cultureCredit, Integer normalCredit){
        this.semester = semester;
        this.majorCredit = majorCredit;
        this.cultureCredit = cultureCredit;
        this.normalCredit = normalCredit;
    }
}
