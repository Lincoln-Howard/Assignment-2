package tests;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import model.LinkedList;
import static org.junit.Assert.*;
import java.util.List;

public class TestLinkedList {
  List <String> list = new LinkedList <String> ();
  // test the size method
  @Test
  public void testSize () {
    assertEquals (0, list.size ());
  }
  // test the add method, compare size of list
  @Test
  public void testAdd () {
    list.add ("Something");
    assertEquals (1, list.size ());
  }
  // test the get method, compare get 0
  @Test
  public void testGet () {
    list.add ("Something");
    assertEquals ("Something", list.get (0));
  }
  // test addAll
  @Test
  public void testAddAll () {
    List <String> toadd = new LinkedList <String> ();
    toadd.add ("Something");
    toadd.add ("Else");
    list.addAll (toadd);
  }
  // test toString of a,b,c
  @Test
  public void testToString () {
    list.add ("a");
    list.add ("b");
    list.add ("c");
    assertEquals ("[ a , b , c ]", list.toString ());
  }
  // test insert, compare to toString
  @Test
  public void testInsert () {
    list.add ("b");
    list.add ("c");
    list.add (0, "a");
    assertEquals ("[ a , b , c ]", list.toString ());
  }
  // main
  public static void main(String[] args) {
    Result result = JUnitCore.runClasses(TestLinkedList.class);
    for (Failure failure : result.getFailures()) {
       System.out.println(failure.toString());
    }
    System.out.println(result.wasSuccessful());
  }
}