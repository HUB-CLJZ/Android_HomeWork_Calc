package com.example.retrofitdemonew;

public class FileBean {

    @Override
    public String toString() {
        return "FileBean{" +
                "success=" + success +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * success : true
     * code : 10000
     * message : 上传成功.文件路径为：E:\codes\Idear\SobNetworkCourseServer\target\classes\sobUpload\0.png
     * data : null
     */

    private boolean success;
    private int code;
    private String message;
    private Object data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
