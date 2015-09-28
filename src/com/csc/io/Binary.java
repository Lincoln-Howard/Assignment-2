package com.csc.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.csc.model.CategoryList;

public class Binary {
  
  public static void write (String path, CategoryList list) throws FileNotFoundException, IOException {
    ObjectOutputStream out = new ObjectOutputStream (new FileOutputStream (path));
    out.writeObject (list);
    out.close ();
  }
  
  public static CategoryList read (String path) throws FileNotFoundException, IOException, ClassNotFoundException {
    ObjectInputStream in = new ObjectInputStream (new FileInputStream (path));
    CategoryList ret = (CategoryList) in.readObject ();
    in.close ();
    return ret;
  }
}