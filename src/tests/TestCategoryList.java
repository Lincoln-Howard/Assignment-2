package tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import io.JSON;
import model.CategoryList;
import model.FoodItem;
import model.LinkedList;

public class TestCategoryList {
  // the list
  CategoryList list = new CategoryList ();
  // test parse a json string
  @Test
  public void testParseStr () throws ClassCastException, NullPointerException, ParseException {
    list = CategoryList.parseJSON("[]");
    System.out.println (list.toJSONString());
  }
  @Test
  public void testAddCategory () {
    list.add ("Appetizers");
    System.out.println (list.toJSONString());
  }
  @Test
  public void testSearch () {
    list.add ("Appetizers");
    list.add ("Appetizers", new FoodItem ("Crab Dip", "$7.95", "Creamy and savory.",
        "1 cup", "700"));
    list.add ("Appetizers", new FoodItem ("Nachos", "$6.45", "Cheesy, spicy.. Nachos!",
        "20 chips", "650"));
    list.add ("Appetizers", new FoodItem ("Boneless Wings", "$8.45", "Classic buffo wings.",
        "8 wings", "400"));
    list.add ("Appetizers", new FoodItem ("Boneless Wings", "$10.45", "Classic buffo wings.",
        "12 wings", "400"));
    list.add ("Appetizers", new FoodItem ("Boneless Wings", "$12.45", "Classic buffo wings.",
        "20 wings", "400"));
    list.add ("Entrees");
    list.add ("Salads");
    list.add ("Deserts");
    list.add ("Combos");
    list.sort (LinkedList.DIRECTION.ASCENDING);
    assertEquals (0, list.byName ("Appetizers"));
    System.out.println ("Appetizers at: " + list.byName ("Appetizers"));
    assertEquals (-1, list.byName ("Sides"));
    System.out.println (list.toJSONString ());
  }
  // test the write to file
  @Test
  public void testWrite () throws FileNotFoundException {
    list.add ("Appetizers");
    list.add ("Appetizers", new FoodItem ("Crab Dip", "$7.95", "Creamy and savory.",
        "1 cup", "700"));
    list.add ("Appetizers", new FoodItem ("Nachos", "$6.45", "Cheesy, spicy.. Nachos!",
        "20 chips", "650"));
    list.add ("Appetizers", new FoodItem ("Boneless Wings", "$8.45", "Classic buffo wings.",
        "8 wings", "400"));
    list.add ("Appetizers", new FoodItem ("Boneless Wings", "$10.45", "Classic buffo wings.",
        "12 wings", "400"));
    list.add ("Appetizers", new FoodItem ("Boneless Wings", "$12.45", "Classic buffo wings.",
        "20 wings", "400"));
    list.add ("Entrees");
    list.add ("Salads");
    list.add ("Deserts");
    list.add ("Combos");
    list.sort (LinkedList.DIRECTION.ASCENDING);
    JSON.write ("menu.json", list);
  }
  //main
  public static void main(String[] args) {
    Result result = JUnitCore.runClasses(TestCategoryList.class);
    for (Failure failure : result.getFailures()) {
      System.out.println(failure.toString());
    }
    System.out.println(result.wasSuccessful());
  }
}