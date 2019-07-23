package com.example.demo.util;

import com.google.appengine.repackaged.org.codehaus.jackson.annotate.JsonIgnore;
import com.google.gson.Gson;

public class JsonResponse {
    private String message;
    private int status;
    private Object data;
    @JsonIgnore
    private static Gson gson = new Gson();

    public JsonResponse() {
    }

    public JsonResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public JsonResponse(String message, int status, Object data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public JsonResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public JsonResponse setStatus(int status) {
        this.status = status;
        return this;
    }

    public Object getData() {
        return data;
    }

    public JsonResponse setData(Object data) {
        this.data = data;
        return this;
    }

    public String toJsonString(){
        return gson.toJson(this);
    }
}
