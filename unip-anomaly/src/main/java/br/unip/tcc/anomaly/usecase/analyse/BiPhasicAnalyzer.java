package br.unip.tcc.anomaly.usecase.analyse;

import org.springframework.stereotype.Component;

import br.unip.tcc.anomaly.usecase.analyse.facade.Analyser;

@Component
public class BiPhasicAnalyzer extends MonoPhasicAnalyzer implements Analyser{

}
