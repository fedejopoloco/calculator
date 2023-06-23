# Calculator

App con encargada de recibir 2 numeros, sumarlos y aplicarle la suba de un porcentaje obtenido por un servicio externo.

## Requisitos previos

Asegúrate de tener instalado lo siguiente en tu sistema:

- Java Development Kit (JDK) 11 o superior
- Apache Maven
- Docker

## Configuración del entorno

1. Clona el repositorio:

   CMD:
   - git clone https://github.com/fedejopoloco/calculator


2. Levantar postgres con docker:

   CMD:
   - docker pull postgres
   - docker run --name postgres-dev -e POSTGRES_PASSWORD=1234 -e POSTGRES_USER=postgres -e POSTGRES_DB=calculator -p 5432:5432 -d postgres


3. Navega hasta el directorio raíz del proyecto:

   CMD:
   - cd nombre-del-proyecto


4. Compila la aplicación con maven:

   CMD:
   - mvn clean install


## Ejecución de la aplicación

Para ejecutar la aplicación, utiliza el siguiente comando:

shell
java -jar target/nombre-del-archivo-jar.jar


La aplicación se ejecutará en `http://localhost:8080`.

## Documentación de la API

La documentación de la API se encuentra en `http://localhost:8080/swagger-ui.html`.

## Contribución

1. Haz un fork del repositorio.
2. Crea una rama con el nombre de tu función o corrección de bug: `git checkout -b nombre-de-la-rama`.
3. Realiza los cambios necesarios y realiza commits: `git commit -m "Descripción de los cambios"`.
4. Sube los cambios a tu repositorio fork: `git push origin nombre-de-la-rama`.
5. Abre un pull request en este repositorio.

## Licencia

Indica aquí la licencia bajo la cual se distribuye la aplicación.

## Contacto

Si tienes alguna pregunta o sugerencia, no dudes en contactarnos a través de [correo electrónico] o [crear un problema] en GitHub.

