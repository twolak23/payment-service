package org.example.design_patterns.service.impl.discounter;

import org.example.design_patterns.service.discounter.Discounter;
/** The implamentation of Discounter interface used for Strategy design pattern
 *  This way of implementation of strategy is more verbose
 *  and takes a little bit more effort if we have to implement separate class for each strategy
 *
 *  To reduce code verbosity, use lambda expressions (
 *  */
public class ChristmasDiscounter implements Discounter {


  /** Apply a christmas discount of 15% on amount
   * @param amount an amount to apply a discount for
   * @return an amount after applying the discount
   * **/
  @Override
  public double applyDiscount(double amount) {
    return amount * 0.85;
  }
}
