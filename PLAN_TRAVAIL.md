# Plan de travail — Groupe 2

## Projet

**RESFLOW — Réservation de ressources**

Ce document décrit les corrections à apporter au projet après l’évaluation. Il s’agit uniquement d’un plan de travail : aucune modification du code Java n’est effectuée dans ce document.

## Remarques de l’évaluateur

Le projet obtient **16/20**. Les points positifs sont :

- le découpage en ressources, salles, matériels, créneaux, utilisateurs et réservations ;
- la présence d’une hiérarchie entre `Ressource`, `Salle` et `MaterielMobile` ;
- l’existence de règles de durée maximale.

Les corrections demandées sont :

1. ajouter `Reservable` ;
2. centraliser la disponibilité ;
3. refuser une durée excessive ;
4. refuser une ressource déjà occupée ;
5. tester `Salle` et `MaterielMobile` à travers une référence commune ;
6. harmoniser les noms ;
7. corriger le README.

---

## 1. Décisions de conception

### Interface `Reservable`

Créer une interface `Reservable` dans `src/Reservable.java`.

L’interface doit définir le contrat commun aux éléments qui peuvent être réservés. Les méthodes prévu sont :

- `estDisponible()` ;
- `reserver(...)` ;
- `liberer()` ;
- `dureeMaxReservation()` ;
- `getReservationActive()` ;
- `afficher()`.

### Hiérarchie

La hiérarchie doit rester la suivante :

```text
Reservable   <<interface>>
     ↑
Ressource    <<abstract>>
     ├── Salle
     └── MaterielMobile
```

- `Ressource` doit implémenter `Reservable` ;
- `Salle` et `MaterielMobile` doivent continuer à hériter de `Ressource` ;
- les classes filles ne doivent pas dupliquer les méthodes communes.

### Centralisation de la disponibilité

La disponibilité et la réservation active doivent rester dans `Ressource` :

- `disponible` ;
- `reservationActive` ;
- `reserver(...)` ;
- `liberer()`.

L’interface `Reservable` déclare les méthodes, mais ne doit pas créer une deuxième logique ou une deuxième variable de disponibilité.

Le code actuel possède déjà une partie de cette logique dans `Ressource`. Il faut la conserver et la clarifier plutôt que de la dupliquer dans `Salle` ou `MaterielMobile`.

---

## 2. Répartition du travail

### MINOUNGOU Eldine Doria — hiérarchie et interface

Tâches :

1. Créer `src/Reservable.java`.
2. Faire implémenter `Reservable` par `Ressource`.
3. Vérifier la relation entre `Ressource`, `Salle` et `MaterielMobile`.
4. Ajouter les méthodes communes au contrat de l’interface.
5. Vérifier que `Salle` conserve une durée maximale de **4 heures**.
6. Vérifier que `MaterielMobile` conserve une durée maximale de **8 heures**.
7. Ajouter ou vérifier les annotations `@Override`.
8. Vérifier que `disponible` et `reservationActive` ne sont pas dupliquées dans les classes filles.

Critère de réussite : les deux classes concrètes sont utilisables comme des `Reservable` sans dupliquer la logique de réservation.

### ZERBO Judicaël — règles et cas de refus

Tâches :

1. Vérifier la logique de `Ressource.reserver(...)`.
2. Refuser une ressource déjà occupée.
3. Calculer la durée du créneau.
4. Refuser une durée supérieure à la durée maximale de la ressource.
5. Créer la réservation seulement après toutes les vérifications.
6. Modifier l’état de la ressource uniquement après une réservation acceptée.
7. Ne pas remplacer une réservation existante lorsqu’une nouvelle réservation est refusée.
8. Vérifier que `liberer()` rend la ressource disponible.
9. Ajouter ou compléter les messages de refus.
10. Tester les cas limites et les cas de conflit.

La logique de réservation doit rester dans un seul endroit : `Ressource`. Il ne faut pas mettre les mêmes vérifications dans `Main`, `Reservation`, `Salle` et `MaterielMobile`.

### KOARA Dorianne — diagramme et polymorphisme

Tâches :

1. Modifier `docs/Diagrammes de classes.png`.
2. Ajouter `Reservable` avec la mention `<<interface>>`.
3. Représenter la relation d’implémentation entre `Reservable` et `Ressource`.
4. Représenter l’héritage de `Salle` et `MaterielMobile` depuis `Ressource`.
5. Représenter les relations entre `Reservation`, `Ressource`, `Utilisateur` et `Creneau`.
6. Montrer que `disponible` et `reservationActive` appartiennent à `Ressource`.
7. Dans `Main`, utiliser des références de type `Reservable` pour `Salle` et `MaterielMobile`.
8. Utiliser un tableau ou une liste de `Reservable` si cela rend la démonstration plus claire.
9. Appeler les méthodes communes à travers le type `Reservable`.
10. Ne pas utiliser `instanceof` ni de cast dans les tests de polymorphisme.
11. Mettre à jour le README après avoir stabilisé le code.

---

## 3. Tests à réaliser

| Cas | Type de ressource | Durée ou situation | Résultat attendu |
|---|---|---:|---|
| 1 | `Salle` | 2 h | Réservation acceptée |
| 2 | `Salle` | 4 h | Réservation acceptée |
| 3 | `Salle` | 5 h | Réservation refusée |
| 4 | `Salle` | 10 h | Réservation refusée |
| 5 | `Salle` | Deuxième réservation pendant qu’elle est occupée | Réservation refusée |
| 6 | `Salle` | Après libération | Nouvelle réservation acceptée |
| 7 | `MaterielMobile` | 5 h | Réservation acceptée |
| 8 | `MaterielMobile` | 8 h | Réservation acceptée |
| 9 | `MaterielMobile` | 9 h | Réservation refusée |
| 10 | Les deux types | Passage par une référence `Reservable` | Les deux objets répondent aux mêmes méthodes |

### Vérifications importantes

- Une réservation refusée ne doit pas créer de nouvelle réservation.
- Une réservation refusée ne doit pas rendre une ressource disponible occupée.
- Une ressource acceptée doit devenir indisponible.
- `liberer()` doit supprimer la réservation active et rendre la ressource disponible.
- Les messages doivent indiquer la raison du refus.
- Si possible, un créneau dont l’heure de fin est inférieure ou égale à l’heure de début doit aussi être refusé.

---

## 4. Correction des noms

Le code et la documentation doivent utiliser les mêmes noms.

- Garder le nom de classe Java `MaterielMobile` ; utiliser « matériel mobile » dans le texte français.
- Utiliser `ressource` au singulier dans `Reservation`.
- Utiliser `heureDebut` et `heureFin` avec la même casse.
- Utiliser `getCategorie` et non une autre variante.
- Garder les noms de méthodes Java en `camelCase` :
  - `estDisponible` ;
  - `reserver` ;
  - `liberer` ;
  - `dureeMaxReservation` ;
  - `getReservationActive` ;
  - `getHeureDebut` ;
  - `getHeureFin`.
- Vérifier que le README utilise exactement les mêmes noms que les classes Java.
- Vérifier également le titre et le chemin du diagramme dans le README.

---

## 5. Correction du README

Le README doit :

1. présenter correctement le projet RESFLOW ;
2. expliquer le rôle de `Reservable` ;
3. présenter la hiérarchie `Reservable` / `Ressource` / `Salle` / `MaterielMobile` ;
4. indiquer que `Ressource` centralise la disponibilité et les règles ;
5. indiquer les durées maximales de 4 h et 8 h ;
6. expliquer les deux refus principaux : durée excessive et ressource occupée ;
7. présenter les scénarios de test ;
8. montrer que les deux types sont manipulés via une référence `Reservable` ;
9. ajouter `Reservable.java` dans l’arborescence ;
10. corriger les fautes de français et d’orthographe ;
11. supprimer ou clarifier les fonctionnalités qui ne sont pas encore implémentées ;
12. remplacer la mention « diagramme de fonctionnement » par « diagramme de classes » si c’est bien le diagramme réalisé ;
13. ajouter la commande de compilation et d’exécution.

Commandes indicatives :

```bash
javac src/*.java
java -cp src Main
```

Le README doit correspondre exactement au code réellement présent.

---

## 6. Ordre de réalisation

1. Minoungou définit l’interface et corrige la hiérarchie.
2. Zerbo vérifie les règles et les cas de refus.
3. Dorianne adapte `Main` avec une référence commune `Reservable`.
4. Dorianne met à jour le diagramme de classes.
5. Dorianne corrige le README avec la relecture des deux autres membres.
6. Le groupe compile le projet.
7. Le groupe compare le code, le diagramme et le README.
8. Le groupe vérifie la checklist finale.

---

## Checklist finale

- [ ] `Reservable` existe et est documenté.
- [ ] `Ressource` implémente `Reservable`.
- [ ] `Salle` et `MaterielMobile` héritent de `Ressource`.
- [ ] La disponibilité est définie à un seul endroit.
- [ ] Une durée excessive est refusée.
- [ ] Une ressource occupée est refusée.
- [ ] Une réservation refusée ne modifie pas l’état.
- [ ] `liberer()` fonctionne correctement.
- [ ] `Salle` et `MaterielMobile` sont testés via `Reservable`.
- [ ] Aucun test de polymorphisme n’utilise `instanceof` ou un cast.
- [ ] Le diagramme représente l’interface et les relations.
- [ ] Le README est corrigé et cohérent avec le code.
- [ ] Le projet compile sans erreur.
