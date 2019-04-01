public class threadRetrait implements Runnable {

    private compteBancaire _compteBancaire;
    private Thread _threadRetrait;

    public threadRetrait(compteBancaire _compteBancaire)
    {
        this._compteBancaire = _compteBancaire;
        _threadRetrait = new Thread(this);
        _threadRetrait.start();
    }

    public void doRetrait()
    {
        int montant;
        for ( int i = 1 ; i <= 10 ; i++ )
        {
            montant = ((int)(Math.random() * 2000));
            _compteBancaire.retrait(montant);
            System.out.print("retrait n°" + i + " de : " + montant + " € --- "
                    + "Solde du compte : " + _compteBancaire.getSolde() + " €\n\n");
        }
    }

    public void run()
    {
        doRetrait();
    }

    public boolean isAlive()
    {
        return _threadRetrait.isAlive();
    }
}