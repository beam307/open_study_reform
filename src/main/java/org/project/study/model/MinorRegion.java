package org.project.study.model;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "`minor_region`")
@Data
public class MinorRegion {

    @Id
    private Integer id;

    private String name;

    @Transient
    @JsonIgnore
    public Integer getCode() {
        return this.id / 100;
    }

}
