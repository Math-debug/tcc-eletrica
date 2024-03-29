package br.unip.tcc.model.dto.reports;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.unip.tcc.model.facade.Report;

public class ReportOfflineEquipment extends Report{
	Long equipmentid;
	Integer hours;
	
	public Long getEquipmentid() {
		return equipmentid;
	}

	public void setEquipmentid(Long equipmentid) {
		this.equipmentid = equipmentid;
	}

	public Integer getHours() {
		return hours;
	}

	public void setHours(Integer hours) {
		this.hours = hours;
	}

	@Override
	public ReportOfflineEquipment mapRow(ResultSet rs, int rowNum) throws SQLException {
		ReportOfflineEquipment report = new ReportOfflineEquipment();
		report.setEquipmentid(rs.getLong("equipmentid"));
		report.setHours(rs.getInt("hours"));
		return report;
	}
	
	@Override
	public Object[] getParams(String params) {
		return new Object[]{new Long(params),new Long(params)};
	}
}
