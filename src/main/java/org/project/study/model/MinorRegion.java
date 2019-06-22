package org.project.study.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Getter
@Setter
@Table(name = "`minor_region`")
@ToString
public class MinorRegion {

    @Id
    private Integer id;

    private String name;

    @Transient
    @JsonIgnore
    public Integer getCode() {
        return this.id/100;
    }

}
