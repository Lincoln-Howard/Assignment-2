package com.csc.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.json.simple.JSONAware;
/**
 * A linked list implementation.
 * @author Lincoln Howard
 * @param <E> Type of list. E must be comparable to other E objects.
 */
@SuppressWarnings("serial")
public class LinkedList <E extends Comparable <E>> implements JSONAware, java.util.List <E>, Serializable {
  /**
   * Orders allowed for sorting.
   * @author Lincoln Howard
   */
  public enum DIRECTION {
    /**
     * Ascending order.
     */
    ASCENDING,
    /**
     * Descending order.
     */
    DESCENDING,
    /**
     * Unsorted order.
     */
    UNSORTED;
  }
  /**
   * The state of sorted direction.
   */
  private DIRECTION state;
  /**
   * The head of the list.
   */
  private LinkedListNode <E> head;
  /**
   * Default constructor initializes head to null.
   * @param head
   */
  public LinkedList () {
    state = DIRECTION.UNSORTED;
    this.head = null;
  }
  @Override
  public boolean add(E e) {
    if (head == null) {
      head = new LinkedListNode <E> (e);
      return true;
    }
    // clone the head of the list
    LinkedListNode <E> end = head;
    // go to the end of the list
    while (end.next () != null) {
      end = end.next ();
    }
    // append to the list
    end.next (new LinkedListNode <E> (e));
    // list was changed
    return true;
  }
  @Override
  public void add(int index, E element) {
    // index must be non-negative
    if (index < 0) throw new IndexOutOfBoundsException ("Non negative index required.");
    if (index == 0) {
      // insert at zero 
      head = new LinkedListNode <E> (element, head);
      return;
    }
    LinkedListNode <E> end = head;
    for (int i = 0; i < index; i++) {
      end = end.next ();
      if (end == null)
        throw new IndexOutOfBoundsException ("Reached end of list.");
    }
    end.next (new LinkedListNode <E> (element, end.next ()));
  }
  @Override
  public boolean addAll(Collection<? extends E> c) {
    // less efficient but neater - O(n^2)
    for (E e : c) {
      // add method O(n)
      add (e);
    }
    return true;
  }

  @Override
  public boolean addAll(int index, Collection<? extends E> c) {
    // refer to addAll without index for efficiency comments
    for (E e : c) {
      add (index, e);
      index++;
    }
    return true;
  }
  @Override
  public void clear() {
    // reset the linked list by making head null
    head = null;
  }
  @Override
  public boolean contains(Object o) {
    return indexOf (o) != -1;
  }
  // if N is the size of this and M the size of 'c'
  // then the efficiency of this method is
  // O (M*N)
  @Override
  public boolean containsAll(Collection<?> c) {
    // neater code using an iterator
    for (Object o : c)
      if (!contains (o))
        // if we can't find one, not all are contained
        return false;
    // all found
    return true;
  }
  @Override
  public E get(int index) {
    // clone the head
    LinkedListNode <E> end = head;
    // loop until we find the index
    for (int i = 0; i < index; i++) {
      end = end.next ();
      // if we reached the end before the index
      if (end == null)
        // throw the exception
        throw new IndexOutOfBoundsException ("Reached end of list.");
    }
    return end.data ();
  }
  @Override
  public int indexOf(Object o) {
    int count = 0;
    for (E data : this) {
      if (data.equals (o))
        return count;
      count++;
    }
    return -1;
  }
  @Override
  public boolean isEmpty () {
    return head == null;
  }
  @Override
  public Iterator<E> iterator() {
    return new Iterator <E> () {
      int i = 0, size = size ();
      @Override
      public boolean hasNext() {
        return i < size;
      }
      @Override
      public E next() {
        i++;
        return get (i - 1);
      }
    };
  }
  @Override
  public int lastIndexOf(Object o) {
    int i = 0, last = -1;
    LinkedListNode <E> node = head;
    while (node.next () != null) {
      if (node.data ().equals (o))
        last = i;
      node = node.next ();
      i++;
    }
    return last;
  }
  @Override
  public ListIterator<E> listIterator() {
    return new ListIterator <E> () {
      int i = 0;
      @Override
      public void add(E arg0) {
        LinkedList.this.add (i , arg0);
      }
      @Override
      public boolean hasNext() {
        return i < size ();
      }
      @Override
      public boolean hasPrevious() {
        return i > 0;
      }
      @Override
      public E next() {
        i++;
        return get (i - 1);
      }
      @Override
      public int nextIndex() {
        return i + 1;
      }
      @Override
      public E previous() {
        i--;
        return get (i + 1);
      }
      @Override
      public int previousIndex() {
        return i - 1;
      }
      @Override
      public void remove() {
        LinkedList.this.remove (nextIndex ());
      }
      @Override
      public void set(E arg0) {
        LinkedList.this.set (nextIndex (), arg0);
      }      
    };
  }
  @Override
  public ListIterator<E> listIterator(int index) {
    return new ListIterator <E> () {
      int size = size (), i = index;
      @Override
      public void add(E arg0) {
        LinkedList.this.add (i, arg0);
        size++;
      }
      @Override
      public boolean hasNext() {
        return i < size;
      }
      @Override
      public boolean hasPrevious() {
        return i > 0;
      }
      @Override
      public E next() {
        i++;
        return LinkedList.this.get (i);
      }
      @Override
      public int nextIndex() {
        return i + 1;
      }
      @Override
      public E previous() {
        i--;
        return get (i + 1);
      }
      @Override
      public int previousIndex() {
        return i;
      }
      @Override
      public void remove() {
        LinkedList.this.remove (i + 1);
      }

      @Override
      public void set(E arg0) {
        LinkedList.this.set (i + 1, arg0);
      }
    };
  }
  @Override
  public boolean remove(Object o) {
    // get the first occurrence
    int index = indexOf (o);
    // if nothing is removed, return false
    if (index == -1) return false;
    // if -1, its not there
    while (index != -1) {
      // remove based on index
      remove (index);
      // get the first occurrence
      index = indexOf (o);
    }
    // something got removed, return true
    return true;
  }
  @Override
  public E remove(int index) {
    int size = size ();
    E ret = null;
    if (index == 0) {
      ret = head.data ();
      head = head.next ();
    } else if (index == size - 1) {
      ret = get (index);
      LinkedListNode <E> temp = head;
      for (int i = 0; i < index - 1; i++) {
        temp = temp.next ();
      }
      temp.next (null);
    } else if (index < size) {
      ret = get (index);
      LinkedListNode <E> temp = head;
      for (int i = 0; i < index - 1; i++) {
        temp = temp.next ();
      }
      temp.next (temp.next ().next ());
    } else {
      throw new IndexOutOfBoundsException ("Index provided" + index + "Size of list: " + size);
    }
    return ret;
  }
  @Override
  public boolean removeAll(Collection<?> c) {
    boolean ret = true;
    // neat code, inefficient
    for (Object e : c)
      if (ret)
        ret = remove (e);
      else
        remove (e);
    return ret;
  }
  @Override
  public boolean retainAll (Collection<?> c) {
    for (E e : this) {
      if (!c.contains (e))
        remove (e);
    }
    return true;
  }
  @Override
  public E set (int index, E element) {
    add (index, element);
    return remove (index + 1);
  }
  @Override
  public int size() {
    if (isEmpty ()) return 0;
    int count = 1;
    LinkedListNode <E> clone = head;
    while (clone.next () != null) {
      count++;
      clone = clone.next ();
    }
    return count;
  }
  @Override
  public List<E> subList(int fromIndex, int toIndex) {
    LinkedList <E> ret = new LinkedList <E> ();
    for (int i = fromIndex; i < toIndex; i++)
      ret.add (get (i));
    return ret;
  }
  @Override
  public Object [] toArray() {
    int size = size ();
    Object [] arr = new Object [size];
    LinkedListNode <E> node = head;
    for (int i = 0; i < size; i++) {
      arr [i] = node.data ();
      node = node.next ();
    }
    return arr;
  }
  @SuppressWarnings("unchecked")
  @Override
  public <T> T[] toArray(T[] a) {
    return (T[]) toArray ();
  }
  /**
   * Sorts the list based on direction.
   * @param direction The direction to sort in.
   */
  public void sort (DIRECTION direction) {
    // don't do anything if you don't have to
    if (direction == this.state) return;
    // set the state
    state = direction;
    // don't do anything if unsorted
    if (direction == DIRECTION.UNSORTED) return;
    // necessary variables
    LinkedList <E> list = new LinkedList <E> ();
    int min = 0;
    // sort ascending
    if (direction == DIRECTION.ASCENDING) {
      // loop until all are removed
      while (!isEmpty ()) {
        // reset the min
        min = 0;
        // loop over all others
        for (int i = 1; i < size (); i++)
          // compare
          if (get (i).compareTo (get (min)) < 0)
            // set min
            min = i;
        // remove from this and add to the new list
        list.add (remove (min));
      }
    }
    // sort descending
    else if (direction == DIRECTION.DESCENDING) {
      // loop until all are removed
      while (!isEmpty ()) {
        // reset the min
        min = 0;
        // loop over all others
        for (int i = 1; i < size (); i++)
          // compare
          if (get (min).compareTo (get (i)) < 0)
            // set min
            min = i;
        // remove from this and add to the new list
        list.add (remove (min));
      }
    }
    addAll (list);
  }
  /**
   * Get the state of the sorting of this list.
   * @return The state of the sorting of the list.
   */
  public DIRECTION state () {
    return state;
  }
  @Override
  public String toString () {
    String ret = "[";
    for (ListIterator<E> iterator = listIterator(); iterator.hasNext();) {
      ret += " " + iterator.next () + " ";
      if (iterator.hasNext ())
        ret += ",";
    }
    ret += "]";
    return ret;
  }
  @Override
  public String toJSONString() {
    String ret = "[";
    for (ListIterator <E> iterator = listIterator (); iterator.hasNext ();) {
      E next = iterator.next ();
      try {
        JSONAware aware = (JSONAware) next;
        ret += aware.toJSONString ();
      } catch (Exception e) {
        ret += "\"" + next.toString () + "\"";
      }
      if (iterator.hasNext ())
        ret += ",";
    }
    return ret + "]";
  }
}