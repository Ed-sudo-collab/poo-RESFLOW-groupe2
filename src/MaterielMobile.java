public class MaterielMobile extends Ressource {

    private String numeroSerie;
    private String categorie;

    public MaterielMobile(String code, String nom, String numeroSerie, String categorie) {
        super(code, nom);
        if (numeroSerie == null || numeroSerie.trim().isEmpty()) {
            throw new IllegalArgumentException("Le numero de serie ne peut pas etre vide.");
        }
        this.numeroSerie = numeroSerie.trim();
        this.categorie = categorie;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public String getCategorie() {
        return categorie;
    }

    @Override
    public String typeRessource() {
        return "MaterielMobile";
    }

    @Override
    public int dureeMaxReservation() {
        return 8;
    }

    @Override
    public String toString() {
        return "MaterielMobile" + super.toString() + "; numeroSerie=" + numeroSerie + "; categorie=" + categorie;
    }

}
