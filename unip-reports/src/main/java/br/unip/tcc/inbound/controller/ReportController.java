package br.unip.tcc.inbound.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.unip.tcc.entity.dto.ReportListDTO;
import br.unip.tcc.model.facade.Report;
import br.unip.tcc.usecase.FindReport;

@RestController
@RequestMapping(value = "/reports")
public class ReportController {
	
    private final FindReport findReport;

	public ReportController(FindReport findReport) {
		this.findReport = findReport;
	}

	@GetMapping
	public ResponseEntity<List<ReportListDTO>> findList(){
		List<ReportListDTO> result;
		result = findReport.findList();
		return ResponseEntity.ok(result);
	}
	
	@GetMapping(value = "search")
	public ResponseEntity<List<Report>> findByReport(@RequestParam(value = "reportid") Long id, @RequestParam(value = "params", required = false) String params){
		List<Report> list = findReport.getReport(id,params);
		return ResponseEntity.ok(list);
	}
}
