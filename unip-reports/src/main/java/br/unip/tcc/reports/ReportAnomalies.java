package br.unip.tcc.reports;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.unip.tcc.facade.Report;

public class ReportAnomalies extends Report{
	private Integer anomalyid;
	private String anomalia;
	private String observation;
	private String username;
	private String createdat;
	private String lasttreatment;
	private String normalizedat;
	private String closedat;
	public Integer getAnomalyid() {
		return anomalyid;
	}
	public void setAnomalyid(Integer anomalyid) {
		this.anomalyid = anomalyid;
	}
	public String getAnomalia() {
		return anomalia;
	}
	public void setAnomalia(String anomalia) {
		this.anomalia = anomalia;
	}
	public String getObservation() {
		return observation;
	}
	public void setObservation(String observation) {
		this.observation = observation;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCreatedat() {
		return createdat;
	}
	public void setCreatedat(String createdat) {
		this.createdat = createdat;
	}
	public String getLasttreatment() {
		return lasttreatment;
	}
	public void setLasttreatment(String lasttreatment) {
		this.lasttreatment = lasttreatment;
	}
	public String getNormalizedat() {
		return normalizedat;
	}
	public void setNormalizedat(String normalizedat) {
		this.normalizedat = normalizedat;
	}
	public String getClosedat() {
		return closedat;
	}
	public void setClosedat(String closedat) {
		this.closedat = closedat;
	}
	
	@Override
	public ReportAnomalies mapRow(ResultSet rs, int rowNum) throws SQLException {
		ReportAnomalies report = new ReportAnomalies();
		report.setAnomalia(rs.getString("anomalia"));
		report.setAnomalyid(rs.getInt("anomalyid"));
		report.setClosedat(rs.getString("closedat"));
		report.setCreatedat(rs.getString("createdat"));
		report.setLasttreatment(rs.getString("lasttreatment"));
		report.setNormalizedat(rs.getString("normalizedat"));
		report.setObservation(rs.getString("observation"));
		report.setUsername(rs.getString("username"));
		return report;
	}
}
