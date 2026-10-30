/**
 * Creneau de reservation : jour, heure de debut et heure de fin.
 */
public class Creneau {

    private int numero;
    private String jour;
    private int heureDebut;
    private int heureFin;

    public Creneau(int numero, String jour, int heureDebut, int heureFin) {
        if (heureFin <= heureDebut) {
            throw new IllegalArgumentException(
                    "L'heure de fin doit etre strictement superieure a l'heure de debut.");
        }
        this.numero = numero;
        this.jour = jour == null ? "" : jour.trim();
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

    public void afficher() {
        System.out.println("Creneau n°" + numero + " : " + getLibelle());
    }
}
