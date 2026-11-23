import java.util.ArrayList;

/**
 * Catalogue des elements reservables de l'etablissement (salle, materiel, poste).
 * Le code d'une ressource est son identifiant unique : un doublon est refuse.
 */
public class CatalogueReservable {

    private ArrayList<Reservable> elements;

    public CatalogueReservable() {
        elements = new ArrayList<Reservable>();
    }

    /**
     * Ajoute un element reservable au catalogue.
     *
     * @param element l'element a ajouter (non null, code non vide)
     * @return true si l'ajout a reussi, false si l'element est null,
     *         si son code est vide ou si un element avec le meme code existe deja
     */
    public boolean ajouter(Reservable element) {
        if (element == null) {
            return false;
        }
        String code = element.getCode();
        if (code == null || code.trim().isEmpty()) {
            return false;
        }
        if (chercherParCode(code) != null) {
            System.out.println("Ajout refuse : l'element avec le code '" + code
                    + "' existe deja dans le catalogue.");
            return false;
        }
        elements.add(element);
        return true;
    }

    /**
     * Recherche un element reservable par son code identifiant.
     *
     * @param code le code a rechercher (non null)
     * @return l'element trouve, ou null si aucun element n'a ce code
     */
    public Reservable chercherParCode(String code) {
        if (code == null || code.trim().isEmpty()) {
            return null;
        }
        String codeRecherche = code.trim();
        int i;
        for (i = 0; i < elements.size(); i = i + 1) {
            Reservable element = elements.get(i);
            if (element != null && codeRecherche.equals(element.getCode())) {
                return element;
            }
        }
        return null;
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
