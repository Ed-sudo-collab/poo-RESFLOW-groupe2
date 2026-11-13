public class Main {

    private static void enregistrerSiOk(boolean ok, Reservable cible, HistoriqueReservations historique) {
        if (ok && cible.getReservationActive() != null) {
            historique.enregistrer(cible.getReservationActive());
        }
    }

    public static void main(String[] args) {
        CatalogueReservable catalogue = new CatalogueReservable();
        HistoriqueReservations historique = new HistoriqueReservations();

        Reservable salleA = new Salle("SAL-B12", "Salle B12", 100);
        Reservable projecteur =
                new MaterielMobile("MAT-EPSON", "Videoprojecteur Epson", "SN-8842", "Audiovisuel");
        Reservable poste = new PosteTravail("POSTE-A3", "Zone silencieuse");

        catalogue.ajouter(salleA);
        catalogue.ajouter(projecteur);
        catalogue.ajouter(poste);

        Utilisateur aminata = new Utilisateur(1, "Aminata Traore", "aminata@2ie.edu");
        Utilisateur kofi = new Utilisateur(2, "Kofi Ouedraogo", "kofi@2ie.edu");

        System.out.println("=== Catalogue initial ===");
        System.out.println("Taille du catalogue : " + catalogue.taille());
        System.out.println("Disponibles : " + catalogue.nombreDisponibles());
        catalogue.afficherDisponibles();
        System.out.println();

        System.out.println("=== Durees max ===");
        System.out.println("Duree max pour une salle: " + salleA.dureeMaxReservation() + " h");
        System.out.println("Duree max pour un materiel mobile: " + projecteur.dureeMaxReservation() + " h");
        System.out.println("Duree max pour un poste: " + poste.dureeMaxReservation() + " h");
        System.out.println();

        System.out.println("=== Affichage ===");
        salleA.afficher();
        projecteur.afficher();
        aminata.afficher();
        kofi.afficher();
        System.out.println();

        System.out.println("=== Essai salle 8h-18h ===");
        enregistrerSiOk(salleA.reserver(1, aminata, new Creneau(1, "Lundi", 8, 18)), salleA, historique);
        System.out.println();

        System.out.println("=== Reservation salle 8h-10h ===");
        boolean ok = salleA.reserver(2, aminata, new Creneau(2, "Lundi", 8, 10));
        enregistrerSiOk(ok, salleA, historique);
        if (ok) {
            salleA.getReservationActive().afficher();
        }
        System.out.println();

        System.out.println("=== Autre reservation sur la meme salle ===");
        enregistrerSiOk(salleA.reserver(3, kofi, new Creneau(3, "Lundi", 10, 12)), salleA, historique);
        System.out.println();

        System.out.println("=== Liberation ===");
        salleA.liberer();
        salleA.afficher();
        catalogue.afficherDisponibles();
        System.out.println();

        System.out.println("=== Reservation projecteur 8h-13h ===");
        ok = projecteur.reserver(4, kofi, new Creneau(4, "Mardi", 8, 13));
        enregistrerSiOk(ok, projecteur, historique);
        if (ok) {
            projecteur.getReservationActive().afficher();
        }
        System.out.println();

        projecteur.liberer();
        projecteur.afficher();
        System.out.println();

        System.out.println("=== reserverPourCours ===");
        Reservable salleCours = new Salle("SAL-C01", "Salle C01", 50);
        System.out.println("Creneau vide : " + ReservationCours.reserverPourCours(salleCours, ""));
        ok = ReservationCours.reserverPourCours(salleCours, "Mercredi 14h-16h");
        System.out.println("Reservation cours : " + ok);
        enregistrerSiOk(ok, salleCours, historique);
        System.out.println("Double reservation : " + ReservationCours.reserverPourCours(salleCours, "Jeudi 10h-12h"));
        System.out.println();

        System.out.println("=== PosteTravail (Reservable sans Ressource) ===");
        poste.afficher();
        enregistrerSiOk(poste.reserver(11, aminata, new Creneau(11, "Vendredi", 9, 11)), poste, historique);
        poste.afficher();
        System.out.println();

        System.out.println("=== Cas limite creneau invalide (heure fin <= debut) ===");
        try {
            new Creneau(99, "Test", 14, 14);
            System.out.println("Erreur : le creneau aurait du etre refuse.");
        } catch (IllegalArgumentException e) {
            System.out.println("Creneau invalide detecte : " + e.getMessage());
        }
        System.out.println();

        System.out.println("=== Bilan collections ===");
        System.out.println("Reservations enregistrees dans l'historique : " + historique.nombre());
        historique.afficher();
    }
}
