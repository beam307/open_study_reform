package org.project.study.model;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
@Data
@Table(name = "`category`")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

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
