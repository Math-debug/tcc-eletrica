package br.unip.tcc.factory;

import br.unip.tcc.facade.Report;
import br.unip.tcc.reports.ReportAnalyticAnomalies;
import br.unip.tcc.reports.ReportAnomalies;
import br.unip.tcc.reports.ReportOfflineEquipment;

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
