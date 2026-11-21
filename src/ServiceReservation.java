/**
 * Service de reservation : coordonne la creation d'une reservation sur un element reservable.
 * Centralise l'appel a Reservable.reserver et permet de verifier qu'en cas d'echec,
 * aucun changement d'etat n'a eu lieu (disponibilite inchangee, reservationActive reste null).
 */
public class ServiceReservation {

    private ServiceReservation() {
    }

    /**
     * Tente de reserver l'element cible pour l'utilisateur et le creneau donnes.
     *
     * @param cible            l'element reservable (salle, materiel mobile ou poste de travail)
     * @param numeroReservation le numero de la reservation
     * @param utilisateur      l'utilisateur qui effectue la reservation
     * @param creneau          le creneau souhaite
     * @return true si la reservation a ete acceptee, false sinon
     */
    public static boolean reserver(Reservable cible, int numeroReservation,
                                   Utilisateur utilisateur, Creneau creneau) {
        if (cible == null || utilisateur == null || creneau == null) {
            return false;
        }
        return cible.reserver(numeroReservation, utilisateur, creneau);
    }

    /**
     * Verifie qu'un echec de reservation n'a pas modifie l'etat de la ressource.
     * Cela est utile pour les tests d'echec : la ressource doit avoir le meme etat
     * de disponibilite et la meme reservation active avant et apres la tentative.
     *
     * @param cible             l'element reservable verifie
     * @param etaitDisponible   l'etat de disponibilite avant la tentative
     * @param reservationAvant  la reservation active avant la tentative (peut etre null)
     * @return true si l'etat est identique avant et apres
     */
    public static boolean etatInchangeApresEchec(Reservable cible, boolean etaitDisponible,
                                                 Reservation reservationAvant) {
        if (cible == null) {
            return false;
        }
        boolean disponible = cible.estDisponible();
        Reservation active = cible.getReservationActive();
        boolean memeDisponibilite = disponible == etaitDisponible;
        boolean memeReservation;
        if (reservationAvant == null && active == null) {
            memeReservation = true;
        } else if (reservationAvant == null || active == null) {
            memeReservation = false;
        } else {
            memeReservation = reservationAvant.getNumero() == active.getNumero();
        }
        return memeDisponibilite && memeReservation;
    }
}
