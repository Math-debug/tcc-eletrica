package br.unip.tcc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unip.tcc.service.DashboardService;
import br.unip.tcc.view.ViewConsumoMensal;
import br.unip.tcc.view.ViewEquipmentOffline;

@RestController
@RequestMapping(value = "/reports/dashboard")
public class DashboardController {
	
	@Autowired
	DashboardService dashboardService;
	
	@GetMapping(value = "/offlineequipment")
	ResponseEntity<List<ViewEquipmentOffline>> findByViewEquipmentOffline(){
		List<ViewEquipmentOffline> result = new ArrayList<>();
		result = dashboardService.findByEquipmentsOffline();
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping(value = "/consumomensal")
	ResponseEntity<List<ViewConsumoMensal>> findByViewConsumoMensal(){
		List<ViewConsumoMensal> result = new ArrayList<>();
		result = dashboardService.findByConsumoMensal();
		return ResponseEntity.ok().body(result);
	}
}
