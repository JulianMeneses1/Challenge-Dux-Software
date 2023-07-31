package challenge.duxsoftware.tennismatch;

import static challenge.duxsoftware.tennismatch.ValidationData.validateBestSets;
import static challenge.duxsoftware.tennismatch.ValidationData.validateNamePlayer;
import static challenge.duxsoftware.tennismatch.ValidationData.validateNameTournament;
import static challenge.duxsoftware.tennismatch.ValidationData.validateProbability;
import java.util.HashMap;
import java.util.Map;

// Clase para setear los valores de los jugadores (nombre y probabilidades) y del partido (nombre y al mejor de cuántos sets se juega)
public class ConfigurationMatch {

    String nameTournament;
    int bestSets;
    Map<Integer, Object> pointsDictionary = new HashMap<>();
    Player playerOne = new Player();
    Player playerTwo = new Player();

    public void setConfiguration() {
        // Configuramos un diccionario para convertir los puntos de los jugadores a sus equivalentes en tenis
        pointsDictionary.put(0, 0);
        pointsDictionary.put(1, 15);
        pointsDictionary.put(2, 30);
        pointsDictionary.put(3, 40);
        pointsDictionary.put(4, "Ventaja");
        nameTournament = validateNameTournament();
        validateNamePlayer(playerOne, "primer");
        validateNamePlayer(playerTwo, "segundo");
        validateProbability(playerOne, playerTwo);
        bestSets = validateBestSets();
        PlayMatch playMatch = new PlayMatch(playerOne, playerTwo, pointsDictionary, bestSets, nameTournament);
        playMatch.startMatch();
    }
}
