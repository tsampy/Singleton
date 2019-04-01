public class testBanque {

    private static Personne user;

    public static void main(String[] args)
    {
        compteBancaire compte = new compteBancaire(1, 230, 1500);
        user = new Personne("leNom", true, "leAdresse", compte);

        compte.getLogBanque().ecrireLog( "Solde du compte de " + user.getNom() +
                " résidant " + user.getAdresse() + " : " +
                Integer.toString(compte.getSolde()) + " €\n");

        System.out.println("Solde du compte de " + user.getNom()
                            + " au démarrage : " + compte.getSolde() + " €");
        System.out.println("Limite de retrait : " + compte.getLimiteRetrait() + " €\n");

        threadDepot _threadDepot = new threadDepot(compte);
        threadRetrait _threadRetrait = new threadRetrait(compte);

        System.out.println("Solde du compte de " + user.getNom()
                            + " à la fin : " + compte.getSolde() + " €");

        while ((_threadDepot.isAlive()) || (_threadRetrait.isAlive())) {}

        compte.ecrireLog("--------------------------------------------------------------\n");

        System.out.println("--------------------------------------------------------------");
        System.out.println("Log de la banque :");
        compte.afficherLog();
    }
}