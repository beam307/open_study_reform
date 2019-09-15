package org.project.study.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "`major_region`")
@ToString
public class MajorRegion {

    @Id
    private Integer id;

    private String name;

}
