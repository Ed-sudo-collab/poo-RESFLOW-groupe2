import java.util.ArrayList;

/**
 * Liste des reservations reussies enregistrees pendant la demonstration.
 */
public class HistoriqueReservations {

    private ArrayList<Reservation> reservations;

    public HistoriqueReservations() {
        reservations = new ArrayList<Reservation>();
    }

    public void enregistrer(Reservation reservation) {
        if (reservation != null) {
            reservations.add(reservation);
        }
    }

    public void afficher() {
        int i;

        System.out.println("--- Historique des reservations ---");
        for (i = 0; i < reservations.size(); i = i + 1) {
            reservations.get(i).afficher();
        }
    }

    public int nombre() {
        return reservations.size();
    }
}
