/**
 * Reservation simplifiee pour un cours, a partir d'un libelle de creneau.
 */
public final class ReservationCours {

    private static final Utilisateur UTILISATEUR_COURS =
            new Utilisateur(0, "Reservation cours", "cours@2ie.edu");

    private ReservationCours() {
    }

    public static boolean reserverPourCours(Reservable ressource, String creneau) {
        if (creneau == null || creneau.trim().isEmpty()) {
            return false;
        }
        if (!ressource.estDisponible()) {
            return false;
        }

        return ressource.reserver(
                0,
                UTILISATEUR_COURS,
                new Creneau(0, creneau.trim(), 8, 10));
    }
}
