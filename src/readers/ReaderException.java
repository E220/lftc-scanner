package readers;

import java.io.IOException;

public class ReaderException extends IOException {
    public ReaderException(String message) {
        super(message);
    }
}
