public class Salle extends Ressource {

    private int capacite;

    public Salle(String code, String nom, int capacite) {
        super(code, nom);
        if (capacite <= 0) {
            throw new IllegalArgumentException("La capacite d'une salle doit etre strictement positive.");
        }
        this.capacite = capacite;
    }

    public int getCapacite() {
        return capacite;
    }

    @Override
    public String typeRessource() {
        return "Salle";
    }

    @Override
    public int dureeMaxReservation() {
        return 4;
    }

    @Override
    public String toString() {
        return "Salle" + super.toString() + " ;capacite=" + capacite;
    }

}
