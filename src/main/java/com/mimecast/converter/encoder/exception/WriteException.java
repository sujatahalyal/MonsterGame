package com.mimecast.converter.encoder.exception;

import java.io.IOException;

public class WriteException extends IOException {
    public WriteException(Throwable cause) {
        super(cause);
    }
}
