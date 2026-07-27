package org.example.design_patterns.service.discounter;

/** Interface which applies Strategy design pattern  */
public interface Discounter {
  double applyDiscount(double amount);


  // here are the less verbose, more compact implementations for Christmas and Easter
  static Discounter ChristmasDiscounter() {
    return amount -> amount * 0.8;
  }

  static Discounter EasterDiscounter() {
    return amount -> amount * 0.5;
  }
}
