package com.java.service;

import java.util.ArrayList;

import com.java.entities.LogsFromDb;
import com.java.exceptions.GenericException;
import com.java.requestdto.CreateLogDTO;
import com.java.requestdto.FilterDTO;

public interface LogsService {
	public boolean addLogs(CreateLogDTO createLogDTO) throws GenericException;
	public ArrayList<LogsFromDb> getCorrespondingLogs(FilterDTO filterDTO) throws GenericException;
}
