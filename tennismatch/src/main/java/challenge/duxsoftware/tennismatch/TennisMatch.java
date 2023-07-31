package challenge.duxsoftware.tennismatch;

public class TennisMatch {

    public static void main(String[] args) {
        System.out.println("Bienvenido a una simulación de un partido de tenis. A continuación se le pedirán una serie de datos para poder "
                + "dar comienzo a la simulación.\n");
        ConfigurationMatch configurationMatch = new ConfigurationMatch();
        configurationMatch.setConfiguration();
    }
}
