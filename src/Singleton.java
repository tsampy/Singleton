/*
 * Pour interdire l'instanciation de la classe à l'aide de new,
 * il suffit de définir le constructeur comme privé
 * */

public class Singleton {

    // instance unique
    private static Singleton instance = new Singleton();

    // constructeur privé
    private Singleton()
    {}

    private static class SingletonHolder
    {
        private final static Singleton instance = new Singleton();
    }

    // méthode permettant de récupérer l'instance publique du Singleton
    public static Singleton getInstance()
    {
        return SingletonHolder.instance;
    }
}