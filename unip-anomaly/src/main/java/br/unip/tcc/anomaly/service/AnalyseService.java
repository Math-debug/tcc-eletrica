package br.unip.tcc.anomaly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unip.tcc.anomaly.analyse.facade.Analyser;
import br.unip.tcc.anomaly.analyse.factory.AnalyseFactory;
import br.unip.tcc.entity.dto.KeepAliveDTO;

@Service
public class AnalyseService {
	
	@Autowired
	AnalyseFactory analyseFactory;
	
	public void loadAnalyzerByNetWork(KeepAliveDTO dto) {
		Analyser analyser = analyseFactory.loadFactory(dto.getType());
		analyser.execAnalyse(dto);
	}
	
}
