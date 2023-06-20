package com.hanchaoyang.ol;

/**
 * 操作日志收集器
 *
 * @author 韩朝阳
 * @since 2023/6/16
 */
public interface OperationLogCollector {

    void collect(OperationLogRecord record);

}