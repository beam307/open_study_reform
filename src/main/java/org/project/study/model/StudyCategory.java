package org.project.study.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "`study_category`")
@Data
@NoArgsConstructor(force = true)
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

    public StudyCategory(Long studyId) {
        this.studyId = studyId;
    }
}
