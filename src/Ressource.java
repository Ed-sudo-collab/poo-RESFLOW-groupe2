/**
 * Ressource partagee
 */
public abstract class Ressource implements Reservable {
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

    @Override
    public boolean estDisponible() {
        return disponible;
    }

    @Override
    public Reservation getReservationActive() {
        return reservationActive;
    }

    @Override
    public boolean reserver(int numeroReservation, Utilisateur utilisateur, Creneau creneau) {
        int duree;

        duree = creneau.getDuree();
        if (duree > dureeMaxReservation()) {
            System.out.println(
                    "Reservation refusee : le creneau dure " + duree
                            + " heure(s), alors que " + nom + " accepte au maximum "
                            + dureeMaxReservation() + " heure(s).");
            return false;
        }

        if (!estDisponible()) {
            System.out.println("Reservation refusee : " + nom + " est deja reservee.");
            return false;
        }

        reservationActive = new Reservation(numeroReservation, this, utilisateur, creneau);
        disponible = false;
        System.out.println("Reservation acceptee.");
        return true;
    }

    @Override
    public void liberer() {
        reservationActive = null;
        disponible = true;
        System.out.println(nom + " est maintenant disponible.");
    }

    @Override
    public void afficher() {
        if (disponible) {
            System.out.println(
                    "Ressource n°" + numero + " | " + nom + " | Disponible");
        } else {
            System.out.println(
                    "Ressource n°" + numero + " | " + nom + " | Indisponible");
        }
    }

    @Override
    public abstract int dureeMaxReservation();

    public String toString() {
        return "Ressource" + numero + " ;" + nom + " ;" + disponible + ";" + reservationActive;
    }

}
