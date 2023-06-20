package com.hanchaoyang.ol;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * 操作日志记录
 *
 * @author 韩朝阳
 * @since 2023/6/20
 */
public class OperationLogRecord {

    private LocalDateTime dateTime;

    private Method method;

    private Map<String, Object> args;

    private Object result;

    private String content;

    private Long duration;

    public OperationLogRecord() {

    }

    public OperationLogRecord(LocalDateTime dateTime, Method method, Map<String, Object> args, Object result, String content, Long duration) {
        this.dateTime = dateTime;
        this.method = method;
        this.args = args;
        this.result = result;
        this.content = content;
        this.duration = duration;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Method getMethod() {
        return this.method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Map<String, Object> getArgs() {
        return this.args;
    }

    public void setArgs(Map<String, Object> args) {
        this.args = args;
    }

    public Object getResult() {
        return this.result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getDuration() {
        return this.duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "OperationLogRecord{" +
                "dateTime=" + this.dateTime +
                ", method=" + this.method +
                ", args=" + this.args +
                ", result=" + this.result +
                ", content='" + this.content + '\'' +
                ", duration=" + this.duration +
                '}';
    }

}