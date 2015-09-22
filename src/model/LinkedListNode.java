package model;

import java.io.Serializable;

/**
 * A linked list node implementation. Single link.
 * @author Lincoln Howard
 * @param <E> The type of data to link.
 */
@SuppressWarnings("serial")
public class LinkedListNode <E> implements Serializable {
  /**
   * The data contained by this node.
   */
  private E data;
  /**
   * The next node in the list.
   */
  private LinkedListNode <E> next;
  /**
   * Default constructor sets everything to null.
   */
  public LinkedListNode () {
    data = null;
    next = null;
  }
  /**
   * Data constructor sets data, but link to null.
   * @param data The initial data of the node.
   */
  public LinkedListNode (E data) {
    this.data = data;
    next = null;
  }
  /**
   * Full constructor sets everything.
   * @param data The initial data.
   * @param next The initial next node.
   */
  public LinkedListNode (E data, LinkedListNode <E> next) {
    this.data = data;
    this.next = next;
  }
  /**
   * Get the data.
   * @return The data in this node.
   */
  public E data () {
    return data;
  }
  /**
   * Set the data.
   * @param data New data for this node.
   */
  public void data (E data) {
    this.data = data;
  }
  /**
   * Get the next node.
   * @return The next node.
   */
  public LinkedListNode <E> next () {
    return next;
  }
  /**
   * Set the next node.
   * @param next The next node.
   */
  public void next (LinkedListNode <E> next) {
    this.next = next;
  }
}