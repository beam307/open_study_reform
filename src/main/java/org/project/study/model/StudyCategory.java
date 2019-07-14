package org.project.study.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "`study_category`")
@ToString
public class StudyCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "study_id")
    private Long studyId;

    @Column(name = "category_id")
    private Integer categoryId;


    public StudyCategory(Long studyId, Integer categoryId) {
        this.studyId = studyId;
        this.categoryId = categoryId;
    }
}
