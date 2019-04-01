public class compteBancaire {

    private int numeroCompte;
    private int solde;
    private int limiteRetrait;

    private boolean flagCompteBancaire = true;

    private LogBanque logBanque = LogBanque.getInstance();

 // constructeur ----------------------------------------------------------------------------------
    public compteBancaire(int numeroCompte, int solde, int limiteRetrait)
    {
        setLimiteRetrait(limiteRetrait);
        setNumeroCompte(numeroCompte);
        setSolde(solde);
    }

 // accesseurs ------------------------------------------------------------------------------------
    private boolean getFlag()
    {
        return flagCompteBancaire;
    }

    public int getLimiteRetrait()
    {
        return limiteRetrait;
    }

    public LogBanque getLogBanque() {
        return logBanque;
    }

    public int getNumeroCompte()
    {
        return numeroCompte;
    }

    public int getSolde()
    {
        return solde;
    }

 // mutateurs -------------------------------------------------------------------------------------
    private void setFlagCompteBancaire(boolean flagCompteBancaire)
    {
        this.flagCompteBancaire = flagCompteBancaire;
    }

    public void setLimiteRetrait(int limiteRetrait)
    {
        this.limiteRetrait = limiteRetrait;
    }

    public void setNumeroCompte(int numeroCompte)
    {
        this.numeroCompte = numeroCompte;
    }

    public void setSolde(int solde)
    {
        this.solde = solde;
    }

 // méthodes --------------------------------------------------------------------------------------
    public synchronized void depot(int montant)
    {
        while(!getFlag())
        {
            try
            {
                wait();
                System.out.println("Opération de dépot (" + montant
                        + " €) en attente, le compte est déjà utilisé\n");
            }
            catch (InterruptedException e)
            {
                System.out.println("Thread interrupted");
            }
        }

        setFlagCompteBancaire(false);
        notify();

        setSolde( getSolde() + montant );
        logBanque.ecrireLog( "Depot d'un montant de " + Integer.toString(montant) +
                ", solde du compte : "+ Integer.toString(getSolde()) + " €\n");
    }

    public synchronized void retrait(int montant)
    {
        while(getFlag())
        {
            try
            {
                wait();
                System.out.println("Opération de retrait (" + montant
                        + " €) en attente, le compte est déjà utilisé\n");
            }
            catch (InterruptedException e)
            {
                System.out.println("Thread interrupted");
            }
        }

        setFlagCompteBancaire(true);
        notify();

        // retrait possible
        if (( montant <= getLimiteRetrait() ) && ( montant < getSolde() ))
        {
            setSolde( getSolde() - montant );
            logBanque.ecrireLog( "Retrait d'un montant de " + Integer.toString(montant) +
                    ", solde du compte : "+ Integer.toString(getSolde()) + " €\n");
        }
        else
            // retrait impossible
            // montant du retrait superieur a la limite autorisee
            if ( montant > getLimiteRetrait() )
            {
                System.out.println("Retrait impossible : Le montant demandé (" + montant
                        + " €) dépasse " + "la limite autorisée de " + getLimiteRetrait() + " €\n");
                logBanque.ecrireLog( "Retrait réfusé, le montant de " + Integer.toString(montant) +
                        " € dépasse la limite de " + getLimiteRetrait() +
                        " €, solde du compte : "+ Integer.toString(getSolde()) + " €\n");
            }
            // retrait impossible
            // montant du retrait superieur au solde du compte
            else
            {
                System.out.println("Retrait impossible : le montant demandé (" + montant
                        + " €) dépasse le solde de " + getSolde() + " €\n");
                logBanque.ecrireLog( "Retrait réfusé, le montant de " + Integer.toString(montant) +
                        " € dépasse le solde du compte : "+ Integer.toString(getSolde()) + " €\n");
            }
    }

    public void afficherLog()
    {
        logBanque.afficherLog();
    }

    public void ecrireLog(String textToLog)
    {
        logBanque.ecrireLog(textToLog);
    }
}