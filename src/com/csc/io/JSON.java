package com.csc.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import com.csc.model.CategoryList;

/**
 * Utility for writing and reading json objects in files.
 * @author Lincoln Howard
 */
public class JSON {
  /**
   * Write a category list to file.
   * @param path The destination of the file.
   * @param list The list to write to file.
   * @throws FileNotFoundException If there is an issue opening the file.
   */
  public static void write (String path, CategoryList list) throws FileNotFoundException {
    File file = new File (path);
    PrintWriter out = new PrintWriter (file);
    out.write (list.toJSONString ());
    out.close ();
  }
  /**
   * Reads a category list from specified file.
   * @param path The location of the file to read.
   * @return A CategoryList created from the file. Returns an empty list
   * if a parse exception is found.
   * @throws FileNotFoundException If the file can't be opened.
   */
  public static CategoryList read (String path) throws FileNotFoundException {
    File file = new File (path);
    Scanner in = new Scanner (file);
    String str = "";
    while (in.hasNextLine ())
      str += in.nextLine ();
    in.close ();
    return CategoryList.parseJSON (str);
  }
}