### docker

version: '2'

services:
 db:
  image: postgres
  restart: always
  environment:
   - POSTGRES_PASSWORD=tcc
   - POSTGRES_USER=tcc
   - POSTGRES_DB=tcc
  ports:
   - "5432:5432"
 broker:
  image: webcenter/activemq:5.14.3
  environment:
   - ACTIVEMQ_ADMIN_LOGIN=Admin
   - ACTIVEMQ_ADMIN_PASSWORD=Admin
  ports:
   - "8161:8161"
   - "61616:61616"
 tccws:
  image: mxavier27/tcc-eletrica:0.0.1-SNAPSHOT
  volumes:
   - /home/matheus/Projetos/TCC/tcc-eletrica/unip-core/src/main/resources/application.properties:/app/application.properties
  ports:
   - "9023:9023"
  depends_on:
   - db
   - broker
 tcc-anomaly:
  image: mxavier27/tcc-eletrica-anomaly:0.0.1-SNAPSHOT
  volumes:
   - /home/matheus/Projetos/TCC/tcc-eletrica/unip-anomaly/src/main/resources/application.properties:/app/application.properties
  ports:
   - "9025:9025"
  depends_on:
   - db
   - broker   
 tcc-reports:
  image: mxavier27/tcc-eletrica-report:0.0.1-SNAPSHOT
  volumes:
   - /home/matheus/Projetos/TCC/tcc-eletrica/unip-reports/src/main/resources/application.properties:/app/application.properties
  ports:
   - "9026:9026"
  depends_on:
   - db
   - broker      

 tcc-sync:
  image: mxavier27/tcc-eletrica-sync:0.0.1-SNAPSHOT
  volumes:
   - /home/matheus/Projetos/TCC/tcc-eletrica/unip-sync/src/main/resources/application.properties:/app/application.properties
  ports:
   - "9024:9024"
  depends_on:
   - db
   - broker   
 tcc-front:
  image: mxavier27/tcc-eletrica-portal:latest
  environment:
   - TZ=America/Bahia
   - VUE_APP_URL=http://localhost
  ports:
   - "8082:80"   
 api-gateway:
  image: mxavier27/api-gateway:0.0.1-SNAPSHOT
  volumes:
   - /home/matheus/Projetos/API-GATEWAY/api-gateway/application.yml:/app/application.yml
  ports:
   - "5555:5555"# tcc-eletrica
