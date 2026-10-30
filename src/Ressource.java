/**
 * Ressource partagee
 */
public abstract class Ressource implements Reservable {
    private String code;
    private String nom;
    private boolean disponible;
    private Reservation reservationActive;

    public Ressource(String code, String nom) {
        if (code == null || code.trim().isEmpty()) {
            throw new IllegalArgumentException("Le code de la ressource ne peut pas etre vide.");
        }
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom de la ressource ne peut pas etre vide.");
        }
        this.code = code.trim();
        this.nom = nom.trim();
        this.disponible = true;
        this.reservationActive = null;
    }

    public String getCode() {
        return code;
    }

    public String getNom() {
        return nom;
    }

    public abstract String typeRessource();

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
                    "Ressource " + code + " | " + nom + " | " + typeRessource() + " | Disponible");
        } else {
            System.out.println(
                    "Ressource " + code + " | " + nom + " | " + typeRessource() + " | Indisponible");
        }
    }

    @Override
    public abstract int dureeMaxReservation();

    @Override
    public String toString() {
        return typeRessource() + " " + code + " ;" + nom + " ;" + disponible + ";" + reservationActive;
    }

}
