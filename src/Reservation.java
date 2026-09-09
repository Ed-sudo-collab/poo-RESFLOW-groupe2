/**
 * Reservation active : lie une ressource, un utilisateur et un creneau.
 */
public class Reservation {

    private int numero;
    private Ressource ressource;
    private Utilisateur utilisateur;
    private Creneau creneau;

    public Reservation(int numero, Ressource ressource, Utilisateur utilisateur, Creneau creneau) {
        this.numero = numero;
        this.ressource = ressource;
        this.utilisateur = utilisateur;
        this.creneau = creneau;
    }

    public int getNumero() {
        return numero;
    }

    public Ressource getRessource() {
        return ressource;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public Creneau getCreneau() {
        return creneau;
    }

    public void afficher() {
        System.out.println("--- Reservation n°" + numero + " ---");
        ressource.afficher();
        utilisateur.afficher();
        creneau.afficher();
    }
}
