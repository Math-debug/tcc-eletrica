package br.unip.tcc.entity;

public enum AnomalyStatusEnum {
	OPEN(0),
	TRATED(1),
	CLOSED(2),
	NORMALIZED(3);
	
	public int anomalyType;

	AnomalyStatusEnum(int valor) {
		anomalyType = valor;
	}
}
