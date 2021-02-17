package com.example.northwind.exceptions;

public class DeletingErrorByRelationException extends Exception {

  public DeletingErrorByRelationException(String className, int id) {

    super(String.format("%d'li  %s sınıfına ait obje order_details tablosuyla ilişkili", id, className));
  }
}
