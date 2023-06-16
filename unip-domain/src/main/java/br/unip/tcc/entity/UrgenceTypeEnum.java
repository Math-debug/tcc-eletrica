package br.unip.tcc.entity;

public enum UrgenceTypeEnum {
	LOW(1), MEDIUM(2), HIGH(3);

	public int urgenceType;

	UrgenceTypeEnum(int valor) {
		urgenceType = valor;
	}
}
