package com.codeforcommunity.dto.neighborhoods;

public class UserEmailRecord {
  private final String email;
  private final String name;

  public UserEmailRecord(
      String email,
      String name) {
    this.email = email;
    this.name = name;
  }

  public String getEmail() {
    return this.email;
  }

  public String getName() {
    return this.name;
  }
}
