import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Level {

    private int number;
    private double difficulty;
    private List<Character> charactersGroup;

    public  Level (int number, double difficulty) {
        this.number = number;
        this.difficulty = difficulty;
        this.charactersGroup = generateCharacters();
    }

    private List<Character> generateCharacters() {
        List<Character> l = new LinkedList<>();
        Character risky = new Character(true, 0, 0, 10);
        l.add(risky);

        int leftCharacters = generateCharactersByDifficulty(this.difficulty);
        for (int i = 0; i < leftCharacters; i++) {
            l.add(new Character(false, 0,0,10));
        }

        return l;
    }

    private int generateCharactersByDifficulty(double difficulty) {
        Random r = new Random();
        int n = (int) (number * difficulty);
        int characterNumber = r.nextInt(n)+1;
        //Faltaria refinar un poco la generacion de personajes en funcion de la dificultad
        return characterNumber;
    }
}
