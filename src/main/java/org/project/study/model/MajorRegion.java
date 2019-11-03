package org.project.study.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "`major_region`")
public class MajorRegion {

    @Id
    private Integer id;

    private String name;

}
