/**
 * Ressource partagee : salle, projecteur, box, ordinateur, etc.
 */
public class Ressource {

    public enum TypeRessource {
        BOX,
        PROJECTEUR,
        SALLE,
        ORDINATEUR
    }

    private int numero;
    private String nom;
    private TypeRessource type;
    private boolean disponible;
    private Reservation reservationActive;

    public Ressource(int numero, String nom, TypeRessource type) {
        this.numero = numero;
        this.nom = nom;
        this.type = type;
        this.disponible = true;
        this.reservationActive = null;
    }

    public int getNumero() {
        return numero;
    }

    public String getNom() {
        return nom;
    }

    public TypeRessource getType() {
        return type;
    }

    public boolean estDisponible() {
        return disponible;
    }

    public Reservation getReservationActive() {
        return reservationActive;
    }

    public boolean reserver(int numeroReservation, Utilisateur utilisateur, Creneau creneau) {
        if (!disponible) {
            System.out.println("Reservation refusee : " + nom + " est deja reservee.");
            return false;
        }
        reservationActive = new Reservation(numeroReservation, this, utilisateur, creneau);
        disponible = false;
        System.out.println("Reservation acceptee.");
        return true;
    }

    public void liberer() {
        reservationActive = null;
        disponible = true;
        System.out.println(nom + " est maintenant disponible.");
    }

    public void afficher() {
        if (disponible) {
            System.out.println(
                "Ressource n°" + numero + " | " + nom + " | Type : " + type + " | Disponible"
            );
        } else {
            System.out.println(
                "Ressource n°" + numero + " | " + nom + " | Type : " + type + " | Indisponible"
            );
        }
    }
}
