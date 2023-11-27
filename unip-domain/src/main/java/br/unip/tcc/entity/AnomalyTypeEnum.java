package br.unip.tcc.entity;

public enum AnomalyTypeEnum {
	CURRENTOVERLOAD(1l),
	VOLTAGEOVERLOAD(2l),
	PHASEMISSING(3l),
	CURRENTUNDER(4l),
	VOLTAGEUNDER(5l),
	SHUTDOWN(6l);
	
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
