package br.unip.tcc.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import br.unip.tcc.model.dto.view.ViewConsumoMensal;
import br.unip.tcc.model.dto.view.ViewEquipmentOffline;
import br.unip.tcc.outbound.repository.RepositoryViewConsumoMensal;
import br.unip.tcc.outbound.repository.RepositoryViewEquipmentOffline;

@Service
public class FindDashboard {
	
	private final RepositoryViewEquipmentOffline repositoryViewEquipmentOffline;

	private final RepositoryViewConsumoMensal repositoryViewConsumoMensal;

	public FindDashboard(RepositoryViewEquipmentOffline repositoryViewEquipmentOffline, RepositoryViewConsumoMensal repositoryViewConsumoMensal) {
		this.repositoryViewEquipmentOffline = repositoryViewEquipmentOffline;
		this.repositoryViewConsumoMensal = repositoryViewConsumoMensal;
	}

	public List<ViewEquipmentOffline> findByEquipmentsOffline() {
		return repositoryViewEquipmentOffline.findAll();
	}

	public List<ViewConsumoMensal> findByConsumoMensal() {
		return repositoryViewConsumoMensal.findAll();
	}
	
}
