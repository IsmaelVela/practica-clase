void main() {
    String opcion;
    int ticketsGenerados = 0, multasAnuladas = 0;
    double dineroTickets = 0, dineroMultas = 0;
    boolean apagar = false;
     do {
         IO.println("""
╔══════════════════════════════════════════════╗
║              MENÚ PRINCIPAL                  ║
╠══════════════════════════════════════════════╣
║                                              ║
║       1. Obtener un ticket                   ║
║                                              ║
║       2. Anular una multa                    ║
║                                              ║
║       3. Consultar fecha, hora,              ║
║          tarifas y horarios                  ║
║                                              ║
║       4. Menú de administrador               ║
║                                              ║
╠══════════════════════════════════════════════╣
║          AYUNTAMIENTO DE MARTOS              ║
╚══════════════════════════════════════════════╝
""");
    opcion = IO.readln("¿Que quieres hacer?: ");
    switch (opcion){
        case "1" -> {
            IO.println("-Realizando Ticket-");
            boolean residente, abierto;
            String respuesta;
            double precio1, precio2, precio3, precio4, precio5, importe, precioImporte = 0;
            int duracion, aleatorio = (int) (Math.random() * 300000) + 100000;
            int codigo = aleatorio * 3 +7;
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
                precio5 = 1.70;
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
                ticketsGenerados ++;
                dineroTickets += importe;
                IO.println("""
╔══════════════════════════════════════╗
║                 TICKET               ║
╠══════════════════════════════════════╣
║                                      ║
║  Código: %d
║                                      ║
║  Fecha de emisión:                   ║
║  %s
║                                      ║
║  Fecha de vencimiento:               ║
║  %s
║                                      ║
║  Importe pagado: %.2f euros
║                                      ║
║       AYUNTAMIENTO DE MARTOS         ║
║                                      ║
╚══════════════════════════════════════╝
""".formatted(codigo, fechaActual.format(formato), vencimiento.format(formato), importe));
            }
        }
        case "2" -> {
            //Display del menú en que el pondremos al ususario que elija que motivo tiene para pagar la multa
            IO.println("""
           * ANULAR UNA MULTA *
           1. Por exceder el tiempo permitido
           2. Por carecer de ticket\n""");

            String motivoMulta = IO.readln("Selecciona el motivo de la anulación: ");

            double multa = 0;
            //Si selecciona 1 la multa a pagar seria de 3 euros
            if (motivoMulta.equals("1")) {
                multa = 3.00;
                IO.println("Multa por exceder el tiempo: 3,00 euros");
                //Introduce un ticket, sea valido o  no
                int numeroTicket = Integer.parseInt(
                        IO.readln("Introduce el número de ticket: "));
                //Aplicando la formula que tenemos para generar al codigo pero ahora aplicado a la inversa.
                //En el caso de que sea distinto a cero no es valido, y si si es valido
                if ((numeroTicket - 7) % 3 != 0) {
                    IO.println("El número de ticket no es válido");
                    multa = 0;
                }
                //Caso 2, no tener un ticket. la multa es de 6 euros
            } else if (motivoMulta.equals("2")) {
                multa = 6.00;
                IO.println("Multa por carecer de ticket: 6,00 euros");

            } else {
                IO.println("Opción no válida");
            }
            //Ahora le pedimos al usuario que introduzaca el dinero
            if (multa > 0) {
                double pagoMulta = Double.parseDouble(IO.readln("Introduce el dinero: "));

                if ( pagoMulta >= multa) {
                    IO.println("La multa se ha pagado correctamente");
                    //Se geenra el codigo de la multa
                    multasAnuladas++;
                    dineroMultas += multa;
                    int aleatorioMulta = (int) (Math.random() * 9000000) + 1000000;
                    int codigoMulta = aleatorioMulta * 2 + 15 ;
                    //Impresion del ticket de multa
                    IO.println("""
╔══════════════════════════════════════╗
║                                      ║
║          TICKET DE ANULACIÓN         ║
║                                      ║
╠══════════════════════════════════════╣
║                                      ║
║  Código de multa: %d
║                                      ║
║  Motivo: %s
║                                      ║
║  Importe pagado: %.2f euros
║                                      ║
║       AYUNTAMIENTO DE MARTOS         ║
║                                      ║
╚══════════════════════════════════════╝
""".formatted(codigoMulta, motivoMulta.equals("1") ? "Exceso del tiempo permitido" : "No tiene ticket", multa));
                } else {
                    IO.println("La cantidad introducida es insuficiente");
                }
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
        }

        case "4" -> {
            IO.println("""
            * MENU DE ADMINISTRADOR *
            1. Dinero ingresado en tickets y tickets generados
            2. Dinero ingresado por multas y multas anuladas
            3. Apagar el software\n""");

            String menuAdministrador = IO.readln("Que quieres ver: ");
            if (menuAdministrador.equals("1")){
                IO.println("""
┌──────────────────────────────────────────────────────────────────────────────┐
│                         RESUMEN DE TICKETS                                   │
├──────────────────────────────────────────────────────────────────────────────┤
│       Tickets generados:                     %d                              │
│       Dinero ingresado en tickets:           %.2f euros                      │
├──────────────────────────────────────────────────────────────────────────────┤
│                         AYUNTAMIENTO DE MARTOS                               │
└──────────────────────────────────────────────────────────────────────────────┘
""".formatted(ticketsGenerados, dineroTickets));
            } else if (menuAdministrador.equals("2")) {
                IO.println("""
┌──────────────────────────────────────────────────────────────────────────────┐
│                         RESUMEN DE MULTAS                                    │
├──────────────────────────────────────────────────────────────────────────────┤
│       Multas anuladas:                         %d                            │
│       Dinero ingresado por multas:             %.2f euros                    │
├──────────────────────────────────────────────────────────────────────────────┤
│                         AYUNTAMIENTO DE MARTOS                               │
└──────────────────────────────────────────────────────────────────────────────┘
""".formatted(multasAnuladas, dineroMultas));
            }else if (menuAdministrador.equals("3")){
                IO.println("Apagando el doftware");
                apagar = true;
            }else IO.println("Opcion no valida");
        }
        default -> IO.println("Opción no válida");
    }
}while(!apagar);
}
