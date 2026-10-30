public class Main {

    public static void main(String[] args) {

        Reservable salleA = new Salle("SAL-B12", "Salle B12", 100);
        Reservable projecteur = new MaterielMobile("MAT-EPSON", "Videoprojecteur Epson", "SN-8842", "Audiovisuel");

        Utilisateur aminata = new Utilisateur(1, "Aminata Traore", "aminata@2ie.edu");
        Utilisateur kofi = new Utilisateur(2, "Kofi Ouedraogo", "kofi@2ie.edu");

        System.out.println("=== Durees max ===");

        System.out.println("Duree max pour une salle: " + salleA.dureeMaxReservation() + " h");
        System.out.println("Duree max pour un materiel mobile: " + projecteur.dureeMaxReservation() + " h");
        System.out.println();

        System.out.println("=== Affichage ===");
        salleA.afficher();
        projecteur.afficher();
        aminata.afficher();
        kofi.afficher();
        System.out.println();

        // Test de reservation d'une salle pour un creneau de 10 heures
        System.out.println("=== Essai salle 8h-18h ===");
        salleA.reserver(1, aminata, new Creneau(1, "Lundi", 8, 18));
        System.out.println();

        // Test de reservation d'une salle pour un creneau de 2 heures
        System.out.println("=== Reservation salle 8h-10h ===");
        boolean ok = salleA.reserver(2, aminata, new Creneau(2, "Lundi", 8, 10));
        if (ok) {
            salleA.getReservationActive().afficher();
        }
        System.out.println();

        System.out.println("=== Autre reservation sur la meme salle ===");
        salleA.reserver(3, kofi, new Creneau(3, "Lundi", 10, 12));
        System.out.println();

        System.out.println("=== Liberation ===");
        salleA.liberer();
        salleA.afficher();
        System.out.println();

        // Test de reservation d'un materiel pour un creneau de 5 heures
        System.out.println("=== Reservation projecteur 8h-13h ===");
        ok = projecteur.reserver(4, kofi, new Creneau(4, "Mardi", 8, 13));
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
        System.out.println("Reservation cours : " + ReservationCours.reserverPourCours(salleCours, "Mercredi 14h-16h"));
        System.out.println("Double reservation : " + ReservationCours.reserverPourCours(salleCours, "Jeudi 10h-12h"));
    }
}
