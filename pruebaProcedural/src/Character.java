public class Character {

    private boolean isRisky;
    private double xCoord;
    private double yCoord;
    private double margin;

    public Character(boolean isRisky, double xCoord, double yCoord, double margin) {
        this.isRisky = isRisky;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.margin = margin;
        //Faltaria refinar un poco la generacion de personajes de manera que no puedan solaparse coordenadas y se mantenga el margen
    }
}
