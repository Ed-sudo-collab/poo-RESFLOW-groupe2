/**
 * Poste de travail reservable dans une zone d'etude.
 * Implemente Reservable sans heriter de Ressource : ce n'est ni une salle
 * ni un materiel mobile, mais on peut quand meme le reserver.
 */
public class PosteTravail implements Reservable {

    private String code;
    private String zone;
    private boolean disponible;
    private Reservation reservationActive;

    public PosteTravail(String code, String zone) {
        if (code == null || code.trim().isEmpty()) {
            throw new IllegalArgumentException("Le code du poste ne peut pas etre vide.");
        }
        this.code = code.trim();
        this.zone = zone == null ? "" : zone.trim();
        this.disponible = true;
        this.reservationActive = null;
    }

    public String getCode() {
        return code;
    }

    public String getZone() {
        return zone;
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
    public int dureeMaxReservation() {
        return 3;
    }

    @Override
    public boolean reserver(int numeroReservation, Utilisateur utilisateur, Creneau creneau) {
        int duree = creneau.getDuree();
        if (duree > dureeMaxReservation()) {
            System.out.println(
                    "Reservation refusee : le poste " + code + " accepte au maximum "
                            + dureeMaxReservation() + " heure(s).");
            return false;
        }
        if (!disponible) {
            System.out.println("Reservation refusee : le poste " + code + " est deja reserve.");
            return false;
        }
        reservationActive = new Reservation(numeroReservation, this, utilisateur, creneau);
        disponible = false;
        System.out.println("Reservation acceptee pour le poste " + code + ".");
        return true;
    }

    @Override
    public void liberer() {
        reservationActive = null;
        disponible = true;
        System.out.println("Poste " + code + " est maintenant disponible.");
    }

    @Override
    public void afficher() {
        String etat = disponible ? "Disponible" : "Indisponible";
        System.out.println("Poste " + code + " | Zone " + zone + " | " + etat);
    }
}
