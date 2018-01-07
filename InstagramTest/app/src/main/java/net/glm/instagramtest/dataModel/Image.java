package net.glm.instagramtest.dataModel;

/**
 * Created by Michael on 22/12/2017.
 */

public class Image {
    private Standard_resolution standardResolution;

    public Standard_resolution getStandardResolution() {
        return standardResolution;
    }

    public void setStandardResolution(Standard_resolution standardResolution) {
        this.standardResolution = standardResolution;
    }

    public class Standard_resolution {

        private String url;

        public String getUrl() {
            return url;
        }
    }
}
