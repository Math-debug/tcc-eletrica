package br.unip.tcc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.unip.tcc.entity.dto.ReportListDTO;
import br.unip.tcc.facade.Report;
import br.unip.tcc.service.ReportService;

@RestController
@RequestMapping(value = "/reports")
public class ReportController {
	
	@Autowired
	ReportService reportService;
	
	@GetMapping
	public ResponseEntity<List<ReportListDTO>> findList(){
		List<ReportListDTO> result = new ArrayList<>();
		result = reportService.findList();
		return ResponseEntity.ok(result);
	}
	
	@GetMapping(value = "search")
	public ResponseEntity<List<Report>> findByReport(@RequestParam(value = "reportid") Long id, @RequestParam(value = "params", required = false) String params){
		List<Report> list = reportService.getReport(id,params);		
		return ResponseEntity.ok(list);
	}
}
