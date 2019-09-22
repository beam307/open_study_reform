package org.project.study.model;

import lombok.Data;

@Data
public class SearchDTO {

    private Integer page;

    private String filter;

    private Integer count;

    private Integer region;

    private Integer category;

    public Integer getPage() {
        if (this.page == null || this.page < 1) {
            return 0;
        }
        return this.page - 1;
    }

    public Integer getCount() {
        if (this.count == null) {
            return 6;
        }
        return this.count;
    }

    public String getFilter() {
        if(this.filter == null) {
            return "createdAt";
        }
        switch (this.filter) {
            case "r":
                return "viewCnt";
            case "l":
            case "p":
            default:
                return "createdAt";
        }
    }

}
