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
        case "1": IO.println("Quires hacer un ticket");
            break;
        case "2": IO.println("Quieres anular una multa");
            break;
        case "3": IO.println("\n INFORMACIÓN");
            break;
        case "4": IO.println("Menu de administrador");
            break;
        default:  IO.println("Opción no válida");
            break;
    }
//}while();
}