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
            IO.println("Quieres hacer un ticket");
            //Codigo
            String residente =  IO.readln("¿Eres residente Si/No?: ");
            if (residente.equals("si")){
                double dinero = Double.parseDouble(IO.readln("Introduce el dinero: "));
            }
        }

        case "2" -> {
            IO.println("""
           * ANULAR UNA MULTA *
           1. Por exceder el tiempo permitido
           2. Por carecer de ticket\n""");
            String motivo = IO.readln("Selecciona el motivo de la anulación: ");
            switch (motivo) {
                case "1" -> IO.println("Por exceder el tiempo permitido");
                // codigo
                case "2" -> IO.println("Por carecer de un ticket");
                // ccodigo
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
}while(opcion.equals("0"));
}
