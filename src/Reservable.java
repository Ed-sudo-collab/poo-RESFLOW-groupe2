public interface Reservable {
    boolean estDisponible();
    boolean reserver(int numeroReservation, Utilisateur utilisateur, Creneau creneau);
    void liberer();
    int dureeMaxReservation();
}
