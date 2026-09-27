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

    public int getDuree() {
    return heureFin - heureDebut;
    }
    public boolean seChevaucheAvec(Creneau autre) {
    if (autre == null || !this.jour.equalsIgnoreCase(autre.jour)) {
        return false;
    }
    return this.heureDebut < autre.heureFin && autre.heureDebut < this.heureFin;
    }

    public void afficher() {
        System.out.println("Creneau n°" + numero + " : " + getLibelle());
    }
}
