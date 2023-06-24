package br.unip.tcc.entity;

public enum NetWorkTypeEnum {
	MONOPHASIC(0), BIPHASIC(1), TRIPHASIC(2);

	public int networkType;

	NetWorkTypeEnum(int valor) {
		networkType = valor;
	}
}
