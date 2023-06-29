package br.unip.tcc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unip.tcc.view.ViewConsumoMensal;
import br.unip.tcc.view.ViewEquipmentOffline;
import br.unip.tcc.view.repository.RepositoryViewConsumoMensal;
import br.unip.tcc.view.repository.RepositoryViewEquipmentOffline;

@Service
public class DashboardService {
	
	@Autowired
	RepositoryViewEquipmentOffline repositoryViewEquipmentOffline;

	@Autowired
	RepositoryViewConsumoMensal repositoryViewConsumoMensal;
	
	public List<ViewEquipmentOffline> findByEquipmentsOffline() {
		return repositoryViewEquipmentOffline.findAll();
	}

	public List<ViewConsumoMensal> findByConsumoMensal() {
		return repositoryViewConsumoMensal.findAll();
	}
	
}
