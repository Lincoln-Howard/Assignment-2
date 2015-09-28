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
   * 
   * @param file
   * @param obj
   * @throws FileNotFoundException 
   */
  public static void write (String path, CategoryList list) throws FileNotFoundException {
    File file = new File (path);
    PrintWriter out = new PrintWriter (file);
    out.write (list.toJSONString ());
    out.close ();
  }
  /**
   * 
   * @param path
   * @return
   * @throws FileNotFoundException
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