# ARQUITECTURAS DE SERVIDORES DE APLICACIONES, META PROTOCOLOS DE OBJETOS, PATRÓN IOC, REFLEXIÓN

Un servidor web que provee un framework IoC el cual permite la construccion de aplicaciones web a partir de un POJO (bean) utilizando reflexion de Java. Se construye un ejemplo de una app que es capaz de entregar paginas HTML e imagenes PNG. El servidor atiende multiples solicitudes no concurrentes.

## Instrucciones para ejecutar

A continuacion, dejo respectivas instrucciones para correr el proyecto adecuadamente tras obtener la direccion a este repositorio GitHub. Igualmente, mas abajo dejare evidencia detallada para garantizar que se entienda su implementacion. La aplicacion debe usarse para fines de prueba y desarrollo.

### Requisitos previos

Para descargar la aplicacion, ya estando aqui, se necesita un equipo de computo con las siguientes caracteristicas:

```
- Java 8+ instalado

- Maven instalado

- JavaScript instalado

- Conexion a internet

- Explorador web

- (RECOMENDACION) Tener todo actualizado
```

### Instalando

Paso a paso

```
1. Descargar el codigo: Bajar el .ZIP correspondiente al repositorio.

2. Extraer el contenido del archivo comprimido.

3. Abrir el Shell de su preferencia.

4. Desde el Shell, muevase a la ubicacion donde extrajo el archivo .ZIP (Deberia estar dentro de la carpeta llamada  "ARQUITECTURAS-EMPRESARIALES-TALLER-4-master").

5. Desde el Shell, escriba "mvn package" (este comando compila, construye y empaqueta el proyecto en un .JAR).

6. Desde el Shell, escriba "java -cp target/Taller4-1.0-SNAPSHOT.jar edu.escuelaing.app.MyApp" para ejecutar la aplicacion.
   Deberia ver un mensaje diciendo "Listo para recibir... ".

7. Abra su explorador web de preferencia y busque en una pestaña incognita lo siguiente:
   
   - "localhost:35001/terminos.html" (SIN LAS COMILLAS) - Pagina html con ruta "src/main/resources/terminos.html".
   - "localhost:35001/style.css" (SIN LAS COMILLAS) - Archivo css con ruta "src/main/resources/style.css".
   - "localhost:35001/app.js" (SIN LAS COMILLAS) - Archivo javascript con ruta "src/main/resources/app.js".
   - "localhost:35001/risas.png" (SIN LAS COMILLAS) - Imagen jpg con ruta "src/main/resources/risas.png".
   - "localhost:35001/web/index.html" (SIN LAS COMILLAS) - Aplicacion con varios tipos de archivos 
      incluidos.

NOTA: Para abortar el shell con el servidor encendido, podemos presionar "CTRL" + "C".
```

Una vez haya terminado, puede cerrar el servidor introduciendo, desde el cliente, la URL "localhost:35001/salir.exe" (SIN LAS COMILLAS) y el servidor se apagara enseguida.



## Evaluacion

Ejemplo de app web:

Archivo HTML:

![image](https://github.com/TeranRyl/ARQUITECTURAS-EMPRESARIALES-TALLER-4/assets/81679109/26d18117-6a9d-4b35-b3f8-296f4c70e8f5)


Archivo CSS:

![image](https://github.com/TeranRyl/ARQUITECTURAS-EMPRESARIALES-TALLER-4/assets/81679109/1f416977-6a2a-4e81-958f-ac7978a71136)


Archivo JS:

![image](https://github.com/TeranRyl/ARQUITECTURAS-EMPRESARIALES-TALLER-4/assets/81679109/7c0fdbd3-ac39-4a77-abad-bbce4562cf72)


Imagen PNG:

![image](https://github.com/TeranRyl/ARQUITECTURAS-EMPRESARIALES-TALLER-4/assets/81679109/b38e86a6-a725-4d9d-881a-78b6df1342d5)


Pagina con varios tipos de archivos incluidos:

![image](https://github.com/TeranRyl/ARQUITECTURAS-EMPRESARIALES-TALLER-4/assets/81679109/516ab361-97e6-48ae-8226-ee04b495c880)










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

## Autores

* **Juan Francisco Teran** - *Trabajo total* - [TeranRyl](https://github.com/TeranRyl)

## Licencia

Este proyecto tiene la licencia GNU General Public License v3.0; consulte el archivo [LICENSE](LICENSE.txt) para obtener más información.

## Reconocimientos

* PurpleBooth - Plantilla para hacer un buen README
* Luis Daniel Benavides - Preparacion para el taller e introduccion al diseño de sistemas de informacion

