package br.unip.tcc.model.facade;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public abstract class Report implements RowMapper<Report>{

	@Override
	public Report mapRow(ResultSet rs, int rowNum) throws SQLException {
		return null;
	}
	
	public Object[] getParams(String params){
		return new Object[]{};
	}

}
