package org.project.study.model;

import com.google.common.collect.ImmutableList;
import lombok.Data;

@Data
public class SearchDTO {

    private Integer page;

    private String filter;

    private Integer count;

    private Integer majorRegion;

    private Integer majorCategory;

    public Integer getPage() {
        if (this.page < 1) {
            return 0;
        }
        return this.page - 1;
    }

    public Integer getCount() {
        if (ImmutableList.of(1, 2, 3, 4).contains(this.count)) {
            return this.count * 9;
        }
        return 9;
    }

    public String getFilter() {
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
