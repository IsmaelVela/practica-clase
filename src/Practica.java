/*Ponemos el local date antes del "void main" porwue sirve para incorporar clases que ya existen en Java
y que necesitamos utilizar en nuestro programa, tambien porque Java necesita saber qué clases vas a utilizar
antes de que empecemos a ejecutar nuestro programa.*/
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
void main() {
    //DECLARAMOS LAS VARIABLES
    String opcion;
     do {
         IO.println("""
╔══════════════════════════════════════╗
║          ZONA AZUL - MARTOS          ║
║             MENU PRINCIPAL           ║
╠══════════════════════════════════════╣
║  1. Obtener un ticket                ║
║  2. Anular una multa                 ║
║  3. Consultar fecha, tarifas y horas ║
║  4. Menu de administrador            ║
╠══════════════════════════════════════╣
║  Seleccione una opcion:              ║
╚══════════════════════════════════════╝
""");
    opcion = IO.readln("¿Que quieres hacer?: ");
    switch (opcion){
        case "1" -> {
            IO.println("Quieres hacer un ticket");
            int ticketsGenerados = 0;
            //Codigo
            String residente =  IO.readln("¿Eres residente Si/No?: ");
            if (residente.equals("si") || (residente.equals("no"))){ //comprobamos si es residente o np
                double dinero = Double.parseDouble(IO.readln("Introduce el dinero: "));
                int centimos = (int) Math.round(dinero * 100);
            }
        }
        case "2" -> {
            IO.println("""
           \n ANULAR UNA MULTA 
           1. Por exceder el tiempo permitido
           2. Por carecer de ticket\n""");
            String motivo = IO.readln("Selecciona el motivo de la anulación: ");
            int dineroMulta = 0, multaAnulada = 0;
            int numeroTicket = 0;
            boolean titeckValido = false;
            int ticketsGenerados = 0;

            switch (motivo) {
                case "1" -> {
                    IO.println("Por exceder el tiempo permitido");
                    numeroTicket = Integer.parseInt(IO.readln("Introduce tu numero de tu ticker: "));
                    //Comprobaamos que el numero de ticktec introducido sea valido, con el que se genero anteriormente

                    titeckValido = false;
                    /*HAcemos un bucle for que recorre los tickets que hemos generado anteriormente para comprobar
                     si el número que se introducido coincide con alguno de ellos.*/

                    for (int i = 1; i <= ticketsGenerados; i++) {
                        if (numeroTicket == i ) {
                            titeckValido = true;
                        }
                    }
                    // codigo para el calculo de los ticktecks
                    if (titeckValido) {
                        IO.println("Ticket válido");
                        double multaExceso = 3.0;
                        IO.println("El importe de las multa es: " + multaExceso + " euros");
                        double precio = Double.parseDouble(IO.readln("Introduce el dinero: "));
                        //int centimos = (int) Math.round(precio * 100);
                        if (precio < multaExceso) {
                            IO.println("La cantidad introducida es induficiente");
                        } else {
                            multaAnulada++;
                            dineroMulta += multaExceso;
                            IO.println("""
        ╔══════════════════════════════════════╗
        ║            TICKET ZONA AZUL          ║
        ╠══════════════════════════════════════╣
        ║                                      ║
        ║   Motivo: Por exceder el tiempo      ║
        ║           permitido.                 ║
        ║                                      ║
        ║   Nº de ticket: %-21d║
        ║   Pago:         %-14.2f Euros ║
        ║                                      ║
        ╠══════════════════════════════════════╣
        ║   - - - - - - - - - - - - - - - -    ║
        ║   |||| ||| | |||| || ||| | ||||| |   ║
        ╚══════════════════════════════════════╝
        """.formatted(numeroTicket, multaExceso));
                            IO.println(" \n ✓ ¡PAGO REALIZADO CORRECTAMENTE!");
                            if (precio > multaExceso) {
                                IO.println("Su cambio es de: " + (precio - multaExceso) + " euros");
                            } else if (precio == multaExceso) {
                                IO.println("No tiene cambio");
                            }
                        }

                    }else IO.println("El numero de ticket introducido no es válidp");
                }
                case "2" -> {
                    IO.println("Por carecer de un ticket");
                    double multaNoTicket = 6.0;
                    IO.println("El importe de la multa es de: " + multaNoTicket + " euros");
                    double precio = Double.parseDouble(IO.readln("Introduce el dinero: "));
                    if (precio < multaNoTicket) {
                        IO.println("La cantidad introducida es induficiente");
                    } else {
                        /*Aqui actualiza el contador de multas anuladas y acumula el importe de la multa en el total de dinero*/
                        multaAnulada++;
                        dineroMulta += multaNoTicket;
                        IO.println("""
        ╔══════════════════════════════════════╗
        ║          TICKET ZONA AZUL            ║
        ╠══════════════════════════════════════╣
        ║                                      ║
        ║   Motivo: Por carecer de ticket.     ║
        ║                                      ║
        ║   Nº de ticket: %-21d║
        ║   Pago:         %-14.2f Euros ║
        ║                                      ║
        ╠══════════════════════════════════════╣
        ║   - - - - - - - - - - - - - - - -    ║
        ║   |||| ||| | |||| || ||| | ||||| |   ║
        ╚══════════════════════════════════════╝
        """.formatted(numeroTicket, multaNoTicket));
                        IO.println(" \n ✓ ¡PAGO REALIZADO CORRECTAMENTE!");
                        /*Comprobsmos si el dinero introducido es mayor que el importe de la multa para calcular
                        el cambio que debemos devolver*/
                        if (precio > multaNoTicket) {
                            IO.println("Su cambio es de: " + (precio - multaNoTicket) + " euros");
                        } else if (precio == multaNoTicket) {
                            IO.println("No tiene cambio");
                        }
                    }

                }
                default -> IO.println("Opción no válida.");
            }
        }
        case "3" -> {
            IO.println("Consultar fecha y hora actual, tarifas y horarios");

        java.time.format.DateTimeFormatter formato =
                java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        IO.println("""
        ╔════════════════════════════════════════════╗
        ║                INFORMACIÓN                 ║
        ╠════════════════════════════════════════════╣
        ║  Fecha y hora actual: %-21s║
        ╠════════════════════════════════════════════╣
        ║                  TARIFAS                   ║
        ║                                            ║
        ║  Duración    No residentes   Residentes    ║
        ║  ────────────────────────────────────────  ║
        ║  00:15:00    0,25 €          0,10 €        ║
        ║  00:29:50    0,45 €          0,20 €        ║
        ║  00:59:31    0,85 €          0,40 €        ║
        ║  01:29:12    1,25 €          0,60 €        ║
        ║  02:00:00    1,70 €          0,80 €        ║
        ╠════════════════════════════════════════════╣
        ║                 HORARIOS                   ║
        ║                                            ║
        ║  Lunes a viernes:                          ║
        ║    09:00 - 14:00 y 17:00 - 21:00           ║
        ║  Sábado: 09:00 - 14:00                     ║
        ║  Domingo: cerrado                          ║
        ╚════════════════════════════════════════════╝
        """.formatted(java.time.LocalDateTime.now().format(formato)));

        IO.readln("\nPulsa ENTER para volver al menú principal");
        }
        case "4" -> {
            IO.println("""
            * MENU DE ADMINISTRADOR *
            1. Dinero ingresado en tickets y tickets generados
            2. Dinero ingresado por multas y multas anuladas
            3. Apagar el software\n""");

            String menuAdministrador = IO.readln("Que quieres ver: ");
            switch (menuAdministrador){
                case "1" -> IO.println("Tickets generados");
                //codigo
                case "2" -> IO.println("Multas anuladas");
                //codigo
                case "3" -> IO.println("Apagando el software");
                //codigo
            }
        }
        default -> IO.println("Opción no válida");
    }
}while(!opcion.equals("0"));
}
