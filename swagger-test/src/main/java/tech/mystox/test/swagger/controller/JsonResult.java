/**
 * *****************************************************
 * Copyright (C) Kongtrolink techology Co.ltd - All Rights Reserved
 *
 * This file is part of Kongtrolink techology Co.Ltd property.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 ******************************************************
 */
package tech.mystox.test.swagger.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@ApiModel("统一响应实体")
public class JsonResult<T> implements Serializable{

    @ApiModelProperty("请求信息")
    private String info = "请求成功！";
    @ApiModelProperty("请求响应信息")
    private String message = "请求成功";
    @ApiModelProperty("是否请求成功")
    private Boolean success = true;
    @ApiModelProperty("响应内容实体")
    private T data;

    public JsonResult() {
        printResult();
    }

    public JsonResult(T data) {
        this.data = data;
//        printResult();
    }

    public JsonResult(String info, Boolean success) {
        this.info = info;
        this.message = info;
        this.success = success;
//        printResult();
    }

    /**
    * @Description
    * <p>
      注释:  新加返回参数格式类型，msg+data+success
    * <p>
    * @Date 16:09 2020/10/19
    * @Param [info, success, data]
    * @return
    * @Author 吴又生
    */
    public JsonResult(String info, Boolean success, T data) {
        this.info = info;
        this.success = success;
        this.data = data;
    }

    public String getInfo() {
        return info;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public Boolean getSuccess()
    {
        return success;
    }

    public void setInfo(String info) {
        this.info = info;
        this.message = info;
    }

    public Boolean isSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;

    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "JsonResult{" + "info=" + info + ", success=" + success + ", data=" + data + '}';
    }
    
    private void printResult() {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (data == null) {
            System.out.println(sdf.format(new Date())+" >> "+this);
            return;
        }
        if (data.toString().contains("Tier") == false && data.toString().contains("DeviceType") == false) {
            System.out.println(sdf.format(new Date())+" >> "+this);
        }
    }

}
