package br.unip.tcc.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import br.unip.tcc.entity.ReportList;
import br.unip.tcc.entity.dto.ReportListDTO;
import br.unip.tcc.facade.Report;
import br.unip.tcc.factory.ReportFactory;
import br.unip.tcc.proto.converter.ReportListConverter;
import br.unip.tcc.repository.ReportListRepository;

@Service
public class ReportService {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	ReportListRepository reportListRepository;

	public List<ReportListDTO> findList() {
		List<ReportListDTO> list = ReportListConverter.convertTo(reportListRepository.findAll());
		return list;
	}

	public List<Report> getReport(Long id, String params) {
		ReportList report = reportListRepository.findById(id).get();
		Object[] param = new Object[]{};
		if(report.getParametro() != null) {
			if(report.getParametro().equals("mes")) {
				param = new Object[]{new Long(params),new Long(params)};
			}else if(report.getParametro().equals("data")) {
				String[] datas = params.split(";");
				try {
					param = new Object[]{new SimpleDateFormat("dd/MM/yyyy").parse(datas[0]), new SimpleDateFormat("dd/MM/yyyy").parse(datas[1])};
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		Report mapper = ReportFactory.factory(report.getReportid());
		List<Report> list = jdbcTemplate.query(report.getQuery(), param, mapper);
		return list;
	}
}
