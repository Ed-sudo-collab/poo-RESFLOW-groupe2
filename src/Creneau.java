/**
 * Creneau de reservation : jour, heure de debut et heure de fin.
 */
public class Creneau {

    private int numero;
    private String jour;
    private int heureDebut;
    private int heureFin;

    public Creneau(int numero, String jour, int heureDebut, int heureFin) {
        this.numero = numero;
        this.jour = jour;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
    }

    public int getNumero() {
        return numero;
    }

    public String getJour() {
        return jour;
    }

    public int getHeureDebut() {
        return heureDebut;
    }

    public int getHeureFin() {
        return heureFin;
    }

    public String getLibelle() {
        return jour + " " + heureDebut + "h-" + heureFin + "h";
    }

    public void afficher() {
        System.out.println("Creneau n°" + numero + " : " + getLibelle());
    }
}
