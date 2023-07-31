
package challenge.duxsoftware.tennismatch;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayMatchTest {
    
    @Test
    public void testStartMatch() {
        
        Player playerOne = new Player(0, 0, 0, "Carlos", 80);
        Player playerTwo = new Player(0, 0, 0, "Alberto", 20);
        String nameTournament = "Final Wimbledon";
        int bestSets = 5;
        Map<Integer, Object> pointsDictionary = new HashMap<>();
        
        pointsDictionary.put(0, 0);
        pointsDictionary.put(1, 15);
        pointsDictionary.put(2, 30);
        pointsDictionary.put(3, 40);
        pointsDictionary.put(4, "Ventaja");
                
        PlayMatch playMatch = new PlayMatch(playerOne, playerTwo, pointsDictionary, bestSets, nameTournament);
        
        playMatch.resetSets();
        String input = "0"; 
                
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Ejecutar el método startMatch()
        playMatch.startMatch();

        // Verificar que los valores estén configurados correctamente
        assertEquals("Con 80% de probabiliades de ganar, se espera que el jugador uno la mayoría de las veces gane 3-0 en sets", 3, playerOne.getSets());
        assertEquals("Con 20% de probabiliades de ganar, se espera que el jugador dos la mayoría de las veces pierda 0-3 en sets", 0, playerTwo.getSets());
    }
    
    @Test
    public void testCheckGame() {
        
        Player playerOne = new Player(3, 0, 0, "Carlos", 90);
        Player playerTwo = new Player(2, 0, 0, "Alberto", 10);
        String nameTournament = "Final Wimbledon";
        int bestSets = 5;
        Map<Integer, Object> pointsDictionary = new HashMap<>();
        
        pointsDictionary.put(0, 0);
        pointsDictionary.put(1, 15);
        pointsDictionary.put(2, 30);
        pointsDictionary.put(3, 40);
        pointsDictionary.put(4, "Ventaja");
                
        PlayMatch playMatch = new PlayMatch(playerOne, playerTwo, pointsDictionary, bestSets, nameTournament);
        
        String input = "0"; 
                
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Ejecutar el método checkGame()
        boolean result = playMatch.checkGame(playerOne, playerTwo);
        int expectedGamesPlayerOne = 1;

        // Verificar que los valores estén configurados correctamente
        assertTrue("Se espera que el jugador uno haya ganado el game", result);
        assertEquals("Se espera que el jugador uno haya sumado un game",
                expectedGamesPlayerOne, playerOne.getGames());
    }
    
    @Test
    public void testCheckSet() {
        
        Player playerOne = new Player(2, 3, 0, "Carlos", 10);
        Player playerTwo = new Player(3, 4, 0, "Alberto", 70);
        String nameTournament = "Final Wimbledon";
        int bestSets = 5;
        Map<Integer, Object> pointsDictionary = new HashMap<>();
        
        pointsDictionary.put(0, 0);
        pointsDictionary.put(1, 15);
        pointsDictionary.put(2, 30);
        pointsDictionary.put(3, 40);
        pointsDictionary.put(4, "Ventaja");
                
        PlayMatch playMatch = new PlayMatch(playerOne, playerTwo, pointsDictionary, bestSets, nameTournament);
        
        String input = "0"; 
                
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Ejecutar el método checkSet()
        boolean result = playMatch.checkSet(playerTwo, playerOne);

        // Verificar que el resultado sea el esperado
        assertFalse("Se espera que el jugador dos no haya ganado el set", result);
    }
    
    @Test
    public void testCheckMatch() {
        
        Player playerOne = new Player(0, 0, 2, "Carlos", 85);
        Player playerTwo = new Player(0, 0, 0, "Alberto", 15);
        String nameTournament = "Final Wimbledon";
        int bestSets = 3;
        Map<Integer, Object> pointsDictionary = new HashMap<>();
        
        pointsDictionary.put(0, 0);
        pointsDictionary.put(1, 15);
        pointsDictionary.put(2, 30);
        pointsDictionary.put(3, 40);
        pointsDictionary.put(4, "Ventaja");
                
        PlayMatch playMatch = new PlayMatch(playerOne, playerTwo, pointsDictionary, bestSets, nameTournament);
        
        String input = "0"; 
                
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Ejecutar el método checkSet()
        boolean result = playMatch.checkMatch(playerOne, playerTwo);

        // Verificar que el resultado sea el esperado
        assertTrue("Se espera que el jugador uno al haber ganado el set e ir 2-0 gane el partido", result);
    }
    @Test
    public void testCheckTiebreak() {
        
        Player playerOne = new Player(6, 6, 1, "Carlos", 85);
        Player playerTwo = new Player(5, 6, 0, "Alberto", 15);
        String nameTournament = "Final Wimbledon";
        int bestSets = 3;
        Map<Integer, Object> pointsDictionary = new HashMap<>();      
                
        PlayMatch playMatch = new PlayMatch(playerOne, playerTwo, pointsDictionary, bestSets, nameTournament);
        
        String input = "0"; 
                
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Ejecutar el método checkGame()
        playMatch.tiebreakOn = true;
        boolean result = playMatch.checkGame(playerOne, playerTwo);

        // Verificar que el resultado sea el esperado
        assertTrue("Se espera que el jugador uno al haber ganado el punto, gane también el game y por lo tanto el set", result);
    }
}
