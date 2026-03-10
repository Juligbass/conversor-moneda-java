import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Main {

    public static void main(String[] args) {

        String apiKey = "0f01798682e91bf8ae422aaf";
        String direccion = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/USD";

        try {

            HttpClient cliente = HttpClient.newHttpClient();

            HttpRequest solicitud = HttpRequest.newBuilder()
                    .uri(URI.create(direccion))
                    .GET()
                    .build();

            HttpResponse<String> respuesta = cliente.send(
                    solicitud,
                    HttpResponse.BodyHandlers.ofString()
            );

            JsonObject json = JsonParser.parseString(respuesta.body()).getAsJsonObject();
            JsonObject rates = json.getAsJsonObject("conversion_rates");

            double ars = rates.get("ARS").getAsDouble();
            double brl = rates.get("BRL").getAsDouble();
            double cop = rates.get("COP").getAsDouble();

            Scanner scanner = new Scanner(System.in);

            int opcion = 0;
            double monto;

            while (opcion != 7) {

                System.out.println("****************************************");
                System.out.println("Sea bienvenido/a al Conversor de Moneda");
                System.out.println("1) Dólar → Peso argentino");
                System.out.println("2) Peso argentino → Dólar");
                System.out.println("3) Dólar → Real brasileño");
                System.out.println("4) Real brasileño → Dólar");
                System.out.println("5) Dólar → Peso colombiano");
                System.out.println("6) Peso colombiano → Dólar");
                System.out.println("7) Salir");
                System.out.println("****************************************");
                System.out.println("Elija una opción válida:");

                opcion = scanner.nextInt();

                switch (opcion) {

                    case 1:
                        System.out.println("Ingrese monto en USD:");
                        monto = scanner.nextDouble();
                        System.out.println("Resultado: " + (monto * ars) + " ARS");
                        break;

                    case 2:
                        System.out.println("Ingrese monto en ARS:");
                        monto = scanner.nextDouble();
                        System.out.println("Resultado: " + (monto / ars) + " USD");
                        break;

                    case 3:
                        System.out.println("Ingrese monto en USD:");
                        monto = scanner.nextDouble();
                        System.out.println("Resultado: " + (monto * brl) + " BRL");
                        break;

                    case 4:
                        System.out.println("Ingrese monto en BRL:");
                        monto = scanner.nextDouble();
                        System.out.println("Resultado: " + (monto / brl) + " USD");
                        break;

                    case 5:
                        System.out.println("Ingrese monto en USD:");
                        monto = scanner.nextDouble();
                        System.out.println("Resultado: " + (monto * cop) + " COP");
                        break;

                    case 6:
                        System.out.println("Ingrese monto en COP:");
                        monto = scanner.nextDouble();
                        System.out.println("Resultado: " + (monto / cop) + " USD");
                        break;

                    case 7:
                        System.out.println("Gracias por usar el conversor.");
                        break;

                    default:
                        System.out.println("Opción inválida.");
                }

                System.out.println();
            }
// prueba de commit
            // prueba de commit
            // prueba de commit

        } catch (Exception e) {
            System.out.println("Error al consultar la API");
        }
    }
}