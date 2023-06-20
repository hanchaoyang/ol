package com.hanchaoyang.ol;

import java.time.LocalDateTime;

/**
 * 操作日志记录
 *
 * @author 韩朝阳
 * @since 2023/6/20
 */
public class OperationLogRecord {

    private LocalDateTime dateTime;

    private String content;

    private Long duration;

    public OperationLogRecord() {

    }

    public OperationLogRecord(LocalDateTime dateTime, String content, Long duration) {
        this.dateTime = dateTime;
        this.content = content;
        this.duration = duration;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
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
                ", content='" + this.content + '\'' +
                ", duration=" + this.duration +
                '}';
    }

}