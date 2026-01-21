package com.petcode.dto;

public class DepartmentCreateDto {
  private String centerCode;
  private String code;
  private String name;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCenterCode() {
    return centerCode;
  }

  public void setCenterCode(String centerCode) {
    this.centerCode = centerCode;
  }

}
