/**
 * Reservation simplifiee pour un cours, a partir d'un jour et d'un horaire precise.
 * L'identifiant du creneau est 0 (reservation cours) et l'utilisateur est l'utilisateur
 * générique "Reservation cours".
 */
public final class ReservationCours {

    private static final Utilisateur UTILISATEUR_COURS =
            new Utilisateur(0, "Reservation cours", "cours@2ie.edu");
    private static final int NUMERO_CRENEAU_COURS = 0;

    private ReservationCours() {
    }

    /**
     * Reserve un cours sur la ressource donnee pour le jour et l'horaire specifies.
     *
     * @param ressource    l'element reservable (salle, materiel mobile ou poste de travail)
     * @param jour         le jour du cours (non vide)
     * @param heureDebut   l'heure de debut (doit etre strictement inferieure a heureFin)
     * @param heureFin     l'heure de fin
     * @return true si la reservation a ete acceptee, false sinon
     */
    public static boolean reserverPourCours(Reservable ressource, String jour,
                                            int heureDebut, int heureFin) {
        if (ressource == null) {
            return false;
        }
        if (jour == null || jour.trim().isEmpty()) {
            return false;
        }
        if (!ressource.estDisponible()) {
            return false;
        }

        Creneau creneau = new Creneau(NUMERO_CRENEAU_COURS, jour.trim(), heureDebut, heureFin);
        return ressource.reserver(0, UTILISATEUR_COURS, creneau);
    }
}
