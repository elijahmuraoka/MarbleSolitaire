package mocks;

import java.io.IOException;
import java.nio.CharBuffer;

/**
 * This class represents an invalid Readable object.
 */
public class InvalidReadable implements Readable {

  @Override
  public int read(CharBuffer cb) throws IOException {
    throw new IOException("Invalid Readable Error");
  }
}
