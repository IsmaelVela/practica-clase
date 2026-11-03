import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
void main() {
    Boolean residente, abierto = false;
    String respuesta;
    double precio1, precio2, precio3, precio4, precio5, importe, precioImporte = 0;
    int duracion = 0, codigo = (int) (Math.random() *900000) + 100000;
    LocalDateTime fechaActual = LocalDateTime.now();
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    LocalDateTime vencimiento = fechaActual;


    respuesta = IO.readln("¿Es usted residente? (S/N): ");

    residente = respuesta.equalsIgnoreCase("S");

    switch (residente ? 1 : 0) {
        case 1:
            precio1 = 0.10;
            precio2 = 0.20;
            precio3 = 0.40;
            precio4 = 0.60;
            precio5 = 0.80;
            break;
        default:
            precio1 = 0.25;
            precio2 = 0.45;
            precio3 = 0.85;
            precio4 = 1.25;
            precio5 = 1.75;
            break;
    }
    IO.println("""
            ---Tarifas seleccionadas---
            00:15 = %.2f euros
            00:30 = %.2f euros
            01:00 = %.2f euros
            01:30 = %.2f euros
            02:00 = %.2f euros""".formatted(precio1,precio2,precio3,precio4,precio5));

    importe = Double.parseDouble(IO.readln("Introduce el importe: "));

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
        IO.println("Tarifa aplicada %.2f euros".formatted(precioImporte));
    }

    if (precioImporte == precio1) {
        duracion = 900;

    } else if (precioImporte == precio2) {
        duracion = 1790;

    } else if (precioImporte == precio3) {
        duracion = 3571;

    } else if (precioImporte == precio4) {
        duracion = 5352;

    } else if (precioImporte == precio5) {
        duracion = 7200;

    }
    int restantes = duracion;


    while (restantes > 0){

        DayOfWeek dia = vencimiento.getDayOfWeek();
        LocalTime hora = vencimiento.toLocalTime();

        abierto = false;

        if (dia != DayOfWeek.SUNDAY) {
            if (!hora.isBefore(LocalTime.of(9,0)) &&
                    hora.isBefore(LocalTime.of(14,0))){
                abierto = true;
            } else if (dia != DayOfWeek.SATURDAY &&
                    !hora.isBefore(LocalTime.of(17,0)) &&
                    hora.isBefore(LocalTime.of(21,0))) {
                abierto = true;
            }
        }
        vencimiento = vencimiento.plusSeconds(1);
        if (abierto){
            restantes--;
        }

    }
IO.println("""
        ========================
                  TICKET
        ========================
        Codigo: %d
        Fecha importe: %s
        Fecha vencimiento: %s
        Importe pagado: %.2f euros
        
        ========================="""
        .formatted(codigo,fechaActual.format(formato),vencimiento.format(formato),importe));

}