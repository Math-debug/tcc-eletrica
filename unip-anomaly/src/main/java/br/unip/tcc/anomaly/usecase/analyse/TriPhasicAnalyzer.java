package br.unip.tcc.anomaly.usecase.analyse;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.unip.tcc.anomaly.usecase.analyse.facade.Analyser;
import br.unip.tcc.anomaly.usecase.AnomalyConfigService;
import br.unip.tcc.anomaly.usecase.AnomalyService;
import br.unip.tcc.converver.AnomalyConverter;
import br.unip.tcc.entity.Anomaly;
import br.unip.tcc.entity.AnomalyConfig;
import br.unip.tcc.entity.AnomalyStatusEnum;
import br.unip.tcc.entity.AnomalyType;
import br.unip.tcc.entity.AnomalyTypeEnum;
import br.unip.tcc.entity.KeepAlive;
import br.unip.tcc.entity.UrgenceTypeEnum;
import br.unip.tcc.entity.dto.KeepAliveDTO;
import br.unip.tcc.repository.AnomalyTypeRepository;
import br.unip.tcc.repository.EquipmentRepository;
import br.unip.tcc.repository.KeepAliveRepository;

@Component
public class TriPhasicAnalyzer implements Analyser{

	@Autowired
	private AnomalyConfigService anomalyConfigService;
	
	@Autowired
	private AnomalyService anomalyService;
	
	@Autowired
	private EquipmentRepository equipmentRepository;
	
	@Autowired
	private KeepAliveRepository keepAliveRepository;
	
	private static HashMap<Long, Integer> toNormalize = new HashMap<>();
	
	@Autowired
	private AnomalyTypeRepository anomalyTypeRepository;
	
	@Value(value = "${anomaly.qtdNomalizedToCloseAnomaly}")
	private String qtdToClose;
	
	@Override
	public void execAnalyse(KeepAliveDTO dto) {
		
		boolean isVoltageOverload = false;
		boolean isCurrentOverload = false;
		boolean isUnderVoltage = false;
		boolean isUnderCurrent = false;
		boolean isPhaseMissing = false;
		
		AnomalyConfig configs = anomalyConfigService.findByEquipementId(dto.getEquipment().getId());
		
		if(dto.getVoltage().getAb() > configs.getVmax() || dto.getVoltage().getBc() > configs.getVmax() || dto.getVoltage().getCa() > configs.getVmax()) {
			isVoltageOverload = true;
		}
		if(configs.getVmin() != null && (dto.getVoltage().getAb() < configs.getVmin() || dto.getVoltage().getBc() < configs.getVmin() || dto.getVoltage().getCa() < configs.getVmin())) {
			isUnderVoltage = true;
		}
		if(dto.getCurrent().getA() > configs.getImax() || dto.getCurrent().getB() > configs.getImax() || dto.getCurrent().getC() > configs.getImax()) {
			isCurrentOverload = true;
		}
		if(configs.getImin() != null && (dto.getCurrent().getA() < configs.getImin() || dto.getCurrent().getB() < configs.getImin() || dto.getCurrent().getC() < configs.getImin())) {
			isUnderCurrent = true;
		}
		if( Float.compare(dto.getVoltage().getAb(),0f) == 0 || Float.compare(dto.getVoltage().getBc(),0f) == 0 || Float.compare(dto.getVoltage().getCa(),0f) == 0) {
			isPhaseMissing = true;
		}
		
		//Finaliza se nenhuma anomalia foi detectada, ou adiciona uma leitura normalizada caso detectado anomalia anteriormente
		if(!(isVoltageOverload || isCurrentOverload || isUnderVoltage || isUnderCurrent || isPhaseMissing)) {
			Anomaly anomaly = anomalyService.findByValidStatusAndEquipmentEquipmentid(dto.getEquipment().getId());
			
			if(anomaly == null) {
				return;
			}
			
			if(anomaly.getStatusid().equals(AnomalyStatusEnum.NORMALIZED) || anomaly.getStatusid().equals(AnomalyStatusEnum.TRATED)) {
				Integer qtd = toNormalize.get(dto.getEquipment().getId()) != null ? toNormalize.get(dto.getEquipment().getId()) : 0;
				qtd += 1;
				toNormalize.put(dto.getEquipment().getId(), qtd);
				if(qtd >= Integer.parseInt(qtdToClose)) {
					anomalyService.closed(anomaly);
					toNormalize.put(dto.getEquipment().getId(), 0);
				}
			}else {
				Integer qtd = toNormalize.get(dto.getEquipment().getId());
				qtd += 1;
				toNormalize.put(dto.getEquipment().getId(), qtd);
				anomalyService.normalized(anomaly);
			}
			return;
		}
		
		toNormalize.put(dto.getEquipment().getId(), 0);
		
		Anomaly anomaly = anomalyService.findByValidStatusAndEquipmentEquipmentid(dto.getEquipment().getId());
		KeepAlive keepAlive = keepAliveRepository.findById(dto.getId()).get();
		
		StringBuilder description = new StringBuilder();
		AnomalyType anomalyType = new AnomalyType();
				
		if(isVoltageOverload || isUnderCurrent) {
			description = voltageAnomalyDetail(description,configs,dto);
		}
		if(isVoltageOverload) {
			anomalyType = anomalyTypeRepository.findById(AnomalyTypeEnum.VOLTAGEOVERLOAD.getValor()).get();
		}else if(isUnderVoltage) {
			anomalyType = anomalyTypeRepository.findById(AnomalyTypeEnum.VOLTAGEUNDER.getValor()).get();
		}
		
		if(isCurrentOverload || isUnderCurrent) {
			description = currentAnomalyDetail(description,configs,dto);
		}
		if(isCurrentOverload ) {
			anomalyType = anomalyTypeRepository.findById(AnomalyTypeEnum.CURRENTOVERLOAD.getValor()).get();
		} else if(isUnderCurrent) {
			anomalyType = anomalyTypeRepository.findById(AnomalyTypeEnum.CURRENTUNDER.getValor()).get();
		}
		
		if(isPhaseMissing) {
			anomalyType = anomalyTypeRepository.findById(AnomalyTypeEnum.PHASEMISSING.getValor()).get();
			description = phaseMissingAnomalyDetail(description,configs,dto);
		}
		
		if (anomaly == null) {
			anomaly = new Anomaly();
			anomaly.setDescription(description.toString());
			anomaly.setAnomalytype(anomalyType);
			anomaly.setKeepAlive(keepAlive);
			anomaly.getKeepAlive().setEquipment(equipmentRepository.findById(dto.getEquipment().getId()).get());
			anomaly.setUrgeceid(UrgenceTypeEnum.LOW);
			anomaly.setStatusid(AnomalyStatusEnum.OPEN);
			anomaly.setNormalizedat(null);
			anomalyService.save(AnomalyConverter.convertTo(anomaly));
		} else if(anomaly.getUrgeceid().equals(UrgenceTypeEnum.LOW)) {
			anomaly.setDescription(description.toString());
			anomaly.setAnomalytype(anomalyType);
			anomaly.setKeepAlive(keepAlive);
			anomaly.setUrgeceid(UrgenceTypeEnum.MEDIUM);
			anomaly.setStatusid(AnomalyStatusEnum.OPEN);
			anomaly.setNormalizedat(null);
			anomalyService.save(AnomalyConverter.convertTo(anomaly));
		}else {
			anomaly.setDescription(description.toString());
			anomaly.setAnomalytype(anomalyType);
			anomaly.setKeepAlive(keepAlive);
			anomaly.setUrgeceid(UrgenceTypeEnum.HIGH);
			anomaly.setStatusid(AnomalyStatusEnum.OPEN);
			anomaly.setNormalizedat(null);
			anomalyService.save(AnomalyConverter.convertTo(anomaly));
		}
	}
	
	private StringBuilder voltageAnomalyDetail (StringBuilder description, AnomalyConfig configs, KeepAliveDTO dto) {
		if(dto.getVoltage().getAb() > configs.getVmax()) {
			description.append("Tensão medida acima da configurada entre A-B. Medida: " + dto.getVoltage().getAb() + "/ Configurada: "+ configs.getVmax() + "\n");
		} else if(dto.getVoltage().getAb() != null && dto.getVoltage().getAb() < configs.getVmin()) {
			description.append("Tensão medida abaixo da configurada entre A-B. Medida: " + dto.getVoltage().getAb() + "/ Configurada: "+ configs.getVmax() + "\n");
		}
		if(dto.getVoltage().getBc() > configs.getVmax()) {
			description.append("Tensão medida acima da configurada entre B-C. Medida: " + dto.getVoltage().getBc() + "/ Configurada: "+ configs.getVmax() + "\n");
		}else if(dto.getVoltage().getBc() != null && dto.getVoltage().getBc() < configs.getVmin()) {
			description.append("Tensão medida abaixo da configurada entre B-C. Medida: " + dto.getVoltage().getBc() + "/ Configurada: "+ configs.getVmax() + "\n");
		} 
		if(dto.getVoltage().getCa() > configs.getVmax()) {
			description.append("Tensão medida acima da configurada entre C-A. Medida: " + dto.getVoltage().getCa() + "/ Configurada: "+ configs.getVmax() + "\n");
		}else if(dto.getVoltage().getCa() != null && dto.getVoltage().getCa() < configs.getVmin()) {
			description.append("Tensão medida abaixo da configurada entre C-A. Medida: " + dto.getVoltage().getCa() + "/ Configurada: "+ configs.getVmax() + "\n");
		}
		return description;
	}

	private StringBuilder currentAnomalyDetail (StringBuilder description, AnomalyConfig configs, KeepAliveDTO dto) {
		if(dto.getCurrent().getA() > configs.getImax()) {
			description.append("Corrente medida acima da configurada em A. Medida: " + dto.getCurrent().getA() + "/ Configurada: "+ configs.getImax() + "\n");
		} else if(dto.getCurrent().getA() != null && dto.getCurrent().getA() < configs.getImin()) {
			description.append("Corrente medida abaixo da configurada em A. Medida: " + dto.getCurrent().getA() + "/ Configurada: "+ configs.getImax() + "\n");
		}
		if(dto.getCurrent().getB() > configs.getImax()) {
			description.append("Corrente medida acima da configurada em B. Medida: " + dto.getCurrent().getB() + "/ Configurada: "+ configs.getImax() + "\n");
		}else if(dto.getCurrent().getB() != null && dto.getCurrent().getB() < configs.getImin()) {
			description.append("Corrente medida abaixo da configurada em B. Medida: " + dto.getCurrent().getB() + "/ Configurada: "+ configs.getImax() + "\n");
		} 
		if(dto.getCurrent().getC() > configs.getImax()) {
			description.append("Corrente medida acima da configurada em C. Medida: " + dto.getCurrent().getC() + "/ Configurada: "+ configs.getImax() + "\n");
		}else if(dto.getCurrent().getC() != null && dto.getCurrent().getC() < configs.getImin()) {
			description.append("Corrente medida abaixo da configurada em C. Medida: " + dto.getCurrent().getC() + "/ Configurada: "+ configs.getImax() + "\n");
		}
		return description;
	}
	private StringBuilder phaseMissingAnomalyDetail (StringBuilder description, AnomalyConfig configs, KeepAliveDTO dto) {
		if(Float.compare(dto.getVoltage().getAb(),0f) == 0 && Float.compare(dto.getVoltage().getCa(),0f) == 0) {
			description.append("Falta de fase em A \n");
		}
		if(Float.compare(dto.getVoltage().getAb(),0f) == 0 && Float.compare(dto.getVoltage().getBc(),0f) == 0) {
			description.append("Falta de fase em B \n");
		}
		if(Float.compare(dto.getVoltage().getCa(),0f) == 0 && Float.compare(dto.getVoltage().getBc(),0f) == 0) {
			description.append("Falta de fase em C \n");
		}
		return description;
	}
}
