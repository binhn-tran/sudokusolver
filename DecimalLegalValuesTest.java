package edu.ics211.h09;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Represents a DecimalLegalValues.
 * Code from Professor Moore.
 * @author Cam Moore
 *
 */
class DecimalLegalValuesTest {

  @Test
  void test() {
    int[][] simple = { 
        { 7, 8, 3, 4, 9, 6, 1, 2, 5 }, 
        { 1, 2, 5, 7, 3, 8, 9, 6, 4 },
        { 4, 6, 9, 2, 5, 1, 7, 3, 8 },
        { 2, 3, 6, 1, 8, 5, 4, 7, 9 },
        { 5, 1, 4, 6, 7, 9, 3, 8, 2 },
        { 9, 7, 8, 3, 4, 2, 5, 1, 6 },
        { 8, 5, 7, 9, 6, 3, 2, 4, 1 },
        { 6, 4, 1, 5, 2, 7, 8, 9, 3 },
        { 3, 9, 2, 8, 1, 4, 6, 5, 7 } };
    ArrayList<Integer> legal = Sudoku.legalValues(simple, 0, 0);
    assertTrue(legal == null);
    simple[0][2] = 0;
    legal = Sudoku.legalValues(simple, 0, 2);
    assertEquals(1, legal.size());
    assertEquals(3, (int) legal.get(0));
    int[][] example2 = {
        { 0, 6, 0, 9, 0, 8, 0, 1, 0 },
        { 0, 0, 4, 0, 0, 0, 0, 0, 0 },
        { 8, 0, 3, 0, 0, 0, 4, 5, 0 },
        { 2, 0, 0, 0, 6, 0, 0, 0, 8 },
        { 9, 0, 0, 0, 0, 0, 0, 0, 4 },
        { 5, 0, 0, 0, 7, 0, 0, 0, 2 },
        { 0, 7, 8, 0, 0, 0, 9, 0, 5 },
        { 0, 0, 0, 0, 0, 0, 6, 0, 0 },
        { 0, 1, 0, 3, 0, 2, 0, 4, 0 } };
    legal = Sudoku.legalValues(example2, 0, 2);
    assertEquals(3, legal.size());
    assertEquals(2, (int) legal.get(0));
    assertEquals(5, (int) legal.get(1));
    assertEquals(7, (int) legal.get(2));
    int[][] example3 = {
        { 1, 0, 0, 0, 0, 7, 0, 9, 0 },
        { 0, 3, 0, 0, 2, 0, 0, 0, 8 },
        { 0, 0, 9, 6, 0, 0, 5, 0, 0 },
        { 0, 0, 5, 3, 0, 0, 9, 0, 0 },
        { 0, 1, 0, 0, 8, 0, 0, 0, 2 },
        { 6, 0, 0, 0, 0, 4, 0, 0, 0 },
        { 3, 0, 0, 0, 0, 0, 0, 1, 0 },
        { 0, 4, 0, 0, 0, 0, 0, 0, 7 },
        { 0, 0, 7, 0, 0, 0, 3, 0, 0 } };
    legal = Sudoku.legalValues(example3, 0, 2);
    assertEquals(4, legal.size());
    assertEquals(2, (int) legal.get(0));
    assertEquals(4, (int) legal.get(1));
    assertEquals(6, (int) legal.get(2));
    assertEquals(8, (int) legal.get(3));
  }

}
