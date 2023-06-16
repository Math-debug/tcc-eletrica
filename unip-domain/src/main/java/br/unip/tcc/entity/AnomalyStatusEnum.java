package br.unip.tcc.entity;

public enum AnomalyStatusEnum {
	OPEN(1),
	TRATED(2),
	CLOSED(3),
	NORMALIZED(4);
	
	public int anomalyType;

	AnomalyStatusEnum(int valor) {
		anomalyType = valor;
	}
}
