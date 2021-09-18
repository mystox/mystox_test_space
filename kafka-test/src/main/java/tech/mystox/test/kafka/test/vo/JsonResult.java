
package tech.mystox.test.kafka.test.vo;

public class JsonResult {
    private String info = "请求成功！";
    private Boolean success = true;
    private Object data;
    private int count;
    private Object otherInfo;

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public JsonResult() {
        this.printResult();
    }

    public JsonResult(Object data) {
        this.data = data;
        this.printResult();
    }

    public JsonResult(Object data, int count) {
        this.data = data;
        this.count = count;
        this.printResult();
    }

    public JsonResult(String info, Boolean success) {
        this.info = info;
        this.success = success;
        this.printResult();
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public Object getOtherInfo() {
        return this.otherInfo;
    }

    public void setOtherInfo(Object otherInfo) {
        this.otherInfo = otherInfo;
    }

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String toString() {
        return "JsonResult{info=" + this.info + ", success=" + this.success + ", data=" + this.data + '}';
    }

    private void printResult() {
    }
}
