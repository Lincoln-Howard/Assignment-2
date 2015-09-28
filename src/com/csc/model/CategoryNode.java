package com.csc.model;
import java.io.Serializable;

import org.json.simple.JSONAware;
/**
 * A category node has a name and a list of food items in the category.
 * @author Lincoln Howard
 */
@SuppressWarnings("serial")
public class CategoryNode extends LinkedListNode <LinkedList <FoodItem>> implements JSONAware, Serializable, Comparable <CategoryNode> {
  /**
   * The category name.
   */
  private String name;
  /**
   * All constructors require a name.
   * @param name The name of this category. 
   */
  public CategoryNode (String name) {
    super (new LinkedList <FoodItem> ());
    this.name = name;
  }
  /**
   * Create a node with everything but the next defined.
   * @param name The name of the node.
   * @param data The next node in the list.
   */
  public CategoryNode (String name, LinkedList <FoodItem> data) {
    super (data);
    this.name = name;
  }
  /**
   * Sets all of the fields in the category.
   * @param name The name of this category.
   * @param data The data in this category.
   * @param next The next category.
   */
  public CategoryNode (String name, LinkedList <FoodItem> data, CategoryNode next) {
    super (data, next);
    this.name = name;
  }
  /**
   * Get the name of this category.
   * @return The name of this category.
   */
  public String name () {
    return name;
  }
  /**
   * Set the name.
   * @param name The new name.
   */
  public void name (String name) {
    this.name = name;
  }
  @Override
  public int compareTo(CategoryNode o) {
    return name.toLowerCase ().compareTo (o.name ().toLowerCase ());
  }
  @Override
  public String toJSONString() {
    String ret = "{";
    ret += "\"name\":\"" + name + "\"," + "\"data\":" + data().toJSONString ();
    return ret + "}";
  }
  @Override
  public String toString () {
    return name;
  }
}