package com.talento_tech.BolsaEmpleo.dto;

public class ResponseDto {

    private String message;
    private Object data;
    private int Status;

    public ResponseDto(String message, Object data, int status) {
        this.message = message;
        this.data = data;
        this.Status = status;
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

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        this.Status = status;
    }

}
