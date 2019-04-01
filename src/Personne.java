public class Personne {

    private String adresse;
    private compteBancaire compte;
    private String nom;
    private boolean sexe;

    public Personne(String nom, boolean sexe, String adresse, compteBancaire compte)
    {
        setAdresse(adresse);
        setCompte(compte);
        setNom(nom);
        setSexe(sexe);
    }

    public String getAdresse()
    {
        return adresse;
    }

    public compteBancaire getCompte()
    {
        return compte;
    }

    public String getNom()
    {
        return nom;
    }

    public boolean getSexe()
    {
        return sexe;
    }

    public void setAdresse(String adresse)
    {
        this.adresse = adresse;
    }

    public void setCompte(compteBancaire compte)
    {
        this.compte = compte;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public void setSexe(boolean sexe)
    {
        this.sexe = sexe;
    }
}