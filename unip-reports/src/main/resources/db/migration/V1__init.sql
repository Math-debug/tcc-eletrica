-- Equipamentos offline - Dashboard

create or replace view v_equipment_offline as
select a.*,so2.active from (
select
	max(so.offlineequipid) as id,
	so.equipmentid ,
	te.name
from
	sync_offlineequip so
	inner join tcc_equipment te on so.equipmentid = te.equipmentid 
group by
	te.name,
	so.equipmentid) 
a inner join sync_offlineequip so2 on so2.offlineequipid = a.id;
	
-- Consumo mensal - Dashboard

create or replace view v_consumo_mensal as
select
	sum((current * voltage)/1000) as consumo,
	extract (month
from
	createdat) -1 as mes
from
	tcc_keepalive tk
where
	extract (year
from
	createdat) = extract(year
from
	current_date)
group by extract (month
from
	createdat);
