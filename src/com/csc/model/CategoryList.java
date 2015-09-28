package com.csc.model;

import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 * A list of categories, which are lists of food items.
 * @author Lincoln Howard
 */
@SuppressWarnings("serial")
public class CategoryList extends LinkedList <CategoryNode> {
  /**
   * Parse the CategoryList object from a json array.
   * @param list The json array to parse.
   * @return The resulting CategoryList object.
   */
  public static CategoryList parseJSON (JSONArray list) {
    // the return object
    CategoryList ret = new CategoryList ();
    // if there are exceptions return an empty list
    try {
      // iterate over categories
      for (@SuppressWarnings("rawtypes")
      Iterator iter = list.iterator (); iter.hasNext ();) {
        // get the category as a json object
        JSONObject obj = (JSONObject) iter.next ();
        // create the category
        CategoryNode node = new CategoryNode ((String) obj.get ("name"));
        // get the items in the category
        JSONArray items = (JSONArray) obj.get ("data");
        // loop over sublist items
        for (@SuppressWarnings("rawtypes")
        Iterator sub = items.iterator (); sub.hasNext ();) {
          // get an individual item
          JSONObject item = (JSONObject) sub.next ();
          // add the food to the category
          node.data ().add (new FoodItem ((String) item.get ("name"),
              (String) item.get ("price"), (String) item.get ("description"),
              (String) item.get ("size"), (String) item.get ("quantity")));
        }
        // add the node to the category list
        ret.add (node);
      }
    } catch (Exception e) {
      // do nothing
    }
    // return the category list
    return ret;
  }
  /**
   * Parse a category list from a json string.
   * @param json The json text to parse.
   * @return The resulting CategoryList object.
   * @throws ParseException 
   */
  public static CategoryList parseJSON (String json) {
    CategoryList ret = new CategoryList ();
    // create the parser
    JSONParser parser = new JSONParser ();
    // parse text into a json array
    try{
      JSONArray list = (JSONArray) parser.parse (json);
      ret = CategoryList.parseJSON (list);
    } catch (Exception e) {
      // do nothing
    }
    // return using the other constructor
    return ret;
  }
  /**
   * Adds a node to the list by name.
   * @param node 
   * @return
   */
  public void add (String node) {
    // if the name exists, do nothing and return false
    if (byName (node) != -1) return;
    // otherwise add and return the result
    add (new CategoryNode (node));
  }
  /**
   * Adds a food item to the category with the provided name.
   * @param name The name of the category.
   * @param item The item to add.
   */
  public void add (String name, FoodItem item) {
    // get the index of the category
    int index = byName (name);
    // exit if not found
    if (index == -1) return;
    // get the category itself
    CategoryNode node = get (index);
    // add the food item
    node.data ().add (item);
  }
  /**
   * Get the category object from its name.
   * @param name The name to look for.
   * @return The location of the category with the provided name. -1 if not found.
   */
  public int byName (String name) {
    // count variable
    int count = 0;
    // iterate
    for (CategoryNode node : this)
      // if the name matches, return the count variable
      if (node.name ().equals (name))
        return count;
      // increment count
      else
        count++;
    // if not found, return -1
    return -1;
  }
  /**
   * Finds the item provided if it exists and returns its location.
   * @param item The item to look for.
   * @return Two integers. [0] is the index of the category, [1] the items index in the category. 
   */
  public int [] find (FoodItem item) {
    // create return array
    int [] pos = {-1, -1};
    // loop over categories
    for (int i = 0; i < size (); i++)
      // check
      if (get (i).data ().contains (item)) {
        // set locations
        pos [0] = i;
        pos [1] = this.get (i).data ().indexOf (item);
      }
    // return the position
    return pos;
  }
}