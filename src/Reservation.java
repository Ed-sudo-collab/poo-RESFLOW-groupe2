/**
 * Reservation active : lie une ressource, un utilisateur et un creneau.
 */
public class Reservation {

    private int numero;
    private Reservable elementReservable;
    private Utilisateur utilisateur;
    private Creneau creneau;

    public Reservation(int numero, Reservable elementReservable, Utilisateur utilisateur, Creneau creneau) {
        this.numero = numero;
        this.elementReservable = elementReservable;
        this.utilisateur = utilisateur;
        this.creneau = creneau;
    }

    public int getNumero() {
        return numero;
    }

    public Reservable getElementReservable() {
        return elementReservable;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public Creneau getCreneau() {
        return creneau;
    }

    public void afficher() {
        System.out.println("--- Reservation n°" + numero + " ---");
        elementReservable.afficher();
        utilisateur.afficher();
        creneau.afficher();
    }
}
