package net.glm.retrofitworkandroidacademy2.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImageSearchResult {

    @SerializedName("totalHits")
    private Integer totalHits;
    @SerializedName("hits")
    private List<Hit> hits = null;
    @SerializedName("total")
    private Integer total;

    public Integer getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(Integer totalHits) {
        this.totalHits = totalHits;
    }

    public List<Hit> getHits() {
        return hits;
    }

    public void setHits(List<Hit> hits) {
        this.hits = hits;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

}
