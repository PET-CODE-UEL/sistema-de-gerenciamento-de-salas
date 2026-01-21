package com.petcode.course;

public class CourseResponseDto {
  private String code;
  private String name;
  private String departmentCode;

  public CourseResponseDto(Course c) {
    this.code = c.getCode();
    if (c.getDepartment() != null) {
      this.departmentCode = c.getDepartment().getCode();
    }
    this.name = c.getName();
  }

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
