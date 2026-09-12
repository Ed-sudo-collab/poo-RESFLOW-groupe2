public class MaterielMobile extends Ressource {

    private String categorie;

    public MaterielMobile(int numero, String nom, String categorie) {
        super(numero, nom);
        this.categorie = categorie;

    }

    public String getCategorie() {
        return this.categorie;
    }

    @Override
    public int dureeMaxReservation() {
        return 8;
    }

    @Override
    public String toString() {
        return "Materiel Mobile" + super.toString() + "; categorie : " + categorie;
    }

}