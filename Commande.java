package ma.fstt.model;
public class Commande {
    private Long id_commande;
    private String libele;
    private String code;
    private String date;
    public Commande() {}
    public  Commande(Long id_commande,String libele,String code,String date){
        this.id_commande=id_commande;
        this.libele=libele;
        this.code=code;
        this.date=date;

    }
    public Long getId_commande() { return id_commande; }

    public void setId_commande(Long id_commande) { this.id_commande = id_commande; }

    public String getLibele() {
        return libele;
    }

    public void setLibele(String libele) {
        this.libele = libele;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id_commande=" + id_commande +
                ", libele='" + libele + '\'' +
                ", code='" + code + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
