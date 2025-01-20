# Conversor de Monedas

Este proyecto es un conversor de monedas desarrollado en Java. Permite realizar conversiones entre diferentes monedas utilizando una API externa para obtener las tasas de cambio más actualizadas. El sistema es interactivo, fácil de usar y mantiene un historial de todas las conversiones realizadas.

## Funcionalidades

- **Conversión entre monedas**: El usuario puede convertir una cantidad de una moneda a otra utilizando los códigos internacionales de monedas (por ejemplo, USD, EUR, etc.).
- **Historial de conversiones**: El programa guarda un historial con las conversiones realizadas, incluyendo la fecha y hora de cada transacción.
- **Interfaz simple**: La interfaz es de línea de comandos, donde el usuario debe ingresar las monedas y la cantidad a convertir.
- **Soporte para múltiples monedas**: Puedes convertir entre todas las monedas soportadas por la API de tasas de cambio.

## Tecnologías Usadas

- **Java**: Lenguaje principal para el desarrollo de la aplicación.
- **API externa de tasas de cambio**: Se utiliza una API para obtener las tasas de conversión de las monedas.
- **Maven**: Para la gestión de dependencias y ejecución del proyecto.

## Instalación

### Requisitos

1. **JDK 11 o superior**: Asegúrate de tener Java instalado en tu sistema. Si no lo tienes, puedes descargarlo desde [aquí](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).
2. **Maven**: Para gestionar dependencias y ejecutar el proyecto. Puedes instalar Maven desde [aquí](https://maven.apache.org/download.cgi).

### Pasos para ejecutar el proyecto

1. Clona el repositorio:

   ```bash
   git clone https://github.com/tu_usuario/Conversor_Monedas.git
2. Accede al directorio del proyecto:
   cd Conversor_Monedas
3. Instala las dependencias (si usas Maven):
   mvn install
4. Ejecuta el proyecto:mvn exec:java
   Este comando ejecutará el programa y te permitirá empezar a hacer conversiones entre monedas

## Ejemplo de Uso
Cuando ejecutes el programa, verás algo como esto en la terminal:
¡Bienvenido al conversor de monedas!
Puedes convertir entre cualquier moneda soportada usando su abreviatura, como USD, EUR, etc.

Ingresa la moneda de origen (ejemplo: USD): USD
Ingresa la moneda de destino (ejemplo: EUR): EUR
Ingresa la cantidad a convertir: 100
100.00 USD = 85.30 EUR

Opciones:
1. Realizar otra conversión
2. Ver historial de conversiones
3. Salir
   Elige una opción: 1

## Historial de Conversiones
El programa guarda un historial de conversiones. Cuando elijas la opción "Ver historial de conversiones", se mostrará una lista con todos los registros, incluyendo la fecha y hora de cada conversión realizada.

## Licencia
Este proyecto está licenciado bajo la Licencia MIT - mira el archivo LICENSE.md para más detalles.
