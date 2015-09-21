package model;

import java.io.Serializable;

import org.json.simple.JSONAware;
/**
 * Defines a food item as a java object and json object. JSON form:<br>
 *<code>
 *{<br>
 *  <span title="Displayed name of the food object.">name</span>:
 *  
 *}
 *</code>
 * @author Lincoln Howard
 */
@SuppressWarnings("serial")
public class FoodItem implements JSONAware, Comparable <FoodItem>, Serializable {
  
  private String name;
  
  private String price;
  
  private String description;
  
  private String size;
  
  private long quantity;
  
  public FoodItem (String name, String price, String description, String size, long quantity) {
    this.name = name;
    this.price = price;
    this.description = description;
    this.size = size;
    this.quantity = quantity;
  }
  
  public String name () {
    return name;
  }
  
  public String price() {
    return price;
  }

  public String description() {
    return description;
  }

  public String size() {
    return size;
  }

  public long quantity() {
    return quantity;
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
    ret += "\"quantity\":" + quantity;
    return ret + "}";
  }
  
}