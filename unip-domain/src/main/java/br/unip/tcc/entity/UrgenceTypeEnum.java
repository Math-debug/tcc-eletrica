package br.unip.tcc.entity;

public enum UrgenceTypeEnum {
	LOW(0), MEDIUM(1), HIGH(2);

	public int urgenceType;

	UrgenceTypeEnum(int valor) {
		urgenceType = valor;
	}
}
