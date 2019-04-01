// Singleton de log de la banque

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LogBanque {

    private String fileName;

    // constructeur privé
    private LogBanque(String fileName)
    {
        setFileName(fileName);
    }

    // holder
    private static class LogBanqueHolder
    {
        private final static LogBanque instance = new LogBanque("C:\\Java\\banque.log");
    }

    // affichage du fichier de log dans la console
    public void afficherLog()
    {
        String currentLine = "";

        try
        {
            // ouverture du fichier log
            FileReader fichier = new FileReader( fileName );
            BufferedReader reader = new BufferedReader( fichier );

            // lecture et affichage de toutes les lignes du fichier log
            while( ( currentLine = reader.readLine() ) != null)
            {
                System.out.println( currentLine );
            }

            reader.close();
        }
        // gestion des exceptions
        catch ( FileNotFoundException io404Error )
        {
            System.out.println( "Erreur 404 : le fichier " + fileName + " est introuvable." );
        }
        catch ( IOException ioError)
        {
            System.out.println( "Erreur : " + ioError.getMessage() );
        }
    }

    // affichage d'une fichier log dans la console
    public void afficherLog(String fileName)
    {
        setFileName(fileName);
        afficherLog();
    }

    // écriture d'un fichier log
    public void ecrireLog(String textToLog)
    {
        try ( FileWriter file = new FileWriter( fileName , true ) )
        {
            file.write( textToLog );
            file.flush();
        }
        catch ( IOException ioError )
        {
            ioError.printStackTrace();
        }
    }

    // écriture d'un fichier log
    public void ecrireLog(String fileName, String textToLog)
    {
        setFileName(fileName);
        ecrireLog(textToLog);
    }

    // retourne l'instance du singleton
    public static LogBanque getInstance()
    {
        return LogBanqueHolder.instance;
    }

    // définition du nom de fichier
    private void setFileName(String fileName)
    {
        this.fileName = fileName;
    }
}