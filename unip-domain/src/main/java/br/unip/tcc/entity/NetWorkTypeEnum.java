package br.unip.tcc.entity;

public enum NetWorkTypeEnum {
	MONOPHASIC(1), BIPHASIC(2), TRIPHASIC(3);

	public int networkType;

	NetWorkTypeEnum(int valor) {
		networkType = valor;
	}
}
