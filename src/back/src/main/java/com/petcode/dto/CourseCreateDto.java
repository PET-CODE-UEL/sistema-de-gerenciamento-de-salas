package com.petcode.dto;

public class CourseCreateDto {
  private String code;
  private String name;
  private String departmentCode;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getDepartmentCode() {
    return departmentCode;
  }

  public void setDepartmentCode(String departmentCode) {
    this.departmentCode = departmentCode;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
