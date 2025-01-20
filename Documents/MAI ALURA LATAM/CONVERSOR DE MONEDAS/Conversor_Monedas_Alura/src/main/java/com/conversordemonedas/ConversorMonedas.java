package com.conversordemonedas;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConversorMonedas {

    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/"; // Base URL de la API
    private static final List<String> historialDeConversiones = new ArrayList<>();

    public static void main(String[] args) {
        // Inicializamos el cliente HTTP
        HttpClient clienteHTTP = HttpClient.newHttpClient();

        // Mostramos mensaje de bienvenida
        Scanner scanner = new Scanner(System.in);
        System.out.println("¡Bienvenido al conversor de monedas!");
        System.out.println("Puedes convertir entre cualquier moneda soportada usando su abreviatura, como USD, EUR, etc.");

        while (true) {
            try {
                // Pedimos al usuario que ingrese las monedas de origen y destino
                String monedaOrigen = obtenerMonedaValida(scanner, "Ingresa la moneda de origen (ejemplo: USD): ");
                String monedaDestino = obtenerMonedaValida(scanner, "Ingresa la moneda de destino (ejemplo: EUR): ");

                // Pedimos al usuario la cantidad a convertir
                System.out.print("Ingresa la cantidad a convertir: ");
                double cantidad = obtenerCantidadValida(scanner);

                // Construimos la solicitud para obtener las tasas de cambio
                HttpRequest solicitud = HttpRequest.newBuilder()
                        .uri(URI.create(API_URL + monedaOrigen))
                        .build();

                // Enviamos la solicitud y obtenemos la respuesta
                HttpResponse<String> respuesta = clienteHTTP.send(solicitud, HttpResponse.BodyHandlers.ofString());

                // Verificamos si la respuesta es válida
                if (respuesta.statusCode() != 200) {
                    System.out.println("No se pudo obtener información de la moneda ingresada. Verifica los códigos de moneda e intenta nuevamente.");
                    continue;
                }

                // Parseamos la respuesta JSON
                String cuerpoRespuesta = respuesta.body();
                JsonObject jsonRespuesta = JsonParser.parseString(cuerpoRespuesta).getAsJsonObject();
                JsonObject tasasDeCambio = jsonRespuesta.getAsJsonObject("rates");

                // Verificamos si la moneda destino está en las tasas de cambio
                if (!tasasDeCambio.has(monedaDestino)) {
                    System.out.println("La moneda de destino no es válida. Verifica el código e intenta nuevamente.");
                    continue;
                }

                // Obtenemos la tasa de cambio y realizamos la conversión
                double tasaDestino = tasasDeCambio.get(monedaDestino).getAsDouble();
                double cantidadConvertida = cantidad * tasaDestino;

                // Mostramos el resultado
                System.out.printf("%.2f %s = %.2f %s%n", cantidad, monedaOrigen, cantidadConvertida, monedaDestino);

                // Guardamos la conversión en el historial
                agregarAlHistorial(cantidad, monedaOrigen, monedaDestino, cantidadConvertida);

                // Menú de opciones
                if (!mostrarMenu(scanner)) {
                    break;
                }

            } catch (Exception e) {
                System.out.println("Hubo un error al procesar la solicitud. Por favor, verifica los datos ingresados e intenta nuevamente.");
            }
        }
    }

    // Función para obtener una cantidad válida del usuario
    public static double obtenerCantidadValida(Scanner scanner) {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Cantidad no válida. Por favor, ingresa un número: ");
            }
        }
    }

    // Función para validar y obtener una moneda del usuario
    public static String obtenerMonedaValida(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String moneda = scanner.nextLine().toUpperCase();
            if (moneda.matches("^[A-Z]{3}$")) {
                return moneda;
            } else {
                System.out.println("Moneda no válida. Asegúrate de ingresar solo letras (ejemplo: USD, EUR).");
            }
        }
    }

    // Función para agregar una conversión al historial
    public static void agregarAlHistorial(double cantidad, String monedaOrigen, String monedaDestino, double cantidadConvertida) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String conversion = String.format("%s - %.2f %s a %s = %.2f %s", timestamp, cantidad, monedaOrigen, monedaDestino, cantidadConvertida, monedaDestino);
        historialDeConversiones.add(conversion);
    }

    // Función para mostrar el historial de conversiones
    public static void mostrarHistorial() {
        if (historialDeConversiones.isEmpty()) {
            System.out.println("El historial está vacío.");
        } else {
            System.out.println("\nHistorial de conversiones:");
            for (String conversion : historialDeConversiones) {
                System.out.println(conversion);
            }
        }
    }

    // Función para mostrar el menú de opciones
    public static boolean mostrarMenu(Scanner scanner) {
        System.out.println("\nOpciones:");
        System.out.println("1. Realizar otra conversión");
        System.out.println("2. Ver historial de conversiones");
        System.out.println("3. Salir");
        System.out.print("Elige una opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        switch (opcion) {
            case 1:
                return true;
            case 2:
                mostrarHistorial();
                return true;
            case 3:
                System.out.println("¡Gracias por usar el conversor de monedas! Hasta luego.");
                return false;
            default:
                System.out.println("Opción no válida. Intenta nuevamente.");
                return mostrarMenu(scanner);
        }
    }
}
