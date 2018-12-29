package de.unihamburg.informatik.nlp4web.finalproject.entrypoint;

import java.io.File;

import de.unihamburg.informatik.nlp4web.finalproject.datainput.DataUtils;

public class ExecuteBlogAuthorClassification {

	private static final String TRAIN_DATA_PATH = "src/main/resources/blog_entries/train_data_1000.csv";
	private static final String TEST_DATA_PATH = "src/main/resources/blog_entries/test_data_1000.csv";
	
	public static void main(String[] args) throws Exception {
		System.out.println("Hello machine learning with java...");
		
		File trainDataFile = new File( TRAIN_DATA_PATH );
		File testDataFile = new File( TEST_DATA_PATH );
		//sDataUtils.readData(TRAIN_DATA_PATH);
		
		
		
	}
}
