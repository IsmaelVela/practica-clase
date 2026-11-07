void main() {
    String opcion;
     do {
         IO.println("""
╔══════════════════════════════════════╗
║          ZONA AZUL - MARTOS          ║
║             MENÚ PRINCIPAL           ║
╠══════════════════════════════════════╣
║  1. Obtener un ticket                ║
║  2. Anular una multa                 ║
║  3. Consultar fecha, tarifas y horas ║
║  4. Menú de administrador            ║
╠══════════════════════════════════════╣
║  Seleccione una opción:              ║
╚══════════════════════════════════════╝
""");
         opcion = IO.readln("¿Que quieres hacer?: ");
    switch (opcion){
        case "1" -> {
            IO.println("-Realizando Ticket-");
            boolean residente, abierto;
            String respuesta;
            double precio1, precio2, precio3, precio4, precio5, importe, precioImporte = 0;
            int duracion, codigo = (int) (Math.random() *900000) + 100000;
            LocalDateTime fechaActual = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            LocalDateTime vencimiento = fechaActual;

            // Le perguntamos si es residente, segun su respuesta de si o no, se le aplica una tarifa distinta
            // Si no pone o S o N se le va a repetir para que tenga que escribirlo bien por narices
            do {
                respuesta = IO.readln("¿Es usted residente? (S/N): ");

            }while (!respuesta.equalsIgnoreCase("S") && !respuesta.equalsIgnoreCase("N"));


            residente = respuesta.equalsIgnoreCase("S");

            if (residente) {
                precio1 = 0.10;
                precio2 = 0.20;
                precio3 = 0.40;
                precio4 = 0.60;
                precio5 = 0.80;
            } else {
                precio1 = 0.25;
                precio2 = 0.45;
                precio3 = 0.85;
                precio4 = 1.25;
                precio5 = 1.75;
            }
            IO.println("""
            ---Tarifas seleccionadas---
            00:15 = %.2f euros
            00:30 = %.2f euros
            01:00 = %.2f euros
            01:30 = %.2f euros
            02:00 = %.2f euros""".formatted(precio1,precio2,precio3,precio4,precio5));
            // Le pedimos que introduzca el importe, segun el dinero que introduzca le determina la duración

            do {
                importe = Double.parseDouble(IO.readln("Introduce el importe: "));

                if (importe < precio1) IO.println("Importe insuficiente, no llega a ninguna tarifa valida");
            } while (importe < precio1);

            if (importe >= precio5) {
                precioImporte = precio5;
            }else if (importe >= precio4) {
                precioImporte = precio4;
            } else if (importe >= precio3) {
                precioImporte = precio3;
            }else if (importe >= precio2) {
                precioImporte = precio2;
            }else if (importe >= precio1) {
                precioImporte = precio1;
            }

            if (precioImporte == 0){
                IO.println("Importe insuficiente");
            } else {
                if (precioImporte == precio1) {
                    duracion = 900;

                } else if (precioImporte == precio2) {
                    duracion = 1790;

                } else if (precioImporte == precio3) {
                    duracion = 3571;

                } else if (precioImporte == precio4) {
                    duracion = 5352;

                } else {
                    duracion = 7200;

                }
                int restantes = duracion;


                while (restantes > 0){
                    //Obtenemos el dia y la hora de la fecha de vencimiento del ticket
                    DayOfWeek dia = vencimiento.getDayOfWeek();
                    LocalTime hora = vencimiento.toLocalTime();

                    abierto = false;
                    //Se comprueba que el dia no sea Domingo
                    if (dia != DayOfWeek.SUNDAY) {
                        //Si esta dentro del horario, de 9 a 14 o 17 a 21 es true,
                        // que quiere decir que esta dentro del horario de estacionamiento
                        if (!hora.isBefore(LocalTime.of(9,0)) &&
                                hora.isBefore(LocalTime.of(14,0))){

                            abierto = true;
                            //Distinto a sabado porque no tiene hoarario de tarde
                        } else if (dia != DayOfWeek.SATURDAY &&
                                !hora.isBefore(LocalTime.of(17,0)) &&
                                hora.isBefore(LocalTime.of(21,0))) {
                            abierto = true;
                        }
                    }
                    // Si se cumple la condicion se le añade un segundo a la fecha y la hora de vencimiento
                    vencimiento = vencimiento.plusSeconds(1);
                    //Si abierto es true le quitamos uno a restantes. Cuando restante llegue a 0  se termina el bucle
                    if (abierto){
                        restantes--;
                    }

                }
                //Se hace la impresión del ticket con todos los datos
                IO.println("""
        ========================
                  TICKET
        ========================
        Codigo: %d
        Fecha importe: %s
        Fecha vencimiento: %s
        Importe pagado: %.2f euros
        Ayuntamiento de Martos
        
        ========================="""
                        .formatted(codigo,fechaActual.format(formato),vencimiento.format(formato),importe));

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
