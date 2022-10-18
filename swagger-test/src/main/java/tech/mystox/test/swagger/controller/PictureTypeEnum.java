package tech.mystox.test.swagger.controller;

/**
 * Created by mystox on 2021/12/9, 10:13.
 * company:
 * description:
 * update record:
 */
public enum PictureTypeEnum {
    LEAD_ELECTRIC("引电图片"), POWER_ENV_ROOM("消控机房图片");

    private final String picName;

    public String getPicName() {
        return picName;
    }

    PictureTypeEnum(String picName) {
        this.picName = picName;
    }
}
