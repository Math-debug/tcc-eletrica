insert into anom_anomalytype (anomalytypeid,createdat,description) values 
((SELECT nextval('anom_anomalytype_anomalytypeid_seq')),current_timestamp,'Sobrecarga de corrente');

insert into anom_anomalytype (anomalytypeid,createdat,description) values 
((SELECT nextval('anom_anomalytype_anomalytypeid_seq')),current_timestamp,'Sobrecarga de tensão');

insert into anom_anomalytype (anomalytypeid,createdat,description) values 
((SELECT nextval('anom_anomalytype_anomalytypeid_seq')),current_timestamp,'Falta de fase');