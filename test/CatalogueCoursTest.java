import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests du catalogue, de la recherche par code et de la reservation de cours.
 */
public class CatalogueCoursTest {

    @Test
    public void doublonRefuseEtTailleInchangee() {
        CatalogueReservable catalogue = new CatalogueReservable();
        Salle salle = new Salle("SAL-B12", "Salle B12", 40);

        assertTrue(catalogue.ajouter(salle));
        assertFalse(catalogue.ajouter(new Salle("SAL-B12", "Salle bis", 10)));
        assertEquals(1, catalogue.taille());
    }

    @Test
    public void rechercheCodeConnuTrouve() {
        CatalogueReservable catalogue = new CatalogueReservable();
        Salle salle = new Salle("SAL-B12", "Salle B12", 40);
        catalogue.ajouter(salle);

        assertSame(salle, catalogue.chercherParCode("SAL-B12"));
    }

    @Test
    public void rechercheCodeInconnuAbsente() {
        CatalogueReservable catalogue = new CatalogueReservable();
        catalogue.ajouter(new Salle("SAL-B12", "Salle B12", 40));

        assertNull(catalogue.chercherParCode("INCONNU"));
    }

    @Test
    public void rechercheCodeVideAbsente() {
        CatalogueReservable catalogue = new CatalogueReservable();
        catalogue.ajouter(new Salle("SAL-B12", "Salle B12", 40));

        assertNull(catalogue.chercherParCode(""));
        assertNull(catalogue.chercherParCode("   "));
    }

    @Test
    public void ajoutNullRefuse() {
        CatalogueReservable catalogue = new CatalogueReservable();

        assertFalse(catalogue.ajouter(null));
        assertEquals(0, catalogue.taille());
    }

    @Test
    public void coursSansJourRefuseSalleLibre() {
        Reservable salle = new Salle("SAL-C01", "Salle C01", 50);

        boolean acceptee = ReservationCours.reserverPourCours(salle, "", 14, 16);

        assertFalse(acceptee);
        assertTrue(salle.estDisponible());
        assertNull(salle.getReservationActive());
    }

    @Test
    public void coursMercrediQuatorzeSeizeEnregistreCesHeures() {
        Reservable salle = new Salle("SAL-C01", "Salle C01", 50);

        boolean acceptee = ReservationCours.reserverPourCours(salle, "Mercredi", 14, 16);

        assertTrue(acceptee);
        Creneau creneau = salle.getReservationActive().getCreneau();
        assertEquals("Mercredi", creneau.getJour());
        assertEquals(14, creneau.getHeureDebut());
        assertEquals(16, creneau.getHeureFin());
    }

    @Test
    public void secondCoursRefuseSansChangerEtat() {
        Reservable salle = new Salle("SAL-C01", "Salle C01", 50);
        assertTrue(ReservationCours.reserverPourCours(salle, "Mercredi", 14, 16));
        int numeroAvant = salle.getReservationActive().getNumero();
        int debutAvant = salle.getReservationActive().getCreneau().getHeureDebut();

        boolean second = ReservationCours.reserverPourCours(salle, "Jeudi", 10, 12);

        assertFalse(second);
        assertFalse(salle.estDisponible());
        assertEquals(numeroAvant, salle.getReservationActive().getNumero());
        assertEquals(debutAvant, salle.getReservationActive().getCreneau().getHeureDebut());
    }
}
