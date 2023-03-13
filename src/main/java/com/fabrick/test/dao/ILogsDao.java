package com.fabrick.test.dao;

import org.springframework.data.repository.CrudRepository;

import com.fabrick.test.model.LogsRegistre;

public interface ILogsDao extends CrudRepository<LogsRegistre, Long>{

}
