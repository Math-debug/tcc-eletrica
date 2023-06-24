package br.unip.tcc.anomaly.analyse;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.unip.tcc.anomaly.analyse.facade.Analyser;
import br.unip.tcc.anomaly.service.AnomalyConfigService;
import br.unip.tcc.anomaly.service.AnomalyService;
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
public class MonoPhasicAnalyzer implements Analyser{
	
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
		
		AnomalyConfig configs = anomalyConfigService.findByEquipementId(dto.getEquipment().getId());
		
		if(dto.getVoltage().getAb() > configs.getVmax()) {
			isVoltageOverload = true;
		}
		if(configs.getVmin() != null && dto.getVoltage().getAb() < configs.getVmin()) {
			isUnderVoltage = true;
		}
		if(dto.getCurrent().getA() > configs.getImax()) {
			isCurrentOverload = true;
		}
		if(configs.getImin() != null && dto.getCurrent().getA() < configs.getImin()) {
			isUnderCurrent = true;
		}
		//Finaliza se nenhuma anomalia foi detectada, ou adiciona uma leitura normalizada caso detectado anomalia anteriormente
		if(!(isVoltageOverload || isCurrentOverload || isUnderVoltage || isUnderCurrent)) {
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
				
		if(isVoltageOverload) {
			anomalyType = anomalyTypeRepository.findById(AnomalyTypeEnum.VOLTAGEOVERLOAD.getValor()).get();
			description.append("Tensão medida acima da configurada. Medida: " + dto.getVoltage().getAb() + "/ Configurada: "+ configs.getVmax() + "\n");
		}else if(isUnderVoltage) {
			anomalyType = anomalyTypeRepository.findById(AnomalyTypeEnum.VOLTAGEOVERLOAD.getValor()).get();
			description.append("Tensão medida abaixo da configurada. Medida: " + dto.getVoltage().getAb() + "/ Configurada: "+ configs.getVmax() + "\n");
		}
		
		if(isCurrentOverload ) {
			anomalyType = anomalyTypeRepository.findById(AnomalyTypeEnum.CURRENTOVERLOAD.getValor()).get();
			description.append("Corrente medida acima da configurada. Medida: " + dto.getCurrent().getA() + "/ Configurada: "+ configs.getImax() + "\n");
		} else if(isUnderCurrent) {
			anomalyType = anomalyTypeRepository.findById(AnomalyTypeEnum.CURRENTOVERLOAD.getValor()).get();
			description.append("Corrente medida abaixo da configurada. Medida: " + dto.getCurrent().getA() + "/ Configurada: "+ configs.getImin() + "\n");
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

}
