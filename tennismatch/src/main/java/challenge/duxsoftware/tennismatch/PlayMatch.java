package challenge.duxsoftware.tennismatch;

import java.util.Map;
import java.util.Random;
import java.util.Scanner;

// Clase principal en donde se encuentra toda la lógica para simular un partido de tenis y al final del mismo la posibilidad de "jugar" de vuelta
public class PlayMatch {

    Player playerOne;
    Player playerTwo;
    Map pointsDictionary;
    int bestSets;
    String nameTournament;
    String pointsWinnerTiebreak;
    String pointsLoserTiebreak;
    boolean matchInProgress = true;
    static boolean tiebreakOn = false;
    boolean tiebreakResolved = false;
    boolean serve;
    boolean firstServe;

    public PlayMatch(Player playerOne, Player playerTwo, Map pointsDictionary, int bestSets, String nameTournament) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.pointsDictionary = pointsDictionary;
        this.bestSets = bestSets;
        this.nameTournament = nameTournament;
    }

    // Iniciamos el partido con un bucle que se interrumpe solamente al finalizar el juego (excepto si al final del juego 
    // el usuario elige la opción 1, en cuyo caso el ciclo continúa.
    public void startMatch() {
        Random random = new Random();
        // Determinamos aleatoriamente quién saca primero y lo guardamos en dos variables, una para determinar 
        // quién saca en cada game, y otra para establecer quién saca en cada set
        serve = firstServe = random.nextBoolean();
        System.out.println("\nComienzo del partido. Saca " + (serve ? playerOne.getName() : playerTwo.getName()));
        while (matchInProgress) {
            playPoint();
        }
    }

    // Método para calcular qué jugador gana el punto de forma aleatoria según sus probabilidades 
    public void playPoint() {
        while (true) {
            Random random = new Random();
            int randomValue = random.nextInt(100) + 1;
            // Ganador Jugador Uno
            if (randomValue <= playerOne.getOddsWinning()) {
                System.out.println(playerOne.getName() + " gana el punto");
                // Checkeamos si el jugador al ganar el punto ganó el game
                if (checkGame(playerOne, playerTwo)) {
                    break;
                }
                if (tiebreakOn) {
                    printPartialGameResultTiebreakOn();
                } else {
                    printPartialGameResult();
                }
            // Ganador Jugador Dos (misma lógica que lo anterior)
            } else {
                System.out.println(playerTwo.getName() + " gana el punto");
                if (checkGame(playerTwo, playerOne)) {
                    break;
                }
                if (tiebreakOn) {
                    printPartialGameResultTiebreakOn();
                } else {
                    printPartialGameResult();
                }
            }
        }
    }

    // Método para checkear si se terminó el game
    public boolean checkGame(Player pointWinner, Player pointLoser) {
        // Si el tiebreak está activo, sumamos el punto al jugador correspondiente y verificamos si ganó el game
        if (tiebreakOn) {
            pointWinner.setPoints(pointWinner.getPoints() + 1);
            if (pointWinner.getPoints() >= 7 && pointWinner.getPoints() - pointLoser.getPoints() == 2) {
                System.out.println(pointWinner.getName() + " gana el game.");
                serve = !serve;
                pointsWinnerTiebreak = Integer.toString(pointWinner.getPoints());
                pointsLoserTiebreak = Integer.toString(pointLoser.getPoints());
                pointWinner.setPoints(0);
                pointLoser.setPoints(0);
                pointWinner.setGames(pointWinner.getGames() + 1);
                printPartialSetResult();
                System.out.println("Comienzo del game. Saca " + (serve ? playerOne.getName() : playerTwo.getName()));
                tiebreakResolved = true;
                checkSet(pointWinner, pointLoser);
                return true;
            }
        // Tiebreak inactivo
        } else {
            // Si el que perdió el punto tenía ventaja, entonces le restamos un punto para que vuelva a 40
            if (pointWinner.getPoints() == 3 && pointLoser.getPoints() == 4) {
                pointLoser.setPoints(pointLoser.getPoints() - 1);
            // Si no se da la situación de que uno esté en ventaja, se suma un punto al ganador
            } else {
                pointWinner.setPoints(pointWinner.getPoints() + 1);
            }
            // Verificamos si ganó el game
            if (pointWinner.getPoints() >= 4 && pointWinner.getPoints() - pointLoser.getPoints() >= 2) {
                System.out.println(pointWinner.getName() + " gana el game.");
                serve = !serve;
                pointWinner.setPoints(0);
                pointLoser.setPoints(0);
                pointWinner.setGames(pointWinner.getGames() + 1);
                // verificamos si con el game ganado también ganó el set
                if (!checkSet(pointWinner, pointLoser)) {
                    printPartialSetResult();
                    System.out.println("Comienzo del game. Saca " + (serve ? playerOne.getName() : playerTwo.getName()));
                }
                return true;
            }
            return false;
        }
        return false;
    }

    // Método para verificar si se terminó el set
    public boolean checkSet(Player gameWinner, Player gameLoser) {
        // Si los jugadores van 6-6 en games, entonces iniciamos el tiebreak
        if (gameWinner.getGames() == 6 && gameLoser.getGames() == 6) {
            tiebreakOn = true;
        // Si no, verificamos si el jugador ganador del game ganó el set
        } else if ((gameWinner.getGames() >= 6 && gameWinner.getGames() - gameLoser.getGames() >= 2) || tiebreakResolved) {
            gameWinner.setSets(gameWinner.getSets() + 1);
            System.out.println(gameWinner.getName() + " gana el set.");
            firstServe = !firstServe;
            serve = firstServe;
            // Si el set se ganó con tiebreak, los puntos se registran del tiebreak se guardan en el resultado
            if (tiebreakResolved) {
                gameWinner.getResultsSets().add(Integer.toString(gameWinner.getGames()) + "(" + pointsWinnerTiebreak + ")");
                gameLoser.getResultsSets().add(Integer.toString(gameLoser.getGames()) + "(" + pointsLoserTiebreak + ")");
            // Si es un set normal, se guardan solamente los games en el resultado
            } else {
                gameWinner.getResultsSets().add(Integer.toString(gameWinner.getGames()));
                gameLoser.getResultsSets().add(Integer.toString(gameLoser.getGames()));
            }
            gameWinner.setGames(0);
            gameLoser.setGames(0);
            tiebreakOn = false;
            tiebreakResolved = false;
            // Verificamos si se terminó el partido
            if (!checkMatch(gameWinner, gameLoser)) {
                System.out.println("Resultado parcial partido:\n"
                        + String.format("%-" + (playerOne.getName().length() + 2) + "s", playerOne.getName())
                        + String.format("%-" + playerOne.getName().length() + "s\n", playerTwo.getName())
                        + String.format("%-" + (playerOne.getName().length() + 2) + "s", playerOne.getSets())
                        + String.format("%-" + playerOne.getName().length() + "s\n", playerTwo.getSets()));
                System.out.println("Comienzo del set. Saca " + (serve ? playerOne.getName() : playerTwo.getName()));
            }
            return true;
        }
        return false;
    }

    // Método para verificar si se terminó el partido
    public boolean checkMatch(Player setWinner, Player setLoser) {
        // Si es al mejor de 3 sets, se termina al llegar a 2 sets
        if (bestSets == 3) {
            if (setWinner.getSets() == 2) {
                printFinalResult(setWinner);
                optionPlayAgain();
                return true;
            }
            // Si es al mejor de 5 sets, se termina al llegar a 3 sets
        } else {
            if (setWinner.getSets() == 3) {
                printFinalResult(setWinner);
                optionPlayAgain();
                return true;
            }
        }
        return false;
    }

    // Método para permitir al usuario jugar nuevamente con la misma configuración u otra, o terminar el programa
    public void optionPlayAgain() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Si desea volver a jugar con la misma configuración, ingrese 1.\n"
                + "Si en cambio quiere volver a configurar el juego, ingrese 2.\n"
                + "Si desea finalizar el programa, ingrese 0 ");
        String chosenOption = scanner.next();
        switch (chosenOption) {
            case "0": {
                matchInProgress = false;
                break;
            }
            case "1": {
                resetSets();
                break;
            }
            case "2": {
                matchInProgress = false;
                resetSets();
                scanner.nextLine();
                ConfigurationMatch configurationMatch = new ConfigurationMatch();
                configurationMatch.setConfiguration();
                break;
            }
            default: {
                System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                optionPlayAgain();
            }
        }
    }

    // Método para resetear los sets de los jugadores y los resultados de cada uno, se usa si se juega nuevamente
    public void resetSets() {
        playerOne.getResultsSets().clear();
        playerOne.setSets(0);
        playerTwo.getResultsSets().clear();
        playerTwo.setSets(0);
    }

    public void printPartialGameResult() {
        System.out.println("Resultado parcial game:\n"
                + String.format("%-" + (playerOne.getName().length() + 2) + "s", playerOne.getName())
                + String.format("%-" + playerOne.getName().length() + "s\n", playerTwo.getName())
                + String.format("%-" + (playerOne.getName().length() + 2) + "s", pointsDictionary.get(playerOne.getPoints()))
                + String.format("%-" + playerOne.getName().length() + "s\n", pointsDictionary.get(playerTwo.getPoints())));
    }

    public void printPartialGameResultTiebreakOn() {
        System.out.println("Resultado parcial game:\n"
                + String.format("%-" + (playerOne.getName().length() + 2) + "s", playerOne.getName())
                + String.format("%-" + playerOne.getName().length() + "s\n", playerTwo.getName())
                + String.format("%-" + (playerOne.getName().length() + 2) + "s", playerOne.getPoints())
                + String.format("%-" + playerOne.getName().length() + "s\n", playerTwo.getPoints()));
    }

    public void printPartialSetResult() {
        System.out.println("Resultado parcial set:\n"
                + String.format("%-" + (playerOne.getName().length() + 2) + "s", playerOne.getName())
                + String.format("%-" + playerOne.getName().length() + "s\n", playerTwo.getName())
                + String.format("%-" + (playerOne.getName().length() + 2) + "s", playerOne.getGames())
                + String.format("%-" + playerOne.getName().length() + "s\n", playerTwo.getGames()));
    }

    public void printFinalResult(Player setWinner) {
        System.out.println("\nGanador del partido: " + setWinner.getName());
        System.out.println(nameTournament + " Finalizado\n"
                + String.format("%-" + (playerOne.getName().length() + 2) + "s", playerOne.getName())
                + String.format("%-" + (playerOne.getName().length() + 2) + "s\n", playerOne.getResultsSets())
                + String.format("%-" + (playerOne.getName().length() + 2) + "s", playerTwo.getName())
                + String.format("%-" + (playerOne.getName().length() + 2) + "s\n", playerTwo.getResultsSets()));
    }
}
