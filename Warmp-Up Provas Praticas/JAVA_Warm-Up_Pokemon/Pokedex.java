import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Pokedex {
    public static void main(String args[]) {
        int allPokemons = 151;
        int count = 0;
        String pokedex[] = new String[allPokemons];

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader bfr = new BufferedReader(isr);

        try {
            int capturados = Integer.parseInt(bfr.readLine());
            String pokedexCapturado;
            boolean captured = false;

            for (int i = 0; i < capturados; i++) {
                pokedexCapturado = bfr.readLine();
                captured = false;
                for (int j = 0; j < allPokemons; j++) {
                    if (pokedexCapturado.equals(pokedex[j]))
                        captured = true;
                }
                if (captured == false) {
                    pokedex[count] = pokedexCapturado;
                    count++;
                }
            }

            System.out.println("Falta(m) " + (allPokemons - count) + " pomekon(s).");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
