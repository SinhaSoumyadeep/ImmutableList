package listadt;

import java.util.function.Function;

/**
 * This class is a concrete implementation of the ListADT interface which represents a generic list.
 * This class is used to make a list which is mutable. This class implements various methods to
 * provide operations on the list such as adding an element to the front, adding an element to the
 * back, adding an element to a particular index, removing the first instance of an object from the
 * list, getting the size of the list, getting the object at a particular index, a map of higher
 * order function that converts one list to another, a method that converts any list of type ListADT
 * to an Immutable list.We represent the type of data that this will work with a generic parameter
 * T. This is a placeholder for the actual data type.
 */
public class ListADTImpl<T> implements ListADT<T> {
  private GenericListADTNode<T> head;

  public ListADTImpl() {
    head = new GenericEmptyNode();
  }

  //a private constructor that is used internally (see map)
  private ListADTImpl(GenericListADTNode<T> head) {
    this.head = head;
  }

  /**
   * Add an object to the front of this list.
   *
   * @param b the object to be added to the front of this list
   */
  @Override
  public void addFront(T b) {
    head = head.addFront(b);
  }

  /**
   * Add an object to the back of this list (so it is the last object in the list.
   *
   * @param b the object to be added to the back of this list
   */
  @Override
  public void addBack(T b) {
    head = head.addBack(b);
  }

  /**
   * Add an object to this list so that it occupies the provided index. Index begins with 0.
   *
   * @param index the index to be occupied by this object, beginning at 0
   * @param b     the object to be added to the list
   */
  @Override
  public void add(int index, T b) {
    head = head.add(index, b);
  }

  /**
   * Return the number of objects currently in this list.
   *
   * @return the size of the list
   */
  @Override
  public int getSize() {
    return head.count();
  }

  /**
   * Remove the first instance of this object from this list.
   *
   * @param b the object to be removed
   */
  @Override
  public void remove(T b) {
    head = head.remove(b);
  }

  /**
   * Get the (index)th object in this list.
   *
   * @param index the index of the object to be returned
   * @return the object at the given index
   * @throws IllegalArgumentException if an invalid index is passed
   */
  @Override
  public T get(int index) throws IllegalArgumentException {
    if ((index >= 0) && (index < getSize())) {
      return head.get(index);
    } else {
      throw new IllegalArgumentException("Invalid index");
    }

  }

  /**
   * A general purpose map higher order function on this list, that returns the corresponding list
   * of type R.
   *
   * @param converter the function that converts T into R
   * @param <R>       the type of data in the resulting list
   * @return the resulting list that is identical in structure to this list, but has data of type R
   */
  @Override
  public <R> ListADT<R> map(Function<T, R> converter) {
    return new ListADTImpl(head.map(converter));
  }

  /**
   * This method is used to convert the mutable list to its counterpart immutable list.
   *
   * @return a immutable list.
   */
  @Override
  public ListADT<T> counterPartConverter() {
    return new ListImmutableImpl(this);
  }

  /**
   * This method is used to represents the object as String.
   */
  @Override
  public String toString() {
    return "(" + head.toString() + ")";
  }
}
