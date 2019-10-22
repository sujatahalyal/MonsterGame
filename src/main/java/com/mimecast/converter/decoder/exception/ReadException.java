package com.mimecast.converter.decoder.exception;

import java.io.IOException;

public class ReadException extends IOException {
    public ReadException(Throwable cause) {
        super(cause);
    }
}
