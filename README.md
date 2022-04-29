# UCOM - integración de sistemas 1
## Práctica de socket en Java

El proyecto incluye 3 pares de archivos Java cliente-servidor

- (Cliente|Servidor)SocketSimple.java : ejemplo de comunicación unidireccional de un solo mensaje
- (Cliente|Servidor)SocketBucle.java  : ejemplo de  comunicación unidireccional multimensaje
- ServidorSocketSimple.java + ClienteSocketBidireccional : ejemplo de  comunicación BIdireccional multimensaje y multicliente

## Tarea
Una empresa de telefonía les contrató para crear un bot de atención al cliente.
El servidor del bot debe implementarse como socket java  con el siguiente comportamiento:

PASO1. Mensaje de bienvenida del bot "Bienvenido a SocketCEL, favor ingrese número de cédula:"<br>
PASO2. Leer número de cédula<br>
PASO3. Simular la validación de número de cédula (número guardado en el código) <br>
PASO3.1 Si el número coincide, responder con "Bienvenido XXXX, en qué podemos ayudarte (ingrese número de opción): <br>
1 - Consultar factura<br>
2-  Realizar reclamo<br>
3-  Salir<br>
PASO 3.2 En caso de que el número no coincida volver a solicitar numero de cédula "Disculpe, ingrese de nuevo número de cedula"<br>

Paso 4. Procesar opción ingresada en el PASO 3.1 según sea el caso:
- Consultar factura: solicitar número de línea y retornar mensaje "La cuenta pendiente de <la línea> es 150.0000 gs".
- Realizar reclamo: debe solicitar el mensaje de reclamo "Favor ingrese su reclamo". El servidor debe retornar "Gracias, hemos registrado su reclamo <mostrar texto del reclamo>". 

PASO 5. Volver al paso 3.1 Mostrando las opciones. En caso de seleccionar la opción de Salida, finalizar la comunicación

#### OBS: para fines académicos puede ser monohilo

## Método de entrega: 
Github. Subir a la sección de tareas de canvas el repositorio git del código y un instructivo de como probar la solución.

## Plazo de entrega: 
Lunes 01/05/2020 hasta las 23.59hs, caso contrario una penalización del 10% por cada día de retraso
