package com.java.ServiceImpl;

import java.util.ArrayList;

import com.java.daoimpl.LogsDAOImpl;
import com.java.entities.LogEntity;
import com.java.entities.LogsFromDb;
import com.java.exceptions.GenericException;
import com.java.requestdto.CreateLogDTO;
import com.java.requestdto.FilterDTO;
import com.java.service.LogsService;
import com.java.utilities.ServiceUtility;

public class LogsServiceImpl implements LogsService {
	LogsDAOImpl logsDAOImpl = new LogsDAOImpl();

	@Override
	public boolean addLogs(CreateLogDTO createLogDTO) throws GenericException {
		String logId = ServiceUtility.generateId("LOG");
		LogEntity logEntity = new LogEntity(logId, createLogDTO.getLevel(),
				createLogDTO.getMessage(), createLogDTO.getResourceId(),
				createLogDTO.getTimestamp(), createLogDTO.getTraceId(),
				createLogDTO.getSpanId(), createLogDTO.getCommit(),
				createLogDTO.getMetadata().getParentResourceId());
		boolean status = false;
		try {
			status = logsDAOImpl.createLog(logEntity);
		} catch (GenericException e) {
			throw e;
		}
		return status;
	}

	@Override
	public ArrayList<LogsFromDb> getCorrespondingLogs(FilterDTO filterDTO) throws GenericException {
		ArrayList<LogsFromDb> listOfLogs;
		try {
			listOfLogs = logsDAOImpl.getLogsByFilter(filterDTO);			
		} catch (GenericException e) {
			throw e;
		}
		return listOfLogs;
		
	}

}
