package org.project.study.model;

import lombok.Data;

@Data
public class SearchDTO {

    private Integer page;

    private String filter;

    private Integer count;

    private String majorRegion;

    private String minorRegion;

    private String majorCategory;

    private String minorCategory;

}
