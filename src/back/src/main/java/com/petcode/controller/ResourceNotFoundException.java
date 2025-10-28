package com.petcode.controller;

public class ResourceNotFoundException extends Exception {
  public ResourceNotFoundException(String errorMessage) {
    super(errorMessage);
  }
}
