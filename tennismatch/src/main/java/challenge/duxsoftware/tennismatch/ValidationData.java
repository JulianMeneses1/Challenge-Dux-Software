package challenge.duxsoftware.tennismatch;

import java.util.Scanner;

// Clase para validar los datos ingresados por el usuario en el método setConfiguration()
public class ValidationData {

    // Validación nombre del torneo (que no esté vacío)
    public static String validateNameTournament() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Ingrese el nombre del torneo: ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Debe ingresar un nombre");
            } else {
                return input;
            }
        }
    }

    // Validación nombre del jugador (que no esté vacío)
    public static void validateNamePlayer(Player player, String enteredPlayer) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Ingrese el nombre del " + enteredPlayer + " jugador: ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Debe ingresar un nombre");
            } else {
                player.setName(input);
                return;
            }
        }
    }

    // Validación probabilidades del jugador (que sea un número y tenga como valor entre 1 y 100)
    public static void validateProbability(Player playerOne, Player playerTwo) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Ingrese la probabilidad de ganar el partido para " + playerOne.getName() + " (entre 1 y 100). La probabilidad del otro jugador " 
                    + "se calculará automáticamente: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Error: Sólo se aceptan números");
                scanner.next();
            } else {
                int probability = scanner.nextInt();
                if (probability > 100 || probability < 1) {
                    System.out.println("Error: El número ingresado es inválido, debe ser entre 1 y 100");
                } else {
                    playerOne.setOddsWinning(probability);
                    playerTwo.setOddsWinning(100 - probability);
                    return;
                }
            }
        }
    }

    // Validación al mejor de cuántos sets se juega (que sea un número y sea igual a 3 o 5)
    public static int validateBestSets() {
        Scanner scanner = new Scanner(System.in);
        int bestSets;
        while (true) {
            System.out.print("¿Al mejor de cuántos sets se va a jugar? Las opciones disponibles son 3 o 5: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Error: Sólo se aceptan números");
                scanner.next();
            } else {
                bestSets = scanner.nextInt();
                if (bestSets != 5 && bestSets != 3) {
                    System.out.println("Error: El número ingresado es inválido, debe ser 3 o 5");
                } else {
                    return bestSets;
                }
            }
        }
    }
}
