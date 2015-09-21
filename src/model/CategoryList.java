package model;

import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@SuppressWarnings("serial")
public class CategoryList extends LinkedList <CategoryNode> {
  
  public static CategoryList parseJSON (JSONArray list) {
    CategoryList ret = new CategoryList ();
    for (@SuppressWarnings("rawtypes")
    Iterator iter = list.iterator (); iter.hasNext ();) {
      JSONObject obj = (JSONObject) iter.next ();
      CategoryNode node = new CategoryNode ((String) obj.get ("name"));
      JSONArray items = (JSONArray) obj.get ("data");
      for (@SuppressWarnings("rawtypes")
      Iterator sub = items.iterator (); sub.hasNext ();) {
        JSONObject item = (JSONObject) sub.next ();
        node.data ().add (new FoodItem ((String) item.get ("name"),
            (String) item.get ("price"), (String) item.get ("description"),
            (String) item.get ("size"), (long) item.get ("quantity")));
      }
      ret.add (node);
    }
    return ret;
  }
}