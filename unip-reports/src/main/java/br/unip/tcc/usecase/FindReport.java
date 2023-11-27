package br.unip.tcc.usecase;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import br.unip.tcc.entity.ReportList;
import br.unip.tcc.entity.dto.ReportListDTO;
import br.unip.tcc.model.facade.Report;
import br.unip.tcc.model.factory.ReportFactory;
import br.unip.tcc.proto.converter.ReportListConverter;
import br.unip.tcc.repository.ReportListRepository;

@Service
public class FindReport {

	private final JdbcTemplate jdbcTemplate;
	private final ReportListRepository reportListRepository;

	public FindReport(JdbcTemplate jdbcTemplate, ReportListRepository reportListRepository) {
		this.jdbcTemplate = jdbcTemplate;
		this.reportListRepository = reportListRepository;
	}

	public List<ReportListDTO> findList() {
		return ReportListConverter.convertTo(reportListRepository.findAll());
	}

	public List<Report> getReport(Long id, String params) {
		ReportList report = reportListRepository.findById(id).get();
		Report mapper = ReportFactory.factory(report.getReportid());
		return jdbcTemplate.query(report.getQuery(), mapper.getParams(params), mapper);
	}
}
