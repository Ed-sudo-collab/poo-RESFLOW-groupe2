/**
 * Classe de demonstration de RESFLOW.
 * Pour chaque scenario, le resultat attendu est ecrit, puis le resultat obtenu
 * est verifie et affiche (PASS / FAIL). Les tests d'echec verifient qu'aucun
 * changement d'etat n'a eu lieu (disponibilite inchangee, reservationActive null).
 */
public class Main {

    private static int testsPasses = 0;
    private static int testsEchoues = 0;

    /**
     * Verifie un resultat obtenu par rapport au resultat attendu et affiche PASS / FAIL.
     */
    private static void verifier(String nomTest, boolean attendu, boolean obtenu) {
        System.out.print("  Test : " + nomTest);
        System.out.print(" | Attendu : " + attendu);
        System.out.print(" | Obtenu : " + obtenu);
        if (attendu == obtenu) {
            System.out.println(" -> PASS");
            testsPasses = testsPasses + 1;
        } else {
            System.out.println(" -> FAIL");
            testsEchoues = testsEchoues + 1;
        }
    }

    /**
     * Verifie un resultat obtenu par rapport au resultat attendu (objets) et affiche PASS / FAIL.
     * Gere correctement les comparaisons avec null.
     */
    private static void verifier(String nomTest, Object attendu, Object obtenu) {
        System.out.print("  Test : " + nomTest);
        System.out.print(" | Attendu : " + attendu);
        System.out.print(" | Obtenu : " + obtenu);
        boolean egaux;
        if (attendu == null && obtenu == null) {
            egaux = true;
        } else if (attendu == null || obtenu == null) {
            egaux = false;
        } else {
            egaux = attendu.equals(obtenu);
        }
        if (egaux) {
            System.out.println(" -> PASS");
            testsPasses = testsPasses + 1;
        } else {
            System.out.println(" -> FAIL");
            testsEchoues = testsEchoues + 1;
        }
    }

    /**
     * Verifie qu'un echec de reservation n'a pas modifie l'etat de la ressource.
     */
    private static void verifierEtatInchange(String nomTest, Reservable cible, boolean etaitDisponible,
                                             Reservation reservationAvant) {
        boolean ok = ServiceReservation.etatInchangeApresEchec(cible, etaitDisponible, reservationAvant);
        System.out.print("  Test (etat inchange) : " + nomTest);
        if (ok) {
            System.out.println(" -> PASS (ressource inchangee)");
            testsPasses = testsPasses + 1;
        } else {
            System.out.println(" -> FAIL (etat modifie alors que la reservation a ete refusee)");
            testsEchoues = testsEchoues + 1;
        }
    }

    private static boolean reserver(Reservable cible, int numeroReservation,
                                    Utilisateur utilisateur, Creneau creneau) {
        return ServiceReservation.reserver(cible, numeroReservation, utilisateur, creneau);
    }

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

        System.out.println("=== Ajout d'un doublon (code SAL-B12 deja present) ===");
        boolean ajoutDouble = catalogue.ajouter(new Salle("SAL-B12", "Salle B12 bis", 50));
        verifier("Ajout doublon refuse", false, ajoutDouble);
        System.out.println("Taille apres tentative de doublon : " + catalogue.taille());
        System.out.println();

        System.out.println("=== Recherche par code ===");
        Reservable trouve = catalogue.chercherParCode("SAL-B12");
        verifier("Recherche SAL-B12 trouvee", salleA, trouve);
        Reservable nonTrouve = catalogue.chercherParCode("INCONNU");
        verifier("Recherche INCONNU retourne null", (Object) null, (Object) nonTrouve);
        Reservable codeVide = catalogue.chercherParCode("");
        verifier("Recherche code vide retourne null", (Object) null, (Object) codeVide);
        System.out.println();

        System.out.println("=== Ajout au catalogue : element null (refus) ===");
        int tailleAvantNull = catalogue.taille();
        boolean ajoutNull = catalogue.ajouter(null);
        verifier("Ajout null refuse", false, ajoutNull);
        verifier("Taille catalogue inchangee apres ajout null", true, catalogue.taille() == tailleAvantNull);
        System.out.println();

        System.out.println("=== ServiceReservation : parametres null (refus) ===");
        verifier("Reserver avec cible null", false,
                ServiceReservation.reserver(null, 1, aminata, new Creneau(1, "Lundi", 9, 10)));
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

        System.out.println("=== Essai salle 8h-18h (duree 10h > max 4h) ===");
        boolean etaitDisponibleAvant = salleA.estDisponible();
        boolean okRefus = reserver(salleA, 1, aminata, new Creneau(1, "Lundi", 8, 18));
        verifier("Reservation salle 10h refusee", false, okRefus);
        verifierEtatInchange("Etat salle apres refus 10h", salleA, etaitDisponibleAvant, null);
        System.out.println();

        System.out.println("=== Reservation salle 8h-10h (duree 2h <= max 4h) ===");
        boolean ok = reserver(salleA, 2, aminata, new Creneau(2, "Lundi", 8, 10));
        verifier("Reservation salle 2h acceptee", true, ok);
        enregistrerSiOk(ok, salleA, historique);
        if (ok) {
            salleA.getReservationActive().afficher();
        }
        System.out.println();

        System.out.println("=== Autre reservation sur la meme salle (deja occupee) ===");
        etaitDisponibleAvant = salleA.estDisponible();
        Reservation reservationAvant = salleA.getReservationActive();
        boolean okDouble = reserver(salleA, 3, kofi, new Creneau(3, "Lundi", 10, 12));
        verifier("Deuxieme reservation sur salle occupee refusee", false, okDouble);
        verifierEtatInchange("Etat salle apres double reservation", salleA, etaitDisponibleAvant, reservationAvant);
        System.out.println();

        System.out.println("=== Liberation de la salle ===");
        salleA.liberer();
        salleA.afficher();
        catalogue.afficherDisponibles();
        System.out.println();

        System.out.println("=== Nouvelle reservation apres liberation (8h-10h) ===");
        boolean okApresLiberation = reserver(salleA, 5, aminata, new Creneau(5, "Lundi", 8, 10));
        verifier("Reservation apres liberation acceptee", true, okApresLiberation);
        enregistrerSiOk(okApresLiberation, salleA, historique);
        System.out.println();

        System.out.println("=== Reservation projecteur 8h-13h (duree 5h <= max 8h) ===");
        ok = reserver(projecteur, 4, kofi, new Creneau(4, "Mardi", 8, 13));
        verifier("Reservation projecteur 5h acceptee", true, ok);
        enregistrerSiOk(ok, projecteur, historique);
        if (ok) {
            projecteur.getReservationActive().afficher();
        }
        System.out.println();

        projecteur.liberer();
        projecteur.afficher();
        System.out.println();

        System.out.println("=== reserverPourCours : jour vide (refus) ===");
        Reservable salleCours = new Salle("SAL-C01", "Salle C01", 50);
        boolean coursJourVide = ReservationCours.reserverPourCours(salleCours, "", 14, 16);
        verifier("Cours avec jour vide refuse", false, coursJourVide);
        verifierEtatInchange("Etat salle C01 apres refus jour vide", salleCours, true, null);
        System.out.println();

        System.out.println("=== reserverPourCours : Mercredi 14h-16h (accepte) ===");
        boolean coursAccepte = ReservationCours.reserverPourCours(salleCours, "Mercredi", 14, 16);
        verifier("Cours Mercredi 14h-16h acceptee", true, coursAccepte);
        enregistrerSiOk(coursAccepte, salleCours, historique);
        if (coursAccepte) {
            Reservation res = salleCours.getReservationActive();
            System.out.print("  Creneau enregistre : ");
            res.getCreneau().afficher();
            boolean heuresCorrectes = res.getCreneau().getHeureDebut() == 14
                    && res.getCreneau().getHeureFin() == 16
                    && "Mercredi".equals(res.getCreneau().getJour());
            verifier("Heures du creneau : 14h-16h le Mercredi", true, heuresCorrectes);
        }
        System.out.println();

        System.out.println("=== reserverPourCours : double reservation (refus, salle occupee) ===");
        etaitDisponibleAvant = salleCours.estDisponible();
        Reservation reservationAvantCours = salleCours.getReservationActive();
        boolean coursDouble = ReservationCours.reserverPourCours(salleCours, "Jeudi", 10, 12);
        verifier("Double reservation sur salle occupee refusee", false, coursDouble);
        verifierEtatInchange("Etat salle C01 apres double reservation", salleCours, etaitDisponibleAvant, reservationAvantCours);
        System.out.println();

        System.out.println("=== PosteTravail (Reservable sans Ressource) ===");
        poste.afficher();
        boolean okPoste = reserver(poste, 11, aminata, new Creneau(11, "Vendredi", 9, 11));
        verifier("Reservation poste acceptee", true, okPoste);
        enregistrerSiOk(okPoste, poste, historique);
        poste.afficher();
        System.out.println();

        System.out.println("=== Cas limite creneau invalide (heure fin <= debut) ===");
        try {
            new Creneau(99, "Test", 14, 14);
            System.out.println("Erreur : le creneau aurait du etre refuse.");
            verifier("Creneau invalide detecte", true, false);
        } catch (IllegalArgumentException e) {
            System.out.println("Creneau invalide detecte : " + e.getMessage());
            verifier("Creneau invalide detecte", true, true);
        }
        System.out.println();

        System.out.println("=== Bilan collections ===");
        System.out.println("Reservations enregistrees dans l'historique : " + historique.nombre());
        historique.afficher();
        System.out.println();

        System.out.println("=== RESULTAT FINAL DES TESTS ===");
        System.out.println("Tests passes : " + testsPasses);
        System.out.println("Tests echoues : " + testsEchoues);
        if (testsEchoues == 0) {
            System.out.println("TOUS LES TESTS SONT PASSES.");
        } else {
            System.out.println("CERTAINS TESTS ONT ECHOUE.");
        }
    }
}
