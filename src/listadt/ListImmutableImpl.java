package listadt;


import java.util.function.Function;

/**
 * This class is a concrete implementation of the ListADT interface which represents a generic list.
 * This class is used to make a list which is immutable. This class implements various methods to
 * provide operations on the list such as adding an element to the front, adding an element to the
 * back, adding an element to a particular index, removing the first instance of an object from the
 * list, getting the size of th elist, getting the object at a particular index, a map of higher
 * order function that converts one list to another, a method that converts any list of type ListADT
 * to an Immutable list.We represent the type of data that this will work with a generic parameter
 * T. This is a placeholder for the actual data type.
 */
public class ListImmutableImpl<T> implements ListADT<T> {

  private ListADT<T> list;


  /**
   * This constructor creates an object of Immutable list.
   *
   * @param mutableList the mutable list.
   * @throws IllegalArgumentException if the mutable list passed is null.
   */
  public ListImmutableImpl(ListADT<T> mutableList) throws IllegalArgumentException {

    if (mutableList == null) {
      throw new IllegalArgumentException("the list cannot be null");
    }

    this.list = mutableList.map(e -> e);
  }


  /**
   * Add an object to the front of this list. This method is not supported for Immutable list.
   *
   * @param b the object to be added to the front of this list
   */
  @Override
  public void addFront(T b) {

    throw new UnsupportedOperationException("Add Front is not supported");
  }

  /**
   * Add an object to the back of this list (so it is the last object in the list).This method is
   * not supported for Immutable list.
   *
   * @param b the object to be added to the back of this list
   */
  @Override
  public void addBack(T b) {
    throw new UnsupportedOperationException("Add Back is not Supported");
  }

  /**
   * Add an object to this list so that it occupies the provided index. This method is not supported
   * for Immutable list.
   *
   * @param index the index to be occupied by this object, beginning at 0
   * @param b     the object to be added to the list
   */
  @Override
  public void add(int index, T b) {
    throw new UnsupportedOperationException("Add is not Supported");
  }

  /**
   * Return the number of objects currently in this list.
   *
   * @return the size of the list
   */
  @Override
  public int getSize() {
    return this.list.getSize();
  }


  /**
   * Remove the first instance of this object from the list. This method is not supported for
   * Immutable list.
   *
   * @param b the object to be removed.
   */
  @Override
  public void remove(T b) {
    throw new UnsupportedOperationException("Remove is not allowed");
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
    return this.list.get(index);
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
    return new ListImmutableImpl<R>(list.map(converter));
  }

  /**
   * This method is used to convert the immutable list to its counterpart mutable list.
   *
   * @return a mutable list.
   */
  @Override
  public ListADT<T> counterPartConverter() {
    return this.list.map(e -> e);
  }

  /**
   * This method is used to represents the object as String.
   */
  @Override
  public String toString() {
    return list.toString();
  }


}
