package br.com.midhatdrops.utils.validations;

public class PageValidation implements Validation {
  private Integer page;

  public PageValidation(Integer page) {
    this.page = page;
  }

  @Override
  public boolean validate() {
    if (page == null) {
      return false;
    }
    return true;
  }

}
