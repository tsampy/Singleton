public class threadDepot implements Runnable {

    private compteBancaire _compteBancaire;
    private Thread _threadDepot;

    public threadDepot(compteBancaire _compteBancaire)
    {
        this._compteBancaire = _compteBancaire;
        _threadDepot = new Thread(this);
        _threadDepot.start();
    }

    public void doDepot()
    {
        int montant;
        for ( int i = 1 ; i <= 10 ; i++ )
        {
            montant = ((int)(Math.random() * 2000));
            _compteBancaire.depot(montant);
            System.out.print("depot n°" + i + " de : " + montant + " € --- "
                    + "Solde du compte : " + _compteBancaire.getSolde() + " €\n");
        }
    }

    public void run()
    {
        doDepot();
    }

    public boolean isAlive()
    {
        return _threadDepot.isAlive();
    }
}