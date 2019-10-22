package com.mimecast.converter;

import com.mimecast.converter.decoder.exception.ReadException;
import com.mimecast.manager.GameManager;
import com.mimecast.model.World;

public interface Decoder {
    public World decode() throws ReadException;
}
