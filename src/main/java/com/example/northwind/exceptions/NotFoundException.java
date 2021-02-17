package com.example.northwind.exceptions;

public class NotFoundException extends Exception {

  public NotFoundException(String className, String id) {
    super(String.format("%s'li %s bulunamadı", id, className));
  }
}