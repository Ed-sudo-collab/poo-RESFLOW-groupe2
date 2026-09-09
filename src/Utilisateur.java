/**
 * Utilisateur qui peut reserver une ressource.
 */
public class Utilisateur {

    private int numero;
    private String nom;
    private String email;

    public Utilisateur(int numero, String nom, String email) {
        this.numero = numero;
        this.nom = nom;
        this.email = email;
    }

    public int getNumero() {
        return numero;
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    public void afficher() {
        System.out.println("Utilisateur n°" + numero + " | " + nom + " | " + email);
    }
}
