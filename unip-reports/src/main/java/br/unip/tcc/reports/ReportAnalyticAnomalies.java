package br.unip.tcc.reports;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.unip.tcc.facade.Report;

public class ReportAnalyticAnomalies extends Report{
	
	private Integer qtd;
	private Integer anomalytypeid;
	private Integer equipmentid;
	
	public Integer getQtd() {
		return qtd;
	}
	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}
	public Integer getAnomalytypeid() {
		return anomalytypeid;
	}
	public void setAnomalytypeid(Integer anomalytypeid) {
		this.anomalytypeid = anomalytypeid;
	}
	public Integer getEquipmentid() {
		return equipmentid;
	}
	public void setEquipmentid(Integer equipmentid) {
		this.equipmentid = equipmentid;
	}
	
	@Override
	public ReportAnalyticAnomalies mapRow(ResultSet rs, int rowNum) throws SQLException {
		ReportAnalyticAnomalies report = new ReportAnalyticAnomalies();
		report.setQtd(rs.getInt("qtd"));
		report.setEquipmentid(rs.getInt("equipmentid"));
		report.setAnomalytypeid(rs.getInt("anomalytypeid"));
		return report;
	}
}
