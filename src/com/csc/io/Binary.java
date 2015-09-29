package com.csc.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.csc.model.CategoryList;
/**
 * A utility for binary io of category lists.
 * Recommend using JSON for saving files if you can.
 * @author Lincoln Howard
 */
public class Binary {
  /**
   * Writes a category list to a file with a specified path.
   * @param path Destination of the file.
   * @param list The list to write to file.
   * @throws FileNotFoundException If the file can't be opened.
   * @throws IOException Something happened when trying to write the object.
   */
  public static void write (String path, CategoryList list) throws FileNotFoundException, IOException {
    ObjectOutputStream out = new ObjectOutputStream (new FileOutputStream (path));
    out.writeObject (list);
    out.close ();
  }
  /**
   * Reads a category list from file.
   * @param path Path to the file to read.
   * @return The category list in the file.
   * @throws FileNotFoundException If the file can't be opened.
   * @throws IOException If there is trouble reading the object.
   * @throws ClassNotFoundException If the classes don't line up.
   */
  public static CategoryList read (String path) throws FileNotFoundException, IOException, ClassNotFoundException {
    ObjectInputStream in = new ObjectInputStream (new FileInputStream (path));
    CategoryList ret = (CategoryList) in.readObject ();
    in.close ();
    return ret;
  }
}