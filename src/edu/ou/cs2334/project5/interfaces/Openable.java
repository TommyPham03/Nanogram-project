package edu.ou.cs2334.project5.interfaces;
import java.io.File;
import java.io.IOException;

/**
 * 
 * @author Tommy Pham
 * This class is a simple interface used to handle opening a file
 */
public interface Openable {
	
	/**
	 * interface method to open the file
	 * 
	 * @param file the given file 
	 * @throws IOException throw an exception for something that cant be read in or read out
	 */
	void open(File file) throws IOException;
}