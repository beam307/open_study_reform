package org.project.study.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @Column(name = "view_cnt", insertable = false)
    private Integer viewCnt;

    @Column(name = "created_at", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "start_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date startDate;

    @Column(name="status", insertable = false)
    private Integer status;

    @Transient
    private Map<String, Object> writer;

    @Transient
    private List<Integer> categoryIds;

    @ManyToMany
    @JoinTable(
            name = "study_category",
            joinColumns = @JoinColumn(name = "study_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @BatchSize(size = 20)
    private List<Category> categories;

    @OneToOne
    @JoinColumn(name="major_region", insertable = false, updatable = false)
    private MajorRegion majorRegion;

    @OneToOne
    @JoinColumn(name="minor_region", insertable = false, updatable = false)
    private MinorRegion minorRegion;

}
