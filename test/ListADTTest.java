

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;


import listadt.ListADT;
import listadt.ListADTImpl;
import listadt.ListImmutableImpl;

import static org.junit.Assert.assertEquals;

/**
 * This Class is used to test the implementation of the Immutable List.
 */
public class ListADTTest {

  ListADT<Integer> mutable;
  ListADT<Integer> immutable;

  ListADT<String> mutableString;
  ListADT<String> immutableString;

  /**
   * This method is used to initialize the mutable and immutable list.
   */
  @Before
  public void setUp() {
    mutable = new ListADTImpl<Integer>();

    mutable.addBack(1);
    mutable.addBack(2);
    mutable.addBack(3);
    mutable.addBack(4);

    immutable = mutable.counterPartConverter();

    mutableString = new ListADTImpl<>();

    mutableString.addBack("Boston");
    mutableString.addBack("Paris");
    mutableString.addBack("New York");
    mutableString.addBack("Tokyo");
    mutableString.addBack("London");
    mutableString.addBack("Rome");

    immutableString = mutableString.counterPartConverter();

  }


  /**
   * This test case shows the usibility of the method.
   */
  @Test
  public void testUsabilityOfImmutableList() {

    ListADT<Integer> mutableListNew = new ListADTImpl<Integer>();
    mutableListNew.addBack(10);
    mutableListNew.addBack(20);
    mutableListNew.addBack(30);

    ListADT<Integer> immutableListNew = mutableListNew.counterPartConverter();

    // this tests the toString method.
    assertEquals("(10 20 30)", immutableListNew.toString());

    //This tests the addFront Method
    try {
      immutableListNew.addFront(5);
    } catch (UnsupportedOperationException uo) {
      assertEquals("Add Front is not supported", uo.getMessage());
      assertEquals("(10 20 30)", immutableListNew.toString());
      assertEquals(3, immutableListNew.getSize());
    }

    //This tests the addBack Method.
    try {
      immutableListNew.addBack(6);
    } catch (UnsupportedOperationException uo) {
      assertEquals("Add Back is not Supported", uo.getMessage());
      assertEquals("(10 20 30)", immutableListNew.toString());
      assertEquals(3, immutableListNew.getSize());
    }

    //This tests the add method.
    try {
      immutableListNew.add(3, 6);
    } catch (UnsupportedOperationException uo) {
      assertEquals("Add is not Supported", uo.getMessage());
      assertEquals("(10 20 30)", immutableListNew.toString());
      assertEquals(3, immutableListNew.getSize());
    }

    //This tests the remove method.
    try {
      immutableListNew.remove(30);
    } catch (UnsupportedOperationException uo) {
      assertEquals("Remove is not allowed", uo.getMessage());
      assertEquals("(10 20 30)", immutableListNew.toString());
      assertEquals(3, immutableListNew.getSize());
    }

    assertEquals(3, immutableListNew.getSize());

    //This tests the get method.

    assertEquals(new Integer(20), immutableListNew.get(1));

    //This tests the getSize method.
    assertEquals(3, immutableListNew.getSize());


    //This tests the map method.
    ListADT<Integer> mutableList2 = immutableListNew.map(e -> e + 10);

    assertEquals("(20 30 40)", mutableList2.toString());


  }


  /**
   * if the mutable list is changed then make sure that immutable list does not change.
   */
  @Test
  public void testWhenMutableIsChangedImmutableDoesnotChange() {
    ListADT<Integer> immutable1 = mutable.counterPartConverter();

    mutable.addFront(45);
    mutable.addFront(45);
    mutable.addFront(55);

    assertEquals("(1 2 3 4)", immutable1.toString());

  }


  /**
   * Test creation of the immutable list.
   */
  @Test
  public void testCreationOfImmutableList() {
    ListADT<Integer> immutable1 = mutable.counterPartConverter();

    assertEquals("(1 2 3 4)", immutable1.toString());

    ArrayList<Integer> checkList = new ArrayList<Integer>() {
      {
        add(1);
        add(2);
        add(3);
        add(4);
      }
    };

    for (int i = 0; i < immutable1.getSize(); i++) {
      assertEquals(checkList.get(i), immutable1.get(i));
    }

  }

  /**
   * test for mutable map.
   */
  @Test
  public void testIfMutableMapIsMutable() {
    ListADT<Integer> mutableMap = mutable.map(e -> e - 5);

    assertEquals("(-4 -3 -2 -1)", mutableMap.toString());

    mutableMap.addFront(20);
    mutableMap.addBack(20);
    mutableMap.add(3, 21);
    assertEquals("(20 -4 -3 21 -2 -1 20)", mutableMap.toString());
    mutableMap.remove(20);
    assertEquals("(-4 -3 21 -2 -1 20)", mutableMap.toString());

  }

  /**
   * test for null mutable list passed onto immutable list constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testWhenNullIsPassedToConstructor() {

    ListADT<Integer> immutableList2 = new ListImmutableImpl<Integer>(null);


  }

  /**
   * check when mutable list is empty and it is converted into immutable list.
   */
  @Test
  public void testImmutableListWhenMutableListIsEmpty() {

    ListADT<Integer> mutableList1 = new ListADTImpl<Integer>();
    ListADT<Integer> immutableList2 = mutableList1.counterPartConverter();

    assertEquals("()", immutableList2.toString());

    try {
      immutableList2.addFront(5);
    } catch (UnsupportedOperationException uo) {
      assertEquals("Add Front is not supported", uo.getMessage());
      assertEquals("()", immutableList2.toString());
    }

    try {
      immutableList2.addBack(6);
    } catch (UnsupportedOperationException uo) {
      assertEquals("Add Back is not Supported", uo.getMessage());
      assertEquals("()", immutableList2.toString());
    }

    try {
      immutableList2.add(3, 6);
    } catch (UnsupportedOperationException uo) {
      assertEquals("Add is not Supported", uo.getMessage());
      assertEquals("()", immutableList2.toString());
    }

    try {
      immutableList2.remove(3);
    } catch (UnsupportedOperationException uo) {
      assertEquals("Remove is not allowed", uo.getMessage());
      assertEquals("()", immutableList2.toString());
    }

    assertEquals(0, immutableList2.getSize());

    try {
      immutableList2.get(0);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid index", e.getMessage());
    }

    assertEquals(0, immutableList2.getSize());


    ListADT<Integer> mutableList2 = immutableList2.map(e -> e + 10);

    assertEquals("()", mutableList2.toString());

  }


  /**
   * This method tests the add functionality of the immutable class.
   */
  @Test
  public void testAddFrontMethodOfImmutable() {

    try {
      immutable.addFront(5);
    } catch (UnsupportedOperationException uo) {
      assertEquals("Add Front is not supported", uo.getMessage());
      assertEquals("(1 2 3 4)", immutable.toString());
    }

    try {
      immutableString.addFront("Mumbai");
    } catch (UnsupportedOperationException uo) {
      assertEquals("Add Front is not supported", uo.getMessage());
      assertEquals("(Boston Paris New York Tokyo London Rome)", immutableString.toString());
    }


  }

  /**
   * This method tests the add functionality of the immutable class.
   */
  @Test
  public void testAddBackMethodOfImmutable() {

    try {
      immutable.addBack(6);
    } catch (UnsupportedOperationException uo) {
      assertEquals("Add Back is not Supported", uo.getMessage());
      assertEquals("(1 2 3 4)", immutable.toString());
    }

    try {
      immutableString.addBack("Bangalore");
    } catch (UnsupportedOperationException uo) {
      assertEquals("Add Back is not Supported", uo.getMessage());
      assertEquals("(Boston Paris New York Tokyo London Rome)", immutableString.toString());
    }


  }

  /**
   * This method tests the add functionality of the immutable class.
   */
  @Test
  public void testAddMethodOfImmutable() {

    try {
      immutable.add(3, 6);
    } catch (UnsupportedOperationException uo) {
      assertEquals("Add is not Supported", uo.getMessage());
      assertEquals("(1 2 3 4)", immutable.toString());
    }
    try {
      immutableString.add(3, "Shanghai");
    } catch (UnsupportedOperationException uo) {
      assertEquals("Add is not Supported", uo.getMessage());
      assertEquals("(Boston Paris New York Tokyo London Rome)", immutableString.toString());
    }


  }

  /**
   * This method tests the add functionality of the immutable class.
   */
  @Test
  public void testRemoveMethodOfImmutable() {

    try {
      immutable.remove(3);
    } catch (UnsupportedOperationException uo) {
      assertEquals("Remove is not allowed", uo.getMessage());
      assertEquals("(1 2 3 4)", immutable.toString());
    }

    try {
      immutableString.remove("Boston");
    } catch (UnsupportedOperationException uo) {
      assertEquals("Remove is not allowed", uo.getMessage());
      assertEquals("(Boston Paris New York Tokyo London Rome)", immutableString.toString());
    }

  }


  /**
   * This method tests the add functionality of the immutable class.
   */
  @Test
  public void testGetSizeMethodOfImmutable() {

    assertEquals(4, immutable.getSize());
    assertEquals(6, immutableString.getSize());
  }

  /**
   * This method tests the add functionality of the immutable class.
   */
  @Test
  public void testGetMethodOfImmutable() {

    assertEquals(new Integer(1), immutable.get(0));
    assertEquals("Boston", immutableString.get(0));

    //test for invalid index.
    try {
      immutableString.get(10);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid index", e.getMessage());
    }

    //test for invalid index.
    try {
      immutableString.get(-10);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid index", e.getMessage());
    }


  }


  /**
   * This method tests the map of Immutable list.
   */
  @Test
  public void testMapImmutableFunction() {

    ListADT<Integer> multiplyByTenImmutable = immutable.map(e -> e * 10);

    assertEquals("(10 20 30 40)", multiplyByTenImmutable.toString());


    //the check for immutability of map.
    try {
      multiplyByTenImmutable.addFront(5);
    } catch (UnsupportedOperationException uo) {
      assertEquals("Add Front is not supported", uo.getMessage());
      assertEquals("(10 20 30 40)", multiplyByTenImmutable.toString());
    }

    try {
      multiplyByTenImmutable.addBack(6);
    } catch (UnsupportedOperationException uo) {
      assertEquals("Add Back is not Supported", uo.getMessage());
      assertEquals("(10 20 30 40)", multiplyByTenImmutable.toString());
    }

    try {
      multiplyByTenImmutable.add(3, 6);
    } catch (UnsupportedOperationException uo) {
      assertEquals("Add is not Supported", uo.getMessage());
      assertEquals("(10 20 30 40)", multiplyByTenImmutable.toString());
    }

    try {
      multiplyByTenImmutable.remove(3);
    } catch (UnsupportedOperationException uo) {
      assertEquals("Remove is not allowed", uo.getMessage());
      assertEquals("(10 20 30 40)", multiplyByTenImmutable.toString());
    }

    assertEquals(4, multiplyByTenImmutable.getSize());


    assertEquals(new Integer(10), multiplyByTenImmutable.get(0));


    ListADT<String> iLoveImmutable = immutableString.map(e -> "I Love " + e);
    assertEquals("(I Love Boston I Love Paris I Love New York I Love " +
            "Tokyo I Love London I Love Rome)", iLoveImmutable.toString());


  }


  /**
   * Tests convert from immutable to mutable.
   */
  @Test
  public void testcounterPartOfImmutableList() {
    ListADT<Integer> mutableListFromImmutable = immutable.counterPartConverter();
    assertEquals("(1 2 3 4)", mutableListFromImmutable.toString());
    mutableListFromImmutable.addFront(20);
    mutableListFromImmutable.addBack(20);
    mutableListFromImmutable.add(3, 21);
    assertEquals("(20 1 2 21 3 4 20)", mutableListFromImmutable.toString());
    mutableListFromImmutable.remove(20);
    assertEquals("(1 2 21 3 4 20)", mutableListFromImmutable.toString());
  }

  /**
   * This method tests mutable to immutable and from immutable to mutable.
   */
  @Test
  public void testRepetativeConversion() {
    ListADT<Integer> mutableConversion = new ListADTImpl<Integer>();
    mutableConversion.addBack(10);
    mutableConversion.addBack(9);
    mutableConversion.addBack(8);
    mutableConversion.addBack(7);
    mutableConversion.addBack(6);
    ListADT<Integer> immutableConversion = mutableConversion.counterPartConverter();

    try {
      immutableConversion.addFront(50);
    } catch (UnsupportedOperationException e) {
      assertEquals("Add Front is not supported", e.getMessage());
    }

    ListADT<Integer> mutableConversion2 = immutableConversion.counterPartConverter();

    mutableConversion2.addBack(60);
    mutableConversion2.addFront(70);
    mutableConversion2.addFront(80);

    assertEquals("(80 70 10 9 8 7 6 60)", mutableConversion2.toString());

  }


}