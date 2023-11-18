package com.java.dao;

import java.util.ArrayList;

import com.java.entities.LogEntity;
import com.java.entities.LogsFromDb;
import com.java.exceptions.GenericException;
import com.java.requestdto.FilterDTO;

public interface LogsDAO {
	boolean createLog(LogEntity logEntity) throws GenericException;
	ArrayList<LogsFromDb> getLogs() throws GenericException;
	ArrayList<LogsFromDb> getLogsByFilter(FilterDTO filterDTO) throws GenericException;
}
