package br.unip.tcc.proto.converter;

import java.util.ArrayList;
import java.util.List;

import br.unip.tcc.entity.ReportList;
import br.unip.tcc.entity.dto.ReportListDTO;

public class ReportListConverter {
	public static List<ReportListDTO> convertTo (List<ReportList> dto){
		List<ReportListDTO> result = new ArrayList<>();
		for(ReportList report : dto) {
			result.add(ReportListConverter.convertTo(report));
		}
		return result;
	}
	
	public static ReportListDTO convertTo (ReportList entity) {
		ReportListDTO dto = new ReportListDTO();
		dto.setParametro(entity.getParametro());
		dto.setReportid(entity.getReportid());
		dto.setTitle(entity.getTitle());
		return dto;
	}
}
