package cn.hnisi.wx.core.io;

public class ResponseEntity<T>{
    private String errcode;
    private String errmsg;
    private T data;

    public ResponseEntity(){
        ResponseStatus status = ResponseStatus.OK;
        this.errcode = status.getErrcode();
        this.errmsg = status.getErrmsg();
    }

    public ResponseEntity(T data) {
        this.data = data;
        ResponseStatus status = ResponseStatus.OK;
        this.errcode = status.getErrcode();
        this.errmsg = status.getErrmsg();
    }

    public ResponseEntity(ResponseStatus status){
        this.errcode = status.getErrcode();
        this.errmsg = status.getErrmsg();
    }

    public ResponseEntity(ResponseStatus status, String errmsg){
        this.errcode = status.getErrcode();
        this.errmsg = errmsg;
    }

    public ResponseEntity(T data, ResponseStatus status, String errmsg){
        this.data = data;
        this.errcode = status.getErrcode();
        this.errmsg = errmsg;
    }

    public String getErrcode() {
        return errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public T getData() {
        return data;
    }
}

