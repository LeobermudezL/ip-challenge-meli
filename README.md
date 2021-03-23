# ip-challenge-meli

Proyecto para challenge tecnico de Geolocalizacion en MercadoLibre.

# El siguiente proyecto fue construido en las siguientes tecnologias:

- Gradle 6.0.1
- Java 11
- Springboot 2.2.5.RELEASE
- Caffeine cache
- Docker

El proyecto comple con las 3 primisas dadas en las cuales se debe ejecutar el codigo para la verificacion de Geolocalizacion de Ips para el consumo de las diversas Apis que integran el consumo de este MicroServicio.

# Para este microservicio se implementaron las siguientes APis:
 - url: https://api.ip2country.info/ip
 - url: https://restcountries.eu/rest/v2/alpha
 - url: https://api.exchangeratesapi.io/latest
 
 Con la finalidad de poder obtener la informacion necesaria para su uso a gran escala.
 
 Para el deployment de la aplicacion son necesarios los siguientes pasos:
 
 # DOCKER
 
 1- Descargar el proyecto localmente
 
 2- Ejecutar localmente en consola sobre la ruta del proyecto el siguiente comando : # docker build -t ipchallengemeli .
 
 3- Esperar que docker descargue correctamente las imagenes expuestas en el dockerfile del dockerhub.
 
 4- Una vez realizado el paso 3, implementar el siguiente comando : # docker run -d -p 8090:8090 --name ipchallengemeli ipchallengemeli
 
 # Se agrego swagger para la visualizacion y utilizacion de los servicios
 
- http://localhost:8090/swagger-ui.html#/
 
 # Autor
 
 [Eleonay Bermudez](https://www.linkedin.com/in/eleonay-leandro-bermudez-063a4915a/)


