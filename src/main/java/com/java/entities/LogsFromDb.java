package com.java.entities;

import java.sql.Timestamp;

public class LogsFromDb {
	private String logId;
	private String level;
	private String message;
	private String resouceId;
	private Timestamp timestamp;
	private String traceId;
	private String spanId;
	private String commit;
	private String parentResourceId;
	public LogsFromDb(String logId, String level, String message, String resouceId, Timestamp timestamp, String traceId,
			String spanId, String commit, String parentResourceId) {
		super();
		this.logId = logId;
		this.level = level;
		this.message = message;
		this.resouceId = resouceId;
		this.timestamp = timestamp;
		this.traceId = traceId;
		this.spanId = spanId;
		this.commit = commit;
		this.parentResourceId = parentResourceId;
	}
	public String getLogId() {
		return logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getResouceId() {
		return resouceId;
	}
	public void setResouceId(String resouceId) {
		this.resouceId = resouceId;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public String getTraceId() {
		return traceId;
	}
	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}
	public String getSpanId() {
		return spanId;
	}
	public void setSpanId(String spanId) {
		this.spanId = spanId;
	}
	public String getCommit() {
		return commit;
	}
	public void setCommit(String commit) {
		this.commit = commit;
	}
	public String getParentResourceId() {
		return parentResourceId;
	}
	public void setParentResourceId(String parentResourceId) {
		this.parentResourceId = parentResourceId;
	}
}
