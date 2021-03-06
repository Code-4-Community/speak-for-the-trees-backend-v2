package com.codeforcommunity.dto.auth;

import com.codeforcommunity.dto.ApiDto;
import com.codeforcommunity.exceptions.InvalidPasswordException;
import java.util.ArrayList;
import java.util.List;

public class NewUserRequest extends ApiDto {

  private String username;
  private String email;
  private String password;
  private String firstName;
  private String lastName;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public List<String> validateFields(String fieldPrefix) {
    String fieldName = fieldPrefix + "new_user_request.";
    List<String> fields = new ArrayList<>();

    if (isEmpty(username) || username.length() > 36) {
      fields.add(fieldName + "username");
    }
    if (email == null || emailInvalid(email)) {
      fields.add(fieldName + "email");
    }
    if (isEmpty(firstName) || firstName.length() > 36) {
      fields.add(fieldName + "first_name");
    }
    if (isEmpty(lastName) || lastName.length() > 36) {
      fields.add(fieldName + "last_name");
    }
    if (password == null) {
      fields.add(fieldName + "password");
    }
    // Only throw this exception if there are no issues with other fields
    if (passwordInvalid(password) && fields.size() == 0) {
      throw new InvalidPasswordException();
    }
    return fields;
  }
}
