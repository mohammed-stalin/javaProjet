package ma.fstt.model;

public class Produit {
    private Long id_Produit ;

    private String libele ;

    private String designation ;

    private String prix;

    public Long getId_Produit() {
        return id_Produit;
    }
    public Produit (){

    }
    public Produit (Long id_Produit, String libele, String designation , String prix){
        this.id_Produit = id_Produit;
        this.libele=libele;
        this.designation=designation;
        this.prix=prix;

    }

    public void setId_Produit(Long id_Produit) {
        this.id_Produit = id_Produit;
    }

    public String getLibele() {
        return libele;
    }

    public void setLibele(String libele) {
        this.libele = libele;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public String getPrix() { return prix; }

    public void setPrix(String prix) { this.prix = prix; }
    @Override
    public String toString() {
        return "Produit{" +
                "id_produit =" + id_Produit +
                ", libele='" + libele + '\'' +
                ", designation='" + designation + '\'' +
                ", prix='" + prix + '\'' +
                '}';
    }


}
