# Soaint Examen

Se  desarrollo el proyecto basada en un carrito de comprando utilizando Java 8, RxJava2, Junit4, MySQL

## Comenzando 🚀


Descargar el proyecto de la ruta https://github.com/pedrolps12/soaintexam.git
Cambiar la rama a master: git checkout master

### Pre-requisitos 📋

Instalar MysqlServer 8.0.21
Crear una base de datos con el nombre soaintexam
Configurar las credenciales en el proyecto en el archivo application.yml
De acuerdo al IDE de preferencia instalar Lombok.

### Instalación 🔧

De acuerdo al Ide ejecutar la clase ApplicationMain para levantar el proyecto.

## Ejecutando las pruebas ⚙️

_Explica como ejecutar las pruebas automatizadas para este sistema_

### Analice las pruebas end-to-end 🔩

_Explica que verifican estas pruebas y por qué_

```
Da un ejemplo
```

### Ejemplos del examen ⌨️

Archivo de logs (Línea 52 caso de error en el que se quiera eliminar un registro que no exista)
https://i.imgur.com/juftFfY.png

Prueba unitaria del controlador
https://i.imgur.com/OXCcxhh.png


## Con sus propias palabras indique de qué forma podría mejorar la seguridad y optimizar el carrito de compras. 📦

El proyecto actualmente no cuenta con una adecuada gestion de perfiles, se debería iniciar identificando los roles que tendrá el sistema y los niveles de acceso. Se podría mejorar implementando oauth para facilitar el logeo de usuarios que no dispongan de una cuenta.

El detalle de ventas del carrito no esta manejando la cantidad de productos.


## Autores ✒️

* **Pedro Peña Salazar** - *Trabajo Inicial* - [pedrolps12](https://github.com/pedrolps12)

