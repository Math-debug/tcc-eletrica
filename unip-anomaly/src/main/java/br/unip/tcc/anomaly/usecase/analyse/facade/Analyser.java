package br.unip.tcc.anomaly.usecase.analyse.facade;

import br.unip.tcc.entity.dto.KeepAliveDTO;

public interface Analyser {
	public void execAnalyse(KeepAliveDTO dto);
}
