package tech.mystox.test.fastjson.pojo;

import java.time.LocalDateTime;

/**
 * Created by mystox on 2024/2/26, 23:13.
 * company:
 * description:
 * update record:
 */
public class LocalDateTimeDTO {
    private LocalDateTime liveStartTime;

    public LocalDateTime getLiveStartTime() {
        return liveStartTime;
    }

    public void setLiveStartTime(LocalDateTime liveStartTime) {
        this.liveStartTime = liveStartTime;
    }

    @Override
    public String toString() {
        return "LocalDateTimeDTO{" +
                "liveStartTime=" + liveStartTime +
                '}';
    }
}
