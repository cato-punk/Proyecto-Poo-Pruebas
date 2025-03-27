import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class RecopilacionDatosAnimal {

    public static void main(String[] args) {
        Animal animal = pedirDatos();
        System.out.println("\nDatos del animal ingresados:");
        System.out.println(animal); // Imprime los datos del animal

        Scanner scanner = new Scanner(System.in);
        System.out.print("\n¿Desea guardar estos datos? (s/n): ");
        String respuesta = scanner.nextLine();

        if (respuesta.equalsIgnoreCase("s")) {
            System.out.println("Datos guardados.");
            // Aquí puedes agregar la lógica para guardar los datos en una base de datos o archivo
        } else {
            System.out.println("¿Desea editar los datos? (s/n): ");
            String editar = scanner.nextLine();
            if (editar.equalsIgnoreCase("s")) {
                animal = pedirDatos(); // Vuelve a pedir los datos
                System.out.println("\nDatos del animal actualizados:");
                System.out.println(animal);
            } else {
                System.out.println("Datos no guardados.");
            }
        }
    }

    public static Animal pedirDatos() {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        System.out.println("Ingrese los datos del animal:");

        System.out.print("Raza: ");
        String raza = scanner.nextLine();

        System.out.print("Sexo: ");
        String sexo = scanner.nextLine();

        System.out.print("Lugar donde se encontró: ");
        String lugar = scanner.nextLine();

        LocalDate fecha = null;
        boolean fechaValida = false;
        while (!fechaValida) {
            System.out.print("Fecha de encuentro (DD-MM-AAAA): ");
            String fechaStr = scanner.nextLine();
            try {
                fecha = LocalDate.parse(fechaStr, formatter);
                fechaValida = true;
            } catch (Exception e) {
                System.out.println("Formato de fecha inválido. Intente nuevamente.");
            }
        }

        System.out.print("Estado del animal (descripción): ");
        String estado = scanner.nextLine();

        return new Animal(raza, sexo, lugar, fecha, estado);
    }
}

class Animal {
    String raza;
    String sexo;
    String lugar;
    LocalDate fecha;
    String estado;

    public Animal(String raza, String sexo, String lugar, LocalDate fecha, String estado) {
        this.raza = raza;
        this.sexo = sexo;
        this.lugar = lugar;
        this.fecha = fecha;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Raza: " + raza + "\n" +
                "Sexo: " + sexo + "\n" +
                "Lugar: " + lugar + "\n" +
                "Fecha: " + fecha.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "\n" +
                "Estado: " + estado;
    }
}