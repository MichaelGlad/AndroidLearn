package net.glm.retrofitworkandroidacademy;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ImageSearchResult {

    @SerializedName("totalHits")
    private Integer totalHits;
    @SerializedName("hits")
    private List<Image> images = null;
    @SerializedName("total")
    private Integer total;

    public Integer getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(Integer totalHits) {
        this.totalHits = totalHits;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

}
