package de.unihamburg.informatik.nlp4web.finalproject.datainput;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class DataUtils {

	public static void readData( String dataPath ) {
		BufferedReader br = null;
		try {
			br = new BufferedReader( new InputStreamReader(new FileInputStream(dataPath)));
			String line = "";
			while( (line = br.readLine()) !=null  ) {
				System.out.println( line );
			}
		} catch ( IOException ex ) {
			
		}
	}
}
