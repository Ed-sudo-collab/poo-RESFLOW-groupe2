import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests d'occupation : refus, etat inchange, liberation, materiel et poste.
 */
public class ReservationEtatTest {

    private Utilisateur aminata() {
        return new Utilisateur(1, "Aminata Traore", "aminata@2ie.edu");
    }

    private Utilisateur kofi() {
        return new Utilisateur(2, "Kofi Ouedraogo", "kofi@2ie.edu");
    }

    @Test
    public void secondeReservationRefusee() {
        Reservable salle = new Salle("SAL-OCC", "Salle occupee", 30);
        boolean premiere = salle.reserver(1, aminata(), new Creneau(1, "Lundi", 8, 10));
        boolean seconde = salle.reserver(2, kofi(), new Creneau(2, "Lundi", 10, 12));

        assertTrue(premiere);
        assertFalse(seconde);
        assertFalse(salle.estDisponible());
        assertEquals(1, salle.getReservationActive().getNumero());
    }

    @Test
    public void etatInchangeApresRefusDuree() {
        Reservable salle = new Salle("SAL-DUR", "Salle duree", 30);
        boolean disponibleAvant = salle.estDisponible();
        Reservation reservationAvant = salle.getReservationActive();

        boolean acceptee = salle.reserver(1, aminata(), new Creneau(1, "Lundi", 8, 18));

        assertFalse(acceptee);
        assertEquals(disponibleAvant, salle.estDisponible());
        assertNull(reservationAvant);
        assertNull(salle.getReservationActive());
    }

    @Test
    public void etatInchangeApresRefusOccupation() {
        Reservable salle = new Salle("SAL-CONF", "Salle conflit", 30);
        salle.reserver(7, aminata(), new Creneau(7, "Mardi", 8, 10));
        boolean disponibleAvant = salle.estDisponible();
        int numeroAvant = salle.getReservationActive().getNumero();

        boolean seconde = salle.reserver(8, kofi(), new Creneau(8, "Mardi", 10, 12));

        assertFalse(seconde);
        assertEquals(disponibleAvant, salle.estDisponible());
        assertEquals(numeroAvant, salle.getReservationActive().getNumero());
    }

    @Test
    public void reservationAccepteeApresLiberation() {
        Reservable salle = new Salle("SAL-LIB", "Salle libre", 30);
        salle.reserver(1, aminata(), new Creneau(1, "Lundi", 8, 10));
        salle.liberer();

        boolean apres = salle.reserver(2, kofi(), new Creneau(2, "Lundi", 10, 12));

        assertFalse(salle.estDisponible());
        assertTrue(apres);
        assertEquals(2, salle.getReservationActive().getNumero());
    }

    @Test
    public void materielMobileCinqHeuresAccepte() {
        Reservable projecteur = new MaterielMobile("MAT-T5", "Projecteur test", "SN-1", "Audiovisuel");

        boolean acceptee = projecteur.reserver(4, kofi(), new Creneau(4, "Mardi", 8, 13));

        assertTrue(acceptee);
        assertFalse(projecteur.estDisponible());
        assertEquals(5, projecteur.getReservationActive().getCreneau().getDuree());
    }

    @Test
    public void posteDeuxHeuresAccepte() {
        Reservable poste = new PosteTravail("POSTE-T2", "Zone test");

        boolean acceptee = poste.reserver(11, aminata(), new Creneau(11, "Vendredi", 9, 11));

        assertTrue(acceptee);
        assertFalse(poste.estDisponible());
        assertEquals(2, poste.getReservationActive().getCreneau().getDuree());
    }
}
