package model;

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
    // return the category list
    return ret;
  }
  /**
   * Parse a category lsit from a json string.
   * @param json The json text to parse.
   * @return The resulting CategoryList object.
   * @throws ParseException 
   */
  public static CategoryList parseJSON (String json) throws ParseException {
    // create the return object
    CategoryList ret = new CategoryList ();
    // create the parser
    JSONParser parser = new JSONParser ();
    // parse text into a json array
    JSONArray list = (JSONArray) parser.parse (json);
    return ret;
  }
}