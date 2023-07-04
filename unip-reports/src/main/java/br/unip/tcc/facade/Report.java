package br.unip.tcc.facade;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public abstract class Report implements RowMapper<Report>{

	@Override
	public Report mapRow(ResultSet rs, int rowNum) throws SQLException {
		return null;
	}

}
