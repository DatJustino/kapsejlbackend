package com.example.kapsejlbackend.model;

public enum BaadType {
  STOERRE_END_40_FOD(" > 40'"),
  MINDRE_END_25_FOD(" < 40'"),
  MELLEM_25_OG_40_FOD(" > 25' og < 40'");

  private final String displayName;

  BaadType(String displayName) {
    this.displayName = displayName;
  }

  public String getDisplayName() {
    return displayName;
  }
}
