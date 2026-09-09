/**
 * Demonstration : creation, reservation, affichage, liberation.
 */
public class Main {

    public static void main(String[] args) {
        
        Ressource salle = new Ressource(1, "Salle B12", Ressource.TypeRessource.SALLE);
        Ressource projecteur = new Ressource(2, "Videoprojecteur Epson", Ressource.TypeRessource.PROJECTEUR);
        Ressource box = new Ressource(3, "Box A", Ressource.TypeRessource.BOX);

        Utilisateur aminata = new Utilisateur(1, "Aminata Traore", "aminata@2ie.edu");
        Utilisateur kofi = new Utilisateur(2, "Kofi Ouedraogo", "kofi@2ie.edu");

        System.out.println("=== Objets crees ===");
        salle.afficher();
        projecteur.afficher();
        box.afficher();
        aminata.afficher();
        kofi.afficher();
        System.out.println();

        Creneau creneau = new Creneau(1, "Lundi", 8, 10);
        System.out.println("=== Reservation ===");
        salle.reserver(1, aminata, creneau);
        System.out.println();

        System.out.println("=== Details de la reservation ===");
        salle.getReservationActive().afficher();
        System.out.println();

        System.out.println("=== Tentative de seconde reservation ===");
        salle.reserver(2, kofi, new Creneau(2, "Lundi", 10, 12));
        System.out.println();

        System.out.println("=== Liberation ===");
        salle.liberer();
        salle.afficher();
    }
}
