# Calculator

App encargada de recibir 2 numeros, sumarlos y aplicarle la suba de un porcentaje obtenido por un servicio externo.

## Requisitos previos

Asegúrate de tener instalado lo siguiente en tu sistema:

- Java Development Kit (JDK) 11 o superior
- Apache Maven
- Docker

## Configuración del entorno con Dockerhub

CMD:
```
   - docker login (Ingresar usuario y contraseña)
   - docker pull federicoprospitti/calculator-percentage:5.0.0
```

#### Descargar archivo "compose.yaml" en algún directorio:

https://drive.google.com/file/d/1d4mMs1GralcjICsmUBEd6fW9wF9LiDxo/view?usp=sharing

#### Ingresar al directorio donde se descargo el .yaml y corrrer el comando:

   CMD:
```
   - docker-compose up
```   
   

## Configuración del entorno descargando el proyecto con git

1. Clonar el repositorio:

   CMD:
```
   - git clone https://github.com/fedejopoloco/calculator
```

2. Levantar postgres con docker:

   CMD:
```
   - docker pull postgres
   - docker run --name postgres-dev -e POSTGRES_PASSWORD=1234 -e POSTGRES_USER=postgres -e POSTGRES_DB=calculator -p 5432:5432 -d postgres
```

3. Navegar hasta el directorio raíz del proyecto:

   CMD:
```
   - cd nombre-del-proyecto
```

4. Compilar la aplicación con maven:

   CMD:
```
   - mvn clean install
```
5. Construir la imagen doker

   CMD:
```
   - docker build -t calculator-percentage .
```

## Ejecución de la aplicación

Para ejecutar la aplicación, utiliza el siguiente comando en la raiz del proyecto:

   CMD:
```
   - docker-compose up
```

La aplicación se ejecutará en `http://localhost:8080`.  

## Probar la aplicacción

#### La documentación de la API se encuentra en http://localhost:8080/swagger-ui/ .
#### Coleccion postman: https://www.postman.com/fprospitti/workspace/federico-prospitti/

## Contacto

Si tienes alguna pregunta o sugerencia, no dudes en contactarme a través de federico1236@gmail.com o crear un problema en GitHub.

Github: https://github.com/fedejopoloco/calculator/tree/master
