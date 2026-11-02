void main() {
    String opcion = "";
    // do {
    IO.println("\n--------------MENU PRINCIPAL-----------------");
    IO.println(" 1. Obtener un ticket");
    IO.println(" 2. Anular una multa");
    IO.println(" 3. consultar fecha y hora actuan, tarifas y horarios");
    IO.println(" 4. Menu de administrador");


    opcion = IO.readln("¿Que quieres hacer?: ");
    switch (opcion){
        case "1" -> IO.println("Quires hacer un ticket");
        case "2" -> {
                IO.println("Quieres anular una multa");
            String motivo = IO.readln("Selecciona el motivo de la anulación: ");
            switch (motivo) {
                case "1" -> IO.println("Por exceder el tiempo permitido");
                // codigo
                case "2" -> IO.println("Por carecer de ticket");
                // ccodigo
                default -> IO.println("Opción no válida.");
            }
            break;
        }

        case "3" -> IO.println("\n INFORMACIÓN");
        case "4" -> IO.println("Menu de administrador");
        default -> IO.println("Opción no válida");
    }
//}while();
}