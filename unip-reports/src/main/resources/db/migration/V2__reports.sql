insert into report_list (reportid,parametro,query,title) values ((select nextval('report_list_reportid_seq')), 'mes',
'select
	equipmentid,
	extract (days from sum(hours)) * 12 +  extract (hours from sum(hours)) hours
	from(
select
	equipmentid ,
	sum (updateat - createdat) hours
from
	sync_offlineequip so
where
	active = false and
	extract (month
from
	createdat) = ?
group by
	equipmentid
union all
select
	equipmentid ,
	sum (current_date - createdat) hours
from
	sync_offlineequip so
where
	active = true and
	extract (month
from
	createdat) = ?
group by
	equipmentid) b
group by equipmentid;', 'Relatório de Equipamentos offline');

-- Açóes nas anomalias

insert into report_list (reportid,parametro,query,title) values ((select nextval('report_list_reportid_seq')), 'data',
'select
	aa2.anomalyid,
	aa2.description as anomalia,
	STRING_AGG(aa.observation,
	";") as observation,
	STRING_AGG(ts.username,
	";") as username,
	aa2.createdat,
	aa2.lasttreatment,
	aa2.normalizedat,
	aa2.closedat
from
	anom_action aa
inner join anom_anomaly aa2 on
	aa.anomalyid = aa2.anomalyid
inner join tcc_sysuser ts on
	aa.userid = ts.sysuserid
	where aa2.createdat between ? and ?
group by
	aa2.anomalyid,
	aa2.description,
	aa2.createdat,
	aa2.lasttreatment,
	aa2.normalizedat,
	aa2.closedat
	;','Relatório de anomalias');



-- Indice de anomalias por equipamento

insert into report_list (reportid,parametro,query,title) values ((select nextval('report_list_reportid_seq')), null,
'select
	count(*) as qtd,
	anomalytypeid,
	tk.equipmentid
from
	anom_anomaly aa
inner join tcc_keepalive tk on
	tk.keepaliveid = aa.keepaliveid
group by
	anomalytypeid,
	tk.equipmentid
	;
', 'Relatório de indicadores de incidente por equipamento');
