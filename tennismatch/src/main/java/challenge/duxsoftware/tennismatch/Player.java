package challenge.duxsoftware.tennismatch;

import java.util.ArrayList;

// Clase para generar los jugadores con sus respectivos atributos
public class Player {

    private int points;
    private int games;
    private int sets;
    private String name;
    private int OddsWinning;
    private ArrayList<String> resultsSets = new ArrayList<>();

    public Player() {

    }

    public Player(int points, int games, int sets, String name, int OddsWinning) {
        this.points = points;
        this.games = games;
        this.sets = sets;
        this.name = name;
        this.OddsWinning = OddsWinning;
    }

    public ArrayList<String> getResultsSets() {
        return resultsSets;
    }

    public void setResultsSets(ArrayList<String> resultsSets) {
        this.resultsSets = resultsSets;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOddsWinning(int OddsWinning) {
        this.OddsWinning = OddsWinning;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public String getName() {
        return name;
    }

    public int getOddsWinning() {
        return OddsWinning;
    }

}
