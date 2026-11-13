import java.util.ArrayList;

/**
 * Catalogue des elements reservables de l'etablissement (salle, materiel, poste).
 */
public class CatalogueReservable {

    private ArrayList<Reservable> elements;

    public CatalogueReservable() {
        elements = new ArrayList<Reservable>();
    }

    public void ajouter(Reservable element) {
        if (element != null) {
            elements.add(element);
        }
    }

    public int nombreDisponibles() {
        int compteur = 0;
        int i;

        for (i = 0; i < elements.size(); i = i + 1) {
            if (elements.get(i).estDisponible()) {
                compteur = compteur + 1;
            }
        }
        return compteur;
    }

    public void afficherDisponibles() {
        int i;

        System.out.println("--- Elements disponibles dans le catalogue ---");
        for (i = 0; i < elements.size(); i = i + 1) {
            if (elements.get(i).estDisponible()) {
                elements.get(i).afficher();
            }
        }
    }

    public Reservable get(int index) {
        return elements.get(index);
    }

    public int taille() {
        return elements.size();
    }
}
