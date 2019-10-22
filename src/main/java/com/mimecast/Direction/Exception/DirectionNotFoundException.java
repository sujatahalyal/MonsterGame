package com.mimecast.Direction.Exception;

import java.security.InvalidKeyException;

public class DirectionNotFoundException extends InvalidKeyException {

  public DirectionNotFoundException(String message){
      super(message);
  }
}
