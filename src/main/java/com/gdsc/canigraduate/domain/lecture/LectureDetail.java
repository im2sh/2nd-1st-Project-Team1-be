package com.gdsc.canigraduate.domain.lecture;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LectureDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="DETAIL_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="LECTURE_ID")
    private Lecture lecture;

    private String lectureName;

    private Integer credit;

    @Enumerated(EnumType.STRING)
    private LectureType lectureType;
}
