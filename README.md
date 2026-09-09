RESFLOW : Une application de gestion des ressources d'un établissement

1-CONSTAT
    Dans les établissements, les ressources sont utilisées par différentes personnes au cours de la journée.
Lorsques leur utilisation est organisée par des méthodes manuelles,il peuttre difficile de savoir qui
utilise quoi à quel moment et si l'équipement est encore libre ou occupée.Cette situation peut entrainer des 
conflits d'horaires, de réservations, qui peuvent mener à une perte de temps.

2-SYSTEME ETUDIE
    Notre étude porte sur l'organisation et la gestion de l'utilisation des ressources d'un établissement.
Nous nous intéressons aux intéractions entre les personnes, les ressources disponibles, les horaires d'utilisation
et les réservations.

3-PROBLEMATIQUE ET BESOIN
    -Problématique: Comment faciliter l'accès aux ressources d'un établissement tout en assurant une bonne 
organisation?
    -Besoin       : L'étabmissement a besoin d'un moyen simple pour organiser et suivre l'utilisation de ses ressources.

4-SOLUTUION PROPOSEE
    Pour répondre à cette problématique et à ce besoin, nous avons pensé à RESFLOW. RESFLOW est une application Java 
qui permet la bonne gestion des ressources des établissements, leurs disponibilités et leurs réservations par les étudiants.
Grace à RESFLOW , chaque établissement pourra avoir une vue sur l'exploitation de leurs ressources.

5-FONCTIONNALITES
    Ce système permet la réalisation de plusieurs actions:
       -Consulter les ressources disponibles;
       -Réserver une ressource;
       -Annuler une réservation;
       -Consulter les réservations.
       -Gérer les ressources(par l'administrateur) : ajouter une ressources, retirer une ressource, ajouter des règles sur les 
réservations.
 
6-CONCEPTION DE RESFLOW
           RESFLOW est concue autour de quatre classes principales : 
    Classe Utilisateur
      Attributs:
          -nom              : Type String
          -email            : Type String
          -numero           : Type int
      Méthodes:
          -afficher()

    Classe Ressource
      Attributs:
          -nom              : Type String
          -type             : Type enum {BOX,PROJECTEUR,SALLE,ORDINATEUR}
          -numero           : Type int
          -disponible       : Type boolean
          -reservationActive: Type reservation
      Méthodes:
          -afficher()
          -reserver()
          -Liberer()

    Classe Creneau
      Attribut:
          -numero           : Type int
          -jour             :  Type String
          -heuredebut       : Type int
          -heurefin         : Type int
      Methodes:
          -afficher()
    Classe Reservation
      Attributs:
          -numero            : Type int
          -ressources        : Type Ressource
          -utilisateur       : Type Utilisateur
          -creneau           : Type Creneau
      Methodes:
          -afficher()

7- FONCTIONNEMENT DE RESFLOW
    le fonctionnement de RESFLOW ainsi que les liens entre les différentes classes sont visibles par un diagramme dans le fichier
docs de notre dossier GROUPE2 IA S 5.

8- STRUCTURE DU PROJET ET TECHNOLOGIES UTILISEES
     STRUCTURE DU PROJET
     
     poo-nomduprojet-groupe2/
        README.md
        docs/
            diagramme-classes.png
         src/
             Main.java
             Ressource.java
             Utilisateur.java
             Creneau.java
             Reservation.java


     Technologies utilisées:
         .Java               : langage utilisé pour dévélopper l'application.
         .POO                : approche utiisée pour exprimer les classes et leurs relations.
         .Git/GitHub         : gestion et partage du projet.

9-EQUIPE DE TRAVAIL
        -MINOUNGOU Eldine Doria                 : Rédaction du README
        -ZERBO Judicael                         :Réalisation du contenu de la classe main
        -KOARA Dorianne                         : Realisation du diagramme de fonctionnement








