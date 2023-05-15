package Utile;

import java.io.*;
import java.util.*;

public class FonctionsUtiles {
	public static Properties ChargerProperties ()
    {
        File f = null;
        try
        {
            f = new File("Configuration.properties");
            f.createNewFile();
        }
        catch(IOException e)
        {
            System.out.println("Chargement du fichier de configuration - IOException : "+e+"\n");
        }

        Properties FichConf = new Properties();
        try
        {
            FichConf.load(new FileInputStream(f));
        }
        catch(FileNotFoundException e)
        {
             System.out.println("Chargement du fichier de configuration - FileNotFoundException : "+e+"\n");
        }
        catch(IOException e)
        {
            System.out.println("Chargement du fichier de configuration - IOException : "+e+"\n");
        }

        return FichConf;
    }
	
	public void EcritureFichier(String nomFichier,String line) throws IOException {
		FileWriter fw = new FileWriter (nomFichier, true);

        BufferedWriter bw = new BufferedWriter (fw);
        PrintWriter fichierSortie = new PrintWriter (bw); 
        fichierSortie.println (line); 
        fichierSortie.close(); 
	}
	
	
}
