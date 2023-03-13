package com.fabrick.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fabrick.test.dao.ILogsDao;
import com.fabrick.test.model.LogsRegistre;

/**
 * 
 * @author JuanBrito
 * save logs about operation
 *
 */
@Service
public class LogsServiceImpl implements ILogsService {

	@Autowired
	private ILogsDao logsDao;
	
	@Override
	@Transactional
	public LogsRegistre save(LogsRegistre logs) {
		
		LogsRegistre data = logsDao.save(logs);
		return data;
	}

}
