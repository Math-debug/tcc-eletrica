package br.unip.tcc.model.factory;

import br.unip.tcc.model.facade.Report;
import br.unip.tcc.model.dto.reports.ReportAnalyticAnomalies;
import br.unip.tcc.model.dto.reports.ReportAnomalies;
import br.unip.tcc.model.dto.reports.ReportOfflineEquipment;

public class ReportFactory {

	public static Report factory(Long reportid) {
		if(reportid.equals(new Long(1))) {
			return new ReportOfflineEquipment();
		}else if(reportid.equals(new Long(2))) {
			return new ReportAnomalies();
		}else if(reportid.equals(new Long(3))) {
			return new ReportAnalyticAnomalies();
		}
		return null;
	}
}
