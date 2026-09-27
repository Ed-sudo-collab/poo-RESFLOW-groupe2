Groupe 2
-NIKIEMA KAFONO ARMAND RAMZIE ARCHAD 2E JUMEAU
- OUALBEOGO GUETWENDE JULIE SIDOINE
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

  La classe abstraite Ressource implémente cette interface.
  La disponibilité est centralisée dans Ressource.reserver() :
  la réservation est refusée si la ressource est déjà occupée,
  ou si la durée du créneau dépasse dureeMaxReservation().

Classe Ressource (abstraite, implémente Reservable)
  Attributs :
    -nom               : Type String
    -numero            : Type int
    -disponible        : Type boolean
    -reservationActive : Type Reservation 
  Méthodes :
    -afficher()
    -reserver()
    -liberer()
    -dureeMaxReservation() (abstraite)

Classe Salle (hérite de Ressource)
  Attributs :
    -capacite : Type int 
  Méthodes :
    -dureeMaxReservation() : retourne 4

Classe MaterielMobile (hérite de Ressource)
  Attributs :
    -categorie : Type String
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
    -numero      : Type int
    -ressources  : Type Ressource
    -utilisateur : Type Utilisateur
    -creneau     : Type Créneau
  Méthodes :
    -afficher()

7-FONCTIONNEMENT DE RESFLOW
Le fonctionnement de RESFLOW ainsi que les liens entre les différentes classes
sont visibles dans le diagramme du fichier docs/Diagrammes de classes.png.
Les essais dans Main passent par une référence de type Reservable. Ils
montrent : le refus d'une salle sur 10h, l'acceptation d'une salle sur 2h,
le refus d'une deuxième réservation sur la même salle déjà occupée, la
libération de la salle, puis l'acceptation d'un matériel mobile sur 5h.

8-STRUCTURE DU PROJET ET TECHNOLOGIES UTILISEES

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
        Utilisateur.java
        Creneau.java
        Reservation.java

Technologies utilisées :
    .Java       : langage utilisé pour développer l application.
    .POO        : approche utilisée pour exprimer les classes et leurs relations (héritage, polymorphisme).
    .Git/GitHub : gestion et partage du projet.

9-EQUIPE DE TRAVAIL
- RAMZIE : ajouter l'interface Reservable.
- JULIE : centraliser la disponibilité ; refuser une durée excessive ou une ressource occupée.
- JUDICAEL : tester Salle et MaterielMobile à travers l'interface Reservable.
- DORIANNE : harmoniser les noms des membres du groupe dans le README et corriger le diagramme de classes.
