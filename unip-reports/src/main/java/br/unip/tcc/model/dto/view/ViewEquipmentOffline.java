package br.unip.tcc.model.dto.view;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Entity 
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "v_equipment_offline") 
public class ViewEquipmentOffline {
	@Id
	Long id;
	Long equipmentid;
	String name;
	Boolean active;
}
