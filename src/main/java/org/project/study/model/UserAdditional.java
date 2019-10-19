package org.project.study.model;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import org.project.study.converter.JsonNodeConverter;

import javax.persistence.*;

@Entity
@Data
@Table(name = "`user_additional`")
public class UserAdditional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Convert(converter = JsonNodeConverter.class)
    private JsonNode categories;

    @Column(name = "major_region")
    private Long majorRegion;

    @Column(name = "minor_region")
    private Long minorRegion;

    private String introduce;

}
