# PATRONES ARQUITECTURALES
Una aplicacion web desplegada en AWS usando EC2 y Docker

## Instrucciones para ejecutar

A continuacion, dejo respectivas instrucciones para correr el proyecto adecuadamente tras obtener la direccion a este repositorio GitHub. Igualmente, mas abajo dejare evidencia detallada para garantizar que se entienda su implementacion. La aplicacion debe usarse para fines de prueba y desarrollo.

### Requisitos previos

Para descargar la aplicacion, ya estando aqui, se necesita un equipo de computo con las siguientes caracteristicas:

```
- Java 17 instalado (si cuenta con otra version, probablemente deba hacer la respectiva modificacion en el archivo "pom.xml")

- Maven instalado

- JavaScript instalado

- Conexion a internet

- Explorador web

- Docker 4.19+

- (RECOMENDACION) Tener todo actualizado
```

### Instalando

Paso a paso

```
1. Descargar el codigo: Bajar el .ZIP correspondiente al repositorio.

2. Extraer el contenido del archivo comprimido.

3. Abrir el Shell de su preferencia.

4. Desde el Shell, muevase a la ubicacion donde extrajo el archivo .ZIP (Deberia estar dentro de la carpeta llamada  "AREP-PATRONES-ARQUITECTURALES-EN-LA-NUBE-master").

5. Desde el Shell, escriba "mvn clean install" (este comando compila el proyecto y coloca el artefacto resultante en tu repositorio local de Maven).

6. Inicie Docker.

7. Desde el Shell, escriba "docker-compose up -d" para generar automáticamente la configuración Docker, los containers e instancias necesarios.

8. Abra su explorador web de preferencia y busque en una pestaña incognita lo siguiente:
   
   - "localhost:35000" (SIN LAS COMILLAS) - Cliente web (RoundRobin).
   - "localhost:35001/logservice?message=*MENSAJE_DE_PRUEBA1*" (SIN LAS COMILLAS) - Servicio GET (LogService1)
   - "localhost:35002/logservice?message=*MENSAJE_DE_PRUEBA2*" (SIN LAS COMILLAS) - Servicio GET (LogService2)
   - "localhost:35003/logservice?message=*MENSAJE_DE_PRUEBA3*" (SIN LAS COMILLAS) - Servicio GET (LogService3)

```


## Evaluacion

Ejemplo de app web:

Cliente web desde Docker:

![image](https://github.com/TeranRyl/AREP-PATRONES-ARQUITECTURALES-EN-LA-NUBE/assets/81679109/a0cb740e-8172-48c8-a2ca-d4e489294c9a)



Servicio GET 1 desde Docker:

![image](https://github.com/TeranRyl/AREP-PATRONES-ARQUITECTURALES-EN-LA-NUBE/assets/81679109/a8116bf4-8dbb-442a-8caa-3ce2cafa6cf2)


Servicio GET 2 desde Docker:

![image](https://github.com/TeranRyl/AREP-PATRONES-ARQUITECTURALES-EN-LA-NUBE/assets/81679109/0d3cb346-d1dd-4f1a-a583-b30d4067dda6)


Servicio GET 3 desde Docker:

![image](https://github.com/TeranRyl/AREP-PATRONES-ARQUITECTURALES-EN-LA-NUBE/assets/81679109/4dfc452a-cb67-4398-9c92-bb878e665704)



Cliente web (RoundRobin) llamando a un servicio (LogServiceX) desde Docker:

![image](https://github.com/TeranRyl/AREP-PATRONES-ARQUITECTURALES-EN-LA-NUBE/assets/81679109/058dbb4e-7be3-49f0-9015-b1a17043d36b)











## Implementacion

NOTA1

En el backend se utilizo codigo fuente puro Java (sin ningun tipo de framework). Se utilizo JavaScript asincrono como cliente web ejecutado como explorador web.
Tambien se utilizaron sockets, los cuales tienen la funcion de comunicar dos programas: un cliente y un servidor. El proposito de esto es poder conectarse al servidor web, por lo que el cliente y servidor se conectaron entre si a traves de sus sockets "cliente" (el servidor abre otro socket adicional). Para esto, el cliente debe buscar comunicarse con el servidor especificando la IP destino a la que pretende conectarse, y por cual puerto, mientras que el servidor abre su respectivo puerto.
Para implementar el servidor web se utilizo HTTP.
El explorador web se conecto al servidor web por medio de sockets (los cuales utilizan el protocolo TCP -> Orientado a Conexion).
El flujo es el siguiente: Se abre la conexion TCP -> El explorador web envia solicitud HTTP al servidor -> El servidor responder al explorador web -> Cierre de conexion.


NOTA 2

El explorador pide y ejecuta lo que el servidor web le envie, y luego el servidor devuelve el resultado (paginas html, archivos css y js e imagenes jpg).
Para esta conversion, se tiene en cuenta el encabezado del archivo y se devuelve en bytes. Este proceso varia segun el tipo de contenido.
App permite crear una aplicacion de backend, la cual estara en el servidor web, y funciona como un framework IoC para la construccion aplicaciones, derivando en una webapp a partir de un POJO cargado. Para esto se explora el directorio raiz para buscar aquellas clases marcadas con la anotacion @component (la cual indica que son componentes-beans) y cargarlas, de tal manera que cuando se invoca el framework este sepa el path de las clases que necesita. En el POJO se especifica cual servicio retornar segun la URI que se indique, atendiendo con la anotacion @GetMapping.
Se hace uso del patron de diseño SINGLETON, para instanciar por unica vez "HttpServer".



## Construido con

* [Java](https://www.oracle.com/co/java/) - Backend
* [Maven](https://maven.apache.org/) - Gestion de ciclo de vida, codigo fuente y dependencias
* [Git/Github](https://git-scm.com/) - Almacenar el codigo fuente
* [IntelliJ IDEA](https://www.jetbrains.com/idea/) - IDE para desarrollo
* [Docker](https://www.docker.com/) - Virtualizacion

## Autores

* **Juan Francisco Teran** - *Trabajo total* - [TeranRyl](https://github.com/TeranRyl)

## Licencia

Este proyecto tiene la licencia GNU General Public License v3.0; consulte el archivo [LICENSE](LICENSE.txt) para obtener más información.

## Reconocimientos

* PurpleBooth - Plantilla para hacer un buen README
* Luis Daniel Benavides - Preparacion para el taller e introduccion al tema

