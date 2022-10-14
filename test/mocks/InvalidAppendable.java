package mocks;

import java.io.IOException;

/**
 * This class represents an invalid Appendable object.
 */
public class InvalidAppendable implements Appendable {

  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException("Invalid Appendable Error");
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException("Invalid Appendable Error");
  }

  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException("Invalid Appendable Error");
  }
}
