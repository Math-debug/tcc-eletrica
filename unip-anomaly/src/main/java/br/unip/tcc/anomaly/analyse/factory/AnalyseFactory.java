package br.unip.tcc.anomaly.analyse.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.unip.tcc.anomaly.analyse.BiPhasicAnalyzer;
import br.unip.tcc.anomaly.analyse.MonoPhasicAnalyzer;
import br.unip.tcc.anomaly.analyse.TriPhasicAnalyzer;
import br.unip.tcc.anomaly.analyse.facade.Analyser;

@Component
public class AnalyseFactory {
	
	@Autowired
	MonoPhasicAnalyzer monoPhasicAnalyzer;
	
	@Autowired
	BiPhasicAnalyzer biPhasicAnalyzer;
	
	@Autowired
	TriPhasicAnalyzer triPhasicAnalyzer;
	
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
