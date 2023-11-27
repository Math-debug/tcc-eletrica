package br.unip.tcc.anomaly.usecase.analyse.factory;

import br.unip.tcc.anomaly.usecase.analyse.TriPhasicAnalyzer;
import org.springframework.stereotype.Component;

import br.unip.tcc.anomaly.usecase.analyse.BiPhasicAnalyzer;
import br.unip.tcc.anomaly.usecase.analyse.MonoPhasicAnalyzer;
import br.unip.tcc.anomaly.usecase.analyse.facade.Analyser;

@Component
public class AnalyseFactory {
	
	private final MonoPhasicAnalyzer monoPhasicAnalyzer;
	private final BiPhasicAnalyzer biPhasicAnalyzer;
	private final TriPhasicAnalyzer triPhasicAnalyzer;

	public AnalyseFactory(MonoPhasicAnalyzer monoPhasicAnalyzer, BiPhasicAnalyzer biPhasicAnalyzer, TriPhasicAnalyzer triPhasicAnalyzer) {
		this.monoPhasicAnalyzer = monoPhasicAnalyzer;
		this.biPhasicAnalyzer = biPhasicAnalyzer;
		this.triPhasicAnalyzer = triPhasicAnalyzer;
	}

	public Analyser loadFactory (String type) {
		if(type.equals("M")) {
			return monoPhasicAnalyzer;
		} else if( type.equals("B") ) {
			return biPhasicAnalyzer;
		} else {
			return triPhasicAnalyzer;
		}
	}
}
