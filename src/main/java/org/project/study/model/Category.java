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
@Table(name = "`category`")
@ToString
public class Category {

    @Id
    private Integer id;

    private String name;

    @Transient
    @JsonIgnore
    public String getTitle() {
        String title = "";
        switch (this.id / 100) {

            case 0:
                title = "영어";
                break;
            case 1:
                title = "외국어";
                break;
            case 2:
                title = "IT";
                break;
            case 3:
                title = "교양";
                break;
            case 4:
            default:
                title = "공무원";
                break;
        }

        return title;
    }
}
