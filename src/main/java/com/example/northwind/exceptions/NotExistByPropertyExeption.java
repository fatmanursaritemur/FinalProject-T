package com.example.northwind.exceptions;

public class NotExistByPropertyExeption extends Exception {

  public NotExistByPropertyExeption(String className, String propertyValue, String propertyName) {
    super(String.format("%s değerindeki %s özelliğine sahip %s bulunamadı", propertyValue, propertyName, className));
  }
}
