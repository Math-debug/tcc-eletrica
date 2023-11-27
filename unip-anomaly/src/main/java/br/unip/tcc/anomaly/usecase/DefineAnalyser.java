package br.unip.tcc.anomaly.usecase;

import br.unip.tcc.anomaly.usecase.analyse.facade.Analyser;
import br.unip.tcc.anomaly.usecase.analyse.factory.AnalyseFactory;
import br.unip.tcc.entity.dto.KeepAliveDTO;
import org.springframework.stereotype.Service;

@Service
public class DefineAnalyser {
	
	private final AnalyseFactory analyseFactory;

	public DefineAnalyser(AnalyseFactory analyseFactory) {
		this.analyseFactory = analyseFactory;
	}

	public void execute(KeepAliveDTO dto) {
		Analyser analyser = analyseFactory.loadFactory(dto.getType());
		analyser.execAnalyse(dto);
	}
	
}
