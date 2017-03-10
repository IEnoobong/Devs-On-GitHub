package co.enoobong.devsongithub.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by owner on 5/3/2017.
 */

public class ApiResponse {
    @SerializedName("total_count")
    private Integer totalCount;
    @SerializedName("incomplete_results")
    private Boolean incompleteResults;
    @SerializedName("items")
    private List<Developer> developers;

    public ApiResponse() {
    }

    public List<Developer> getDevelopers() {
        return developers;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public Boolean getIncompleteResults() {
        return incompleteResults;
    }
}
