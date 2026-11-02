void main() {
    String opcion = "";
     do {
    IO.println("\n--------------MENU PRINCIPAL-----------------");
    IO.println(" 1. Obtener un ticket");
    IO.println(" 2. Anular una multa");
    IO.println(" 3. consultar fecha y hora actuan, tarifas y horarios");
    IO.println(" 4. Menu de administrador");


    opcion = IO.readln("¿Que quieres hacer?: ");
    switch (opcion){
        case "1" -> IO.println("Quires hacer un ticket");
        case "2" -> {
            IO.println("\n ANULAR UNA MULTA");
            IO.println(" 1. Por exceder el tiempo permitido");
            IO.println(" 2. Por carecer de ticket");
            String motivo = IO.readln("Selecciona el motivo de la anulación: ");
            switch (motivo) {
                case "1" -> IO.println("Por exceder el tiempo permitido");
                // codigo
                case "2" -> IO.println("Por carecer de un ticket");
                // ccodigo
                default -> IO.println("Opción no válida.");
            }
            break;
        }
        case "3" -> IO.println("Consultar fecha y hora actual, tarifas y horarios");
        case "4" -> {
            IO.println("\n MENU DE ADMINISTRADOR");
            IO.println(" 1. Dinero ingrsedo en tickets y tickets generados");
            IO.println(" 2. Dinero ingresado por multas y multas anuladas");
            IO.println(" 3. Apagar el software");

            String menuAdministrador = IO.readln("Que quieres ver: ");
            switch (menuAdministrador){
                case "1" -> IO.println("Tickets generados");
                //codigp
                case "2" -> IO.println("Multas abuladas");
                //codigo
                case "3" -> IO.println("Apagando el software");
                //codigo
            }
        }
        default -> IO.println("Opción no válida");
    }
}while(opcion.equals("0"));
}