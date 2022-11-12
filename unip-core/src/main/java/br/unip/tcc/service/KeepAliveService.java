package br.unip.tcc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unip.tcc.converver.KeepAliveConverter;
import br.unip.tcc.entity.KeepAlive;
import br.unip.tcc.entity.dto.KeepAliveDTO;
import br.unip.tcc.repository.KeepAliveRepository;

@Service
public class KeepAliveService {
	@Autowired
	KeepAliveRepository keepAliveRepository;
	
	public List<KeepAlive> findAll(){
		return keepAliveRepository.findAll();
	}
	public KeepAlive findById(Long id) {
		return keepAliveRepository.findById(id).get();
	}
	public KeepAlive save (KeepAliveDTO dto) {
		KeepAlive entity =  KeepAliveConverter.convertTo(dto);
		double fp = 0.8;
		entity.setFp(fp);
		entity.setPotenciaaparente(entity.getVoltage() * entity.getCurrent());
		entity.setPotenciaativa(entity.getPotenciaaparente() * fp);
		entity.setPotenciareativa(Math.sqrt(Math.pow(entity.getPotenciaaparente(),2) - Math.pow(entity.getPotenciaativa(), 2)));
		return keepAliveRepository.save(entity);
	}
}
