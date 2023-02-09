package com.codeforcommunity.dto.user;

import java.util.List;

public class GetChildUserResponse {
  public final List<UserDataResponse> childData;

  public GetChildUserResponse(List<UserDataResponse> childData) {
    this.childData = childData;
  }

  public List<UserDataResponse> getChildData() {
    return childData;
  }
}
