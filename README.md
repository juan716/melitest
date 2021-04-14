# Proyecto Base Implementando Clean Architecture

## Antes de Iniciar

Empezaremos por explicar los diferentes componentes del proyectos y partiremos de los componentes externos, continuando con los componentes core de negocio (dominio) y por último el inicio y configuración de la aplicación.

## Infrastructure

### Helpers

En el apartado de helpers tendremos utilidades generales para los Driven Adapters y Entry Points.

Estas utilidades no están arraigadas a objetos concretos, se realiza el uso de generics para modelar comportamientos genéricos de los diferentes objetos de persistencia que puedan existir, este tipo de implementaciones se realizan basadas en el patrón de diseño [Unit of Work y Repository](https://medium.com/@krzychukosobudzki/repository-design-pattern-bc490b256006)

Estas clases no puede existir solas y debe heredarse su compartimiento en los **Driven Adapters**

## Driven Adapters

Los driven adapter representan implementaciones externas a nuestro sistema, como lo son conexiones a servicios rest, soap, bases de datos, lectura de archivos planos, y en concreto cualquier origen y fuente de datos con la que debamos interactuar.

## Entry Points

Los entry points representan los puntos de entrada de la aplicación o el inicio de los flujos de negocio.

## BASE DE DATOS

La base de datos es motor postgress y está alojada en un recurso RDS en AWS.
En caso de correr local y querer instalar una base de datos. Se debe de crear una base de datos en este caso el aplicativo tiene un adaptador para postgress
CREATE DATABASE databasename CREATE SCHEMA databasename
El aplicativo tiene en sus properties el parametro ddl-auto= update con esto se crea la tabla adn_secuencia automáticamente

## EJECUTAR PROGRAMA LOCAL
Se debe de modificar los parámetros de conexión a la base de datos en el archivo properties del proyecto app-service. 
Luego hacer build del proyecto app-service.
Abrir la terminal y ubicarse en el directorio base del repositorio /applications/app-service/build/libs
allí se encontrará el jar llamado aplications-app-service.jar 
ejecutar el comando java - jar ej: java -jar aplications-app-service,jar Esto ejecutará la aplicación localhost en el puerto 8080.

## CREAR CONTENEDOR EN DOCKER

Se debe de modificar los parámetros de conexión a la base de datos en el archivo properties del proyecto app-service. Luego hacer build del proyecto app-service. Crear la imagen basado en el dockerfile ubicado en la carpeta deployment Crear contenedor Este correrá la aplicación por el puerto 8080

## DESPLIEGUE DE APLICACION EN NUBE HEROKU


Una ayuda para resolver el performance a la necesidad de 1000 a 1 millon de peticiones se podría desplegar el aplicativo en un eks de aws, con sus respectivas configuraciones de deployment, crecimiento horizontal con asignación de recursos, y con un ELB que nos ayude al tema de balanceo de cargas, también evaluar integración a una arquitectura orientada a mensajería(colas).
Se aloja en nube gratuita heroku ya que no tiene costo, como los anteriores.
Se despliega back en nube gratuita heroku como dyno. https://devcenter.heroku.com/articles/deploying-spring-boot-apps-to-heroku

## DOCUMENTACION Y PRUEBA ENDPOINT

Tener en cuenta que la primera petición toma un tiempo mientras el servidor despliega.
https://testmelijq.herokuapp.com/swagger-ui.html
El aplicativo tiene implementado swagger como librería de documentación de servicios expuestos. Luego de ejecutar la aplicación dirigirse a la ruta /swagger-ui.html;


## METODO MUTANT

Se abordó el algoritmo manejando las posiciones por cada letra, luego de obtener esas posiciones se obtienen las posiciones de los vecinos con sentidos vertical-arriba,vertical-abajo,horizontal-derecho, horizontal-izquierdo, oblicuo-arriba,oblicuo-abajo; luego de eso se comparan con la lista de las posiciones iniciales para ver si se repiten más de dos veces.
## COBERTURA DE PRUEBAS UNITARIAS

Para la cobertura de pruebas unitarias se usó jacoco
Se debe de ejecutar el comando gradle test jacocoTestReport Este deja el reporte en la ruta /domain/usecase/build/reports/jacoco/test/html/index.html
https://raw.githubusercontent.com/juan716/melitest/develop/cober.png
