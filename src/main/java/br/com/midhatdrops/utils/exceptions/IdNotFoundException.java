package br.com.midhatdrops.utils.exceptions;

public class IdNotFoundException extends Exception {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  public IdNotFoundException(String error) {
    super(error);
  }

}
