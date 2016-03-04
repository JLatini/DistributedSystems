package DistributedSystems;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.List;

/**
 * Generate a log file (You still need to create the folder or change it to an existing in the code)
 * 
 *
 */
public class Log {
	String fichier;

	public String getFichier() {
		return fichier;
	}

	public void setFichier(String fichier) {
		this.fichier = fichier;
	}

	public Log(String fichier) {
		super();
		this.fichier = fichier;
	}
	
	public static void WriteLog(String path, String data)
	{
		try {

			File file = new File(path);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(data + "\n");
			bw.close();


		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
