package br.unip.tcc.inbound.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unip.tcc.usecase.FindDashboard;
import br.unip.tcc.model.dto.view.ViewConsumoMensal;
import br.unip.tcc.model.dto.view.ViewEquipmentOffline;

@RestController
@RequestMapping(value = "/reports/dashboard")
public class DashboardController {
	
    private final FindDashboard findDashboard;

	public DashboardController(FindDashboard findDashboard) {
		this.findDashboard = findDashboard;
	}

	@GetMapping(value = "/offlineequipment")
	ResponseEntity<List<ViewEquipmentOffline>> findByViewEquipmentOffline(){
		List<ViewEquipmentOffline> result;
		result = findDashboard.findByEquipmentsOffline();
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping(value = "/consumomensal")
	ResponseEntity<List<ViewConsumoMensal>> findByViewConsumoMensal(){
		List<ViewConsumoMensal> result;
		result = findDashboard.findByConsumoMensal();
		return ResponseEntity.ok().body(result);
	}
}
