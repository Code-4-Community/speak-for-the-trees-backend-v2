package com.codeforcommunity.dto.user;

import com.codeforcommunity.dto.ApiDto;
import com.codeforcommunity.exceptions.HandledException;
import java.util.ArrayList;
import java.util.List;

public class ChangeUsernameRequest extends ApiDto {

  private String newUsername;
  private String password;

  public ChangeUsernameRequest(String newUsername, String password) {
    this.newUsername = newUsername;
    this.password = password;
  }

  private ChangeUsernameRequest() {}

  public String getNewUsername() {
    return newUsername;
  }

  public void setNewUsername(String newUsername) {
    this.newUsername = newUsername;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public List<String> validateFields(String fieldPrefix) throws HandledException {
    String fieldName = fieldPrefix + "change_username_request.";
    List<String> fields = new ArrayList<>();

    if (isEmpty(newUsername) || newUsername.length() > 36) {
      fields.add(fieldName + "username");
    }
    if (password == null) {
      fields.add(fieldName + "password");
    }
    return fields;
  }
}
