void main() {
    String opcion;
     do {
    IO.println("""
    --------------MENU PRINCIPAL-----------------
    1. Obtener un ticket
    2. Anular una multa
    3. consultar fecha y hora actual, tarifas y horarios
    4. Menu de administrador\n""");


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
           * ANULAR UNA MULTA *
           1. Por exceder el tiempo permitido
           2. Por carecer de ticket\n""");
            String motivo = IO.readln("Selecciona el motivo de la anulación: ");
            int multa = 0, dineroMulta = 0, multaAnulada = 0;
            final int MULTA_EXCESO = 0, MULTA_NO_TICKET = 0;

            switch (motivo) {
                case "1" -> {
                    IO.println("Por exceder el tiempo permitido");
                    int numeroTicket = Integer.parseInt(IO.readln("Introduce tu numero de tu ticker: "));
                    // codigo
                    if ((numeroTicket - 7) % 3 != 0){
                        IO.println("El numero de ticket no es válido");
                    }else{
                        multa = 300; //precio de la multa en euros;
                        IO.println("El importe de las multa es: " + multa);
                        double precio = Double.parseDouble(IO.readln("Introduce el dinero: "));
                        int centimos = (int) Math.round(precio * 100);

                        if (centimos < multa) {
                            IO.println("La cantidad introducida es induficiente");
                        }else{
                            multaAnulada ++;
                            dineroMulta += multa;
                            IO.println("""
                                    \n TICKET
                                    Mmotivo: Por exceder el tiempo permitido.
                                    Numero de tickter: %d
                                    Pago: %.2f Euros
                                    """.formatted(numeroTicket, multa /100.0));
                            if (centimos > multa) {
                                IO.println("Cambio: " + (centimos - multa) / 100.0 + " Euros");
                            }
                        }
                    }

                }
                case "2" -> {
                    IO.println("Por carecer de un ticket");
                    int numeroTicket = Integer.parseInt(IO.readln("Introduce tu numero de tu ticker: "));
                    // codigo
                    if ((numeroTicket - 7) % 3 != 0){
                        IO.println("El numero de ticket no es válido");
                    }else{
                        multa = 600; //precio de la multa en centimos;
                        IO.println("El importe de las multa es: " + multa);
                        double precio = Double.parseDouble(IO.readln("Introduce el dinero: "));
                        int centimos = (int) Math.round(precio * 100);

                        if (centimos < multa) {
                            IO.println("La cantidad introducida es induficiente");
                        }else{
                            multaAnulada ++;
                            dineroMulta += multa;
                            IO.println("""
                                    \n TICKET
                                    Mmotivo: Por exceder el tiempo permitido.
                                    Numero de tickter: %d
                                    Pago: %.2f Euros
                                    """.formatted(numeroTicket, multa /100.0));
                            if (centimos > multa) {
                                IO.println("Cambio: " + (centimos - multa) / 100.0 + " Euros");
                            }
                        }
                    }
                }
                default -> IO.println("Opción no válida.");
            }
        }
        case "3" -> IO.println("Consultar fecha y hora actual, tarifas y horarios");
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
