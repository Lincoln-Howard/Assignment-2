package model;

import java.io.Serializable;

import org.json.simple.JSONAware;
/**
 * Defines a food item as a java object and json object.
 * 
 * @author Lincoln Howard
 */
@SuppressWarnings("serial")
public class FoodItem implements JSONAware, Comparable <FoodItem>, Serializable {
  /**
   * The name of the food item.
   */
  private String name;
  /**
   * The price of the food item.
   */
  private String price;
  /**
   * A description of the food item.
   */
  private String description;
  /**
   * The size of the food item.
   */
  private String size;
  /**
   * The quantity of the food item.
   */
  private String quantity;
  /**
   * The constructor requires all fields.
   * @param name The name.
   * @param price The price.
   * @param description The description.
   * @param size The size.
   * @param quantity The quantity.
   */
  public FoodItem (String name, String price, String description, String size, String quantity) {
    this.name = name;
    this.price = price;
    this.description = description;
    this.size = size;
    this.quantity = quantity;
  }
  /**
   * Get name.
   * @return The name.
   */
  public String name () {
    return name;
  }
  /**
   * Set name.
   * @param name The new name.
   */
  public void name (String name) {
    this.name = name;
  }
  /**
   * Get price.
   * @return The price.
   */
  public String price() {
    return price;
  }
  /**
   * Set price.
   * @param price The new price.
   */
  public void price (String price) {
    this.price = price;
  }
  /**
   * Get description.
   * @return The description.
   */
  public String description() {
    return description;
  }
  /**
   * Set description.
   * @param description The new description.
   */
  public void description (String description) {
    this.description = description;
  }
  /**
   * Get size.
   * @return The size.
   */
  public String size() {
    return size;
  }
  /**
   * Set size.
   * @param size The new size.
   */
  public void size (String size) {
    this.size = size;
  }
  /**
   * Get quantity
   * @return The quantity.
   */
  public String quantity() {
    return quantity;
  }
  /**
   * Set quantity.
   * @param quantity The new quantity.
   */
  public void quantity (String quantity) {
    this.quantity = quantity;
  }
  @Override
  public int compareTo(FoodItem arg0) {
    return name.toLowerCase ().compareTo (arg0.name ().toLowerCase ());
  }

  @Override
  public String toJSONString() {
    String ret = "{";
    ret += "\"name\":\"" + name + "\",";
    ret += "\"price\":\"" + price + "\",";
    ret += "\"description\":\"" + description + "\",";
    ret += "\"size\":\"" + size + "\",";
    ret += "\"quantity\":\"" + quantity + "\"";
    return ret + "}";
  }
  
}