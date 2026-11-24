import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Quatre tests JUnit pour verifier que la suite demarre
 * et que les regles de base sont respectees.
 */
public class ReservationReglesTest {

    @Test
    public void creneauInvalideEstRefuse() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Creneau(1, "Lundi", 14, 14);
        });
    }

    @Test
    public void codeVideEstRefuse() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Salle("  ", "Salle test", 20);
        });
    }

    @Test
    public void reservationDeuxHeuresAcceptee() {
        Reservable salle = new Salle("SAL-T2", "Salle test", 20);
        Utilisateur utilisateur = new Utilisateur(1, "Aminata Traore", "aminata@2ie.edu");

        boolean acceptee = salle.reserver(1, utilisateur, new Creneau(1, "Lundi", 8, 10));

        assertTrue(acceptee);
        assertFalse(salle.estDisponible());
    }

    @Test
    public void reservationDixHeuresRefusee() {
        Reservable salle = new Salle("SAL-T10", "Salle test", 20);
        Utilisateur utilisateur = new Utilisateur(1, "Aminata Traore", "aminata@2ie.edu");

        boolean acceptee = salle.reserver(1, utilisateur, new Creneau(1, "Lundi", 8, 18));

        assertFalse(acceptee);
        assertTrue(salle.estDisponible());
        assertNull(salle.getReservationActive());
    }
}
