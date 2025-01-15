import com.google.gson.JsonSyntaxException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MainConversorMonedas {
    public static void main(String[] args) {
            Scanner scan = new Scanner(System.in);
        int opcionElegida = 0;

        ConsultaConversion consulta = new ConsultaConversion();
        Calculo calculo = new Calculo(consulta);
        GeneradorDeArchivos generador = new GeneradorDeArchivos();

        List<String> respuestas = new ArrayList<>();

        String menu = """
                \n---------------------------------------------------
                *** Sea bienvenido al Conversor de Monedas ***
                
                1) Sol Peruano ==>> Dólar Estadounidense
                2) Sol Peruano ==>> Euro
                3) Sol Peruano ==>> Libra Esterlina
                4) Dólar Estadounidense ==>> Sol Peruano
                5) Euro ==>> Sol Peruano
                6) Libra Esterlina ==>> Sol Peruano
                7) Otra opción de conversión
                
                8) Salir
                -----------------------------------------------------
                """;

        while (opcionElegida != 8) {
            try {
                System.out.println(menu);
                opcionElegida = Integer.parseInt(scan.nextLine());

                // Obtener la marca de tiempo actual
                LocalDateTime myDateObj = LocalDateTime.now();
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDate = myDateObj.format(myFormatObj);

                switch (opcionElegida) {
                    case 1:
                        calculo.almacenarValores("PEN", "USD");
                        respuestas.add(formattedDate + " - " + calculo.obtenerMensajeRespuesta());
                        break;
                    case 2:
                        calculo.almacenarValores("PEN", "EUR");
                        respuestas.add(formattedDate + " - " + calculo.obtenerMensajeRespuesta());
                        break;
                    case 3:
                        calculo.almacenarValores("PEN", "GBP");
                        respuestas.add(formattedDate + " - " + calculo.obtenerMensajeRespuesta());
                        break;
                    case 4:
                        calculo.almacenarValores("USD", "PEN");
                        respuestas.add(formattedDate + " - " + calculo.obtenerMensajeRespuesta());
                        break;
                    case 5:
                        calculo.almacenarValores("EUR", "PEN");
                        respuestas.add(formattedDate + " - " + calculo.obtenerMensajeRespuesta());
                        break;
                    case 6:
                        calculo.almacenarValores("GBP", "PEN");
                        respuestas.add(formattedDate + " - " + calculo.obtenerMensajeRespuesta());
                        break;
                    case 7:
                        calculo.almacenarValoresPersonalizados();
                        respuestas.add(formattedDate + " - " + calculo.obtenerMensajeRespuesta());
                        break;
                    case 8:
                        break;
                    default:
                        System.out.println("Ingrese una opción válida");
                }
            } catch (JsonSyntaxException | NullPointerException e) {
                System.out.println("Error. Ingrese solo códigos de moneda válidos.");
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Error. Ingrese un valor numérico válido.");
            }
        }
        generador.guardarJson(respuestas);

        System.out.println("Finalizando programa");
    }
}