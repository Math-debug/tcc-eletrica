package br.unip.tcc.anomaly.analyse;

import org.springframework.stereotype.Component;

import br.unip.tcc.anomaly.analyse.facade.Analyser;
import br.unip.tcc.entity.dto.KeepAliveDTO;

@Component
public class BiPhasicAnalyzer extends MonoPhasicAnalyzer implements Analyser{

}
