import java.util.ArrayList;
import java.util.List;

/**
 * Ressource partagee
 */
public abstract class Ressource {
    // abstract afin qu'un objet de type Ressource ne puisse pas etre cree directement
    private int numero;
    private String nom;
    private List<Reservation> reservations;

    public Ressource(int numero, String nom) {
        this.numero = numero;
        this.nom = nom;
        this.reservations = new ArrayList<>();
    }

    public int getNumero() {
        return numero;
    }

    public String getNom() {
        return nom;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    // Disponibilite centralisee : un seul endroit calcule si la ressource
    // est libre sur un creneau donne, en verifiant les chevauchements
    // avec les reservations deja enregistrees.
    public boolean estDisponible(Creneau creneau) {
        for (Reservation reservation : reservations) {
            if (reservation.getCreneau().seChevaucheAvec(creneau)) {
                return false;
            }
        }
        return true;
    }

    public boolean reserver(int numeroReservation, Utilisateur utilisateur, Creneau creneau) {

        // 1. Refus si la duree depasse le maximum de CETTE ressource
        int duree = creneau.getDuree();
        if (duree > dureeMaxReservation()) {
            System.out.println(
                    "Reservation refusee : le creneau dure " + duree
                            + " heure(s), alors que " + nom + " accepte au maximum "
                            + dureeMaxReservation() + " heure(s).");
            return false;
        }

        // 2. Refus si la ressource est deja occupee sur ce creneau precis
        if (!estDisponible(creneau)) {
            System.out.println(
                    "Reservation refusee : " + nom + " est deja occupee sur le creneau "
                            + creneau.getLibelle() + ".");
            return false;
        }

        // 3. Sinon, reservation acceptee
        Reservation reservation = new Reservation(numeroReservation, this, utilisateur, creneau);
        reservations.add(reservation);
        System.out.println("Reservation acceptee pour " + nom + " sur " + creneau.getLibelle() + ".");
        return true;
    }

    public void liberer(Reservation reservation) {
        if (reservation != null && reservations.remove(reservation)) {
            System.out.println(nom + " : la reservation n°" + reservation.getNumero() + " a ete liberee.");
        } else {
            System.out.println(nom + " : impossible de liberer, reservation introuvable.");
        }
    }

    public void afficher() {
        System.out.println(
                "Ressource n°" + numero + " | " + nom + " | "
                        + reservations.size() + " reservation(s) active(s)");
    }

    public abstract int dureeMaxReservation();

    public String toString() {
        return "Ressource n°" + numero + " | " + nom + " | " + reservations.size() + " reservation(s)";
    }

}