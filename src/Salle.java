public class Salle extends Ressource {

    private int capacite;

    public Salle(int numero, String nom, int capacite) {
        super(numero, nom);
        this.capacite = capacite;
    }

    @Override
    public int dureeMaxReservation() {
        return 4;
    }

    public String toString() {
        return "Salle" + super.toString() + " ;" + capacite;
    }

}
