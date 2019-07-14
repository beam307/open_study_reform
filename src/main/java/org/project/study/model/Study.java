package org.project.study.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "`study`")
@ToString
public class Study {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "major_region")
    private Integer majorRegion;

    @Column(name = "minor_region")
    private Integer minorRegion;

    @Column(name = "study_writer_id")
    private Long studyWriterId;

    private String introduce;

    private String meta;

    @Column(name = "max_member_cnt")
    private Integer maxMemberCnt;

    @Column(name = "view_cnt")
    private Integer viewCnt;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "start_date")
    private Date startDate;

    private Integer status;

    @Transient
    private List<Integer> categories;

    @PrePersist
    public void prePersist() {
        this.viewCnt = this.viewCnt == null ? 0 : this.viewCnt;
        this.status = this.status == null ? 1 : this.status;
    }
}
