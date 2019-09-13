package org.project.study.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "`study`")
public class Study {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "major_region")
    private Integer majorRegionId;

    @Column(name = "minor_region")
    private Integer minorRegionId;

    @Column(name = "study_writer_id")
    private Long studyWriterId;

    private String introduce;

    private String meta;

    @Column(name = "max_member_cnt")
    private Integer maxMemberCnt;

    @Column(name = "view_cnt")
    private Integer viewCnt;

    @Column(name = "created_at", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "start_date")
    private Date startDate;

    private Integer status;

    @Transient
    private List<Integer> categoryIds;

    @ManyToMany
    @JoinTable(
            name = "study_category",
            joinColumns = @JoinColumn(name = "study_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @BatchSize(size = 5)
    private List<Category> categories;

    @OneToOne
    @JoinColumn(name="major_region", insertable = false, updatable = false)
    private MajorRegion majorRegion;

    @OneToOne
    @JoinColumn(name="minor_region", insertable = false, updatable = false)
    private MinorRegion minorRegion;

    @PrePersist
    public void prePersist() {
        this.viewCnt = this.viewCnt == null ? 0 : this.viewCnt;
        this.status = this.status == null ? 1 : this.status;
    }
}
