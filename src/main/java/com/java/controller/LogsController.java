package com.java.controller;

import java.util.ArrayList;

import com.java.ServiceImpl.LogsServiceImpl;
import com.java.entities.LogsFromDb;
import com.java.exceptions.GenericException;
import com.java.requestdto.CreateLogDTO;
import com.java.requestdto.FilterDTO;
import com.java.responseentity.Response;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/logs")
public class LogsController {
	LogsServiceImpl logsServiceImpl = new LogsServiceImpl();

	@POST
	@Path("/addLogs")
	@Produces(MediaType.APPLICATION_JSON)
	public Response<Boolean> addLogs(CreateLogDTO createLogDTO){
		boolean status = false;
		try {
			status = logsServiceImpl.addLogs(createLogDTO);
			return new Response<Boolean>("added log successfully", 200, status);
		} catch (GenericException e) {
			return new Response<Boolean>(e.getMessage(), 400, status);
		}
	}
	
	@POST
	@Path("/filter")
	@Produces(MediaType.APPLICATION_JSON)
	public Response<ArrayList<LogsFromDb>> getCorrespondingLogs(FilterDTO filterDTO){
		ArrayList<LogsFromDb> listOfLogs = null;
		try {
			listOfLogs = logsServiceImpl.getCorrespondingLogs(filterDTO);
			return new Response<ArrayList<LogsFromDb>>("retrieved successfully", 200, listOfLogs);
		} catch (GenericException e) {
			return new Response<ArrayList<LogsFromDb>>("retrieved successfully", 200, listOfLogs);
		}
	}
}
