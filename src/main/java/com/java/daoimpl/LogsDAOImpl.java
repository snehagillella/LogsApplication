package com.java.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.java.JdbcConn.JdbcApp;
import com.java.dao.LogsDAO;
import com.java.entities.LogEntity;
import com.java.entities.LogsFromDb;
import com.java.exceptions.GenericException;
import com.java.requestdto.FilterDTO;

public class LogsDAOImpl implements LogsDAO{
	JdbcApp jdbc = new JdbcApp();
	Connection connection = jdbc.getConnection();
	PreparedStatement ps = jdbc.getPs();

	@Override
	public boolean createLog(LogEntity logEntity) throws GenericException {
		boolean status = false;
		try {
			ps=connection.prepareStatement("insert into logs(log_id, log_level, message, resource_id,"
					+ " timestamp, trace_id, span_id, commit, parent_resource_id) values (?,?,?,?,?,?,?,?,?)");
			ps.setString(1, logEntity.getLogId());
			ps.setString(2, logEntity.getLevel());
			ps.setString(3, logEntity.getMessage());
			ps.setString(4, logEntity.getResouceId());
			Timestamp timestamp = convertStringToTimestamp(logEntity.getTimestamp());
			ps.setTimestamp(5, timestamp);
			ps.setString(6, logEntity.getTraceId());
			ps.setString(7, logEntity.getSpanId());
			ps.setString(8, logEntity.getCommit());
			ps.setString(9, logEntity.getParentResourceId());
			int res = ps.executeUpdate();
			if(res == 0) {
				throw new GenericException("failed to create customer");
			}
			status=true;
		} catch (SQLException e) {
			throw new GenericException(e.getMessage(), e);
		}
		return status;
	}
	
	@Override
	public ArrayList<LogsFromDb> getLogs() throws GenericException {
		ArrayList<LogsFromDb> listOfLogs = new ArrayList<>();
		try {
			ps=connection.prepareStatement("select log_id, log_level, message, resource_id, timestamp, trace_id, span_id, commit, parent_resource_id from logs");
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				listOfLogs.add(new LogsFromDb(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getTimestamp(5),
						res.getString(6), res.getString(7), res.getString(8), res.getString(9)));
			}
		} catch (SQLException e) {
			throw new GenericException(e.getMessage(), e);
		}
		
		return listOfLogs;
	}
	
	private static Timestamp convertStringToTimestamp(String timestampString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        try {
            java.util.Date parsedDate = dateFormat.parse(timestampString);
            return new Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // Handle the parse exception according to your requirements
        }
    }

	@Override
	public ArrayList<LogsFromDb> getLogsByFilter(FilterDTO filterDTO) throws GenericException {
		ArrayList<LogsFromDb> listOfLogs = new ArrayList<>();
		try {
			ps=connection.prepareStatement("SELECT log_id, log_level, message, resource_id,"
					+ " timestamp, trace_id, span_id, commit,"
					+ " parent_resource_id from logs WHERE REGEXP_LIKE(?, ?, 'i')");
			ps.setString(1, filterDTO.getFieldName());
			ps.setString(2, filterDTO.getFieldValue());
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				listOfLogs.add(new LogsFromDb(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getTimestamp(5),
						res.getString(6), res.getString(7), res.getString(8), res.getString(9)));
			}
		} catch (SQLException e) {
			throw new GenericException(e.getMessage(), e);
		}
		
		return listOfLogs;
	}
	

}
