/**
 * Ressource partagee
 */
public abstract class Ressource {
    // abstract afin qu'un objet de typ Ressource ne puise être créer
    private int numero;
    private String nom;
    private boolean disponible;
    private Reservation reservationActive;

    public Ressource(int numero, String nom) {
        this.numero = numero;
        this.nom = nom;
        this.disponible = true;
        this.reservationActive = null;
    }

    public int getNumero() {
        return numero;
    }

    public String getNom() {
        return nom;
    }

    public boolean estDisponible() {
        return disponible;
    }

    public Reservation getReservationActive() {
        return reservationActive;
    }

    public boolean reserver(int numeroReservation, Utilisateur utilisateur, Creneau creneau) {
       //à compléter
    }

    public void liberer() {
        reservationActive = null;
        disponible = true;
        System.out.println(nom + " est maintenant disponible.");
    }

    public void afficher() {
        if (disponible) {
            System.out.println(
                    "Ressource n°" + numero + " | " + nom + " | Disponible");
        } else {
            System.out.println(
                    "Ressource n°" + numero + " | " + nom + " | Indisponible");
        }
    }

    public abstract int dureeMaxReservation();

    public String toString() {
        return "Ressource" + numero + " ;" + nom + " ;" + disponible + ";" + reservationActive;
    }

}
