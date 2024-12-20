package tech.mystox.test.fastjson.pojo;

/**
 * Created by mystox on 2023/7/11, 10:47.
 * company:
 * description:
 * update record:
 */
public class CameraVO {
    private String liveUrl;

    public String getLiveUrl() {
        return liveUrl;
    }

    public void setLiveUrl(String liveUrl) {
        this.liveUrl = liveUrl;
    }

    @Override
    public String toString() {
        return "CameraVO{" +
                "liveUrl='" + liveUrl + '\'' +
                '}';
    }
}
