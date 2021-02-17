package com.example.northwind.exceptions;

public class CategoryOutOfBoundsException extends Exception {

  public CategoryOutOfBoundsException() {
    super("Kategoride maksimum ürün var");
  }
}
