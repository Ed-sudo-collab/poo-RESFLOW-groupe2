Groupe 2
-NIKIEMA KAFONO ARMAND RAMZIE ARCHAD 2E JUMEAU
- ZERBO JUDICAEL 
- KOARA Dorianne

RESFLOW : Une application de gestion des ressources d'un établissement

1-CONSTAT
Dans les établissements, les ressources sont utilisées par différentes personnes
au cours de la journée. Lorsque leur utilisation est organisée par des méthodes
manuelles, il peut être difficile de savoir qui utilise quoi à quel moment et si
l'équipement est encore libre ou occupé. Cette situation peut entrainer des
conflits d'horaires, de réservations, qui peuvent mener à une perte de temps.

2-SYSTEME ETUDIE
Notre étude porte sur l'organisation et la gestion de l'utilisation des
ressources d'un établissement. Nous nous intéressons aux interactions entre
les personnes, les ressources disponibles, les horaires d'utilisation et les
réservations.

3-PROBLEMATIQUE ET BESOIN
- Problématique : Comment faciliter l'accès aux ressources d'un établissement
  tout en assurant une bonne organisation ?
- Besoin : L'établissement a besoin d'un moyen simple pour organiser et suivre
  l'utilisation de ses ressources.

4-SOLUTION PROPOSEE
Pour répondre à cette problématique et à ce besoin, nous avons pensé à RESFLOW.
RESFLOW est une application Java qui permet la bonne gestion des ressources des
établissements, leurs disponibilités et leurs réservations par les étudiants.
Grâce à RESFLOW, chaque établissement pourra avoir une vue sur l'exploitation
de leurs ressources.

5-FONCTIONNALITES
Ce système permet la réalisation de plusieurs actions :
- Consulter les ressources disponibles ;
- Réserver une ressource ;
- Annuler une réservation ;
- Consulter les réservations ;
- Gérer les ressources (par l'administrateur) : ajouter une ressource,
  retirer une ressource, ajouter des règles sur les réservations ;
- Refuser une réservation si la ressource est déjà prise ou si la durée
  du créneau dépasse la durée maximale autorisée (4h pour une salle,
  8h pour un matériel mobile).

6-CONCEPTION DE RESFLOW
RESFLOW est conçu autour des classes suivantes :

Classe Utilisateur
  Attributs :
    -nom     : Type String
    -email   : Type String
    -numero  : Type int
  Méthodes :
    -afficher()

Interface Reservable
  Méthodes :
    -estDisponible()
    -reserver()
    -liberer()
    -dureeMaxReservation()
    -getReservationActive()
    -afficher()

  La classe abstraite Ressource implémente cette interface.
  La disponibilité est centralisée dans Ressource.reserver() :
  la réservation est refusée si la ressource est déjà occupée,
  ou si la durée du créneau dépasse dureeMaxReservation().

Classe Ressource (abstraite, implémente Reservable)
  Attributs :
    -code              : Type String
    -nom               : Type String
    -disponible        : Type boolean
    -reservationActive : Type Reservation
  Méthodes :
    -typeRessource() (abstraite)
    -afficher()
    -reserver()
    -liberer()
    -dureeMaxReservation() (abstraite)

Classe PosteTravail (implémente Reservable, sans hériter de Ressource)
  Attributs :
    -code              : Type String
    -zone              : Type String
  Méthodes :
    -dureeMaxReservation() : retourne 3
  Un poste de travail peut être réservé comme une salle, mais ce n'est pas
  une ressource matérielle de l'établissement au sens de Salle ou MaterielMobile.

Classe Salle (hérite de Ressource)
  Attributs :
    -capacite : Type int 
  Méthodes :
    -dureeMaxReservation() : retourne 4

Classe MaterielMobile (hérite de Ressource)
  Attributs :
    -numeroSerie : Type String
    -categorie   : Type String
  Méthodes :
    -dureeMaxReservation() : retourne 8
    -getCategorie()

Classe Creneau
  Attributs :
    -numero      : Type int 
    -jour        : Type String
    -heuredebut  : Type int
    -heurefin    : Type int
  Méthodes :
    -afficher()

Classe Reservation
  Attributs :
    -numero            : Type int
    -elementReservable : Type Reservable
    -utilisateur       : Type Utilisateur
    -creneau           : Type Creneau
  Méthodes :
    -afficher()

7-JALON 1 : SYNTHESE (modele, invariants, essais manuels)

  Tag Git sur ce depot : jalon-1

  Classes et responsabilites :
    - Utilisateur : personne qui effectue une reservation (numero, nom, email).
    - Creneau : intervalle horaire (jour, heureDebut, heureFin) ; calcule la duree.
    - Reservation : associe un numero, un element reservable, un utilisateur et un creneau.
    - Reservable : contrat commun (disponibilite, reserver, liberer, duree max, affichage).
    - Ressource : code, nom, etat de disponibilite et logique de reservation partagee.
    - Salle / MaterielMobile : ressources concretes avec duree maximale propre (4 h / 8 h).
    - PosteTravail : element reservable sans etre une Ressource (duree max 3 h).

  Invariants (regles que le modele respecte) :
    1. Le code et le nom d'une Ressource ne sont jamais vides a la creation.
    2. Un Creneau verifie heureFin > heureDebut ; la duree vaut heureFin - heureDebut.
    3. Tant qu'une reservation est active, l'element reservable n'est pas disponible.
    4. Apres liberer(), l'element redevient disponible et n'a plus de reservation active.
    5. reserver() echoue si l'element est deja occupe.
    6. reserver() echoue si la duree du creneau depasse dureeMaxReservation().

  Essais manuels dans Main.java :
    - Affichage salle, materiel mobile et utilisateurs.
    - Refus reservation salle sur creneau de 10 h (max 4 h).
    - Acceptation reservation salle sur 2 h puis affichage de la reservation.
    - Refus d'une seconde reservation sur la meme salle deja occupee.
    - Liberation puis nouvel etat disponible.
    - Reservation materiel mobile sur 5 h (max 8 h) puis liberation.
    - Reservation poste de travail (Reservable hors Ressource) sur 2 h.

JALON 2 — composition, heritage justifie, polymorphisme, invariants

  Tag Git sur ce depot : jalon-2

  Composition :
    - Une Reservation regroupe un element reservable, un utilisateur et un creneau.
    - Ressource et PosteTravail detiennent au plus une reservationActive (lien vers Reservation).

  Heritage justifie :
    - Salle et MaterielMobile heritent de Ressource : code, nom, disponibilite et
      logique commune de reservation ; seul dureeMaxReservation() (et attributs specifiques)
      differencie les sous-types.

  Polymorphisme :
    - Le type Reservable dans Main et dans ReservationCours.reserverPourCours(Reservable, …)
      accepte Salle, MaterielMobile ou PosteTravail : le comportement depend du type reel.

  Invariants :
    - Memes regles que dans la section Jalon 1 (points 1 a 6), appliquees dans le code.

  Au moins huit essais manuels cumules (blocs === … === dans Main.java) :
    1. Affichage des durees max salle / materiel mobile
    2. Affichage salle, projecteur et utilisateurs
    3. Essai salle 8h–18h (refus : duree > max)
    4. Reservation salle 8h–10h (acceptee) + affichage reservation
    5. Autre reservation sur la meme salle occupee (refus)
    6. Liberation de la salle puis affichage disponible
    7. Reservation projecteur 8h–13h + affichage + liberation
    8. reserverPourCours : creneau vide → false
    9. reserverPourCours : cours → true
    10. reserverPourCours : double reservation → false
    11. PosteTravail : affichage puis reservation acceptee

JALON 3 — interfaces, collections, cas limites, essais manuels

  Lien du depot GitHub : https://github.com/Ed-sudo-collab/poo-RESFLOW-groupe2.git
  Tag Git sur ce depot : jalon-3

  Interface utilisee :
    - Reservable : contrat commun pour reserver, liberer, connaitre la disponibilite
      et la duree maximale, que l'element soit une Ressource (salle, materiel) ou un
      PosteTravail. Justification : Main et Reservation manipulent une reference unique
      sans connaitre la classe concrete ; on peut ajouter un nouveau type reservable
      sans recopier la logique de test.

  Collections utilisees (java.util.ArrayList) :
    - CatalogueReservable : ArrayList<Reservable> regroupe toutes les ressources
      listees par l'etablissement ; on parcourt la liste pour compter et afficher les
      elements disponibles. Justification : le nombre de salles, materiels et postes
      n'est pas fixe dans le temps ; une collection evite de multiplier les variables
      isolees dans Main.
    - HistoriqueReservations : ArrayList<Reservation> conserve les reservations
      acceptees pendant la demonstration. Justification : il faut pouvoir consulter
      plusieurs reservations reussies sans n'avoir qu'un seul attribut reservationActive
      par objet.

  Cas limites traites :
    1. Creneau avec heure de fin <= heure de debut : IllegalArgumentException dans Creneau.
    2. Code ou nom de ressource vide : IllegalArgumentException a la creation.
    3. Duree du creneau > dureeMaxReservation() : reserver() retourne false.
    4. Ressource deja occupee : seconde reserver() retourne false.
    5. Liberation : la ressource redevient disponible (catalogue mis a jour).
    6. reserverPourCours avec libelle vide : retourne false.
    7. reserverPourCours sur ressource deja occupee : retourne false.
    8. Poste de travail : duree max 3 h (refus si depassement comme pour les autres).

  Au moins douze essais manuels cumules (blocs === dans Main.java) :
    1. Catalogue initial (taille, nombre de disponibles, affichage)
    2. Durees max salle / materiel / poste
    3. Affichage salle, projecteur, utilisateurs
    4. Essai salle 8h-18h (refus duree)
    5. Reservation salle 8h-10h (acceptation + affichage)
    6. Seconde reservation sur salle occupee (refus)
    7. Liberation + catalogue des disponibles
    8. Reservation projecteur 8h-13h puis liberation
    9. reserverPourCours : creneau vide
    10. reserverPourCours : cours accepte
    11. reserverPourCours : double reservation
    12. PosteTravail : reservation acceptee
    13. Creneau invalide (exception)
    14. Historique : nombre et affichage des reservations enregistrees

8-CHOIX DE CONCEPTION (abstraction et interfaces)
  Classe abstraite retenue : Ressource, pour regrouper le code, le nom,
  la disponibilité et la réservation communes aux salles et au matériel mobile.
  Interface retenue : Reservable, pour manipuler une salle, un matériel mobile
  ou un poste de travail avec la même référence dans Main.
  Choix écarté : dupliquer reserver() et liberer() dans Salle et MaterielMobile
  sans classe abstraite ni interface. Cela aurait fonctionné pour deux classes,
  mais chaque nouvelle ressource réservable aurait recopié les mêmes règles,
  et Main n'aurait plus pu tester plusieurs types avec une seule variable Reservable.

9-FONCTIONNEMENT DE RESFLOW
Le fonctionnement de RESFLOW ainsi que les liens entre les différentes classes
sont visibles dans le diagramme du fichier docs/Diagrammes de classes.png.
Les essais dans Main passent par une référence de type Reservable. Ils
montrent : le refus d'une salle sur 10h, l'acceptation d'une salle sur 2h,
le refus d'une deuxième réservation sur la même salle déjà occupée, la
libération de la salle, puis l'acceptation d'un matériel mobile sur 5h,
ainsi qu'un poste de travail (Reservable sans Ressource) sur 2 h.

10-STRUCTURE DU PROJET ET TECHNOLOGIES UTILISEES

STRUCTURE DU PROJET

poo-RESFLOW-groupe2/
    README.md
    docs/
        Diagrammes de classes.png
    src/
        Main.java
        Reservable.java
        Ressource.java
        Salle.java
        MaterielMobile.java
        PosteTravail.java
        ReservationCours.java
        Utilisateur.java
        Creneau.java
        Reservation.java
        CatalogueReservable.java
        HistoriqueReservations.java

Technologies utilisées :
    .Java       : langage utilisé pour développer l application.
    .POO        : approche utilisée pour exprimer les classes et leurs relations (héritage, polymorphisme).
    .Git/GitHub : gestion et partage du projet.

11-EQUIPE DE TRAVAIL
- RAMZIE : ajouter l'interface Reservable.
- JUDICAEL : tester Salle et MaterielMobile à travers l'interface Reservable ; centraliser la disponibilité et refuser une durée excessive ou une ressource occupée.
- DORIANNE : PosteTravail (Reservable hors Ressource), choix écarté dans le README, diagramme.
