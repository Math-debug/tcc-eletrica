package br.unip.tcc.entity;

public enum AnomalyTypeEnum {
	CURRENTOVERLOAD(1l),
	VOLTAGEOVERLOAD(2l),
	PHASEMISSING(3l);
	
	public Long valor;
	
	AnomalyTypeEnum(Long valor){
		this.valor = valor;
	}

	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}
	
	
}
