import ua.edu.ucu.apps.decorator.Document;
import ua.edu.ucu.apps.decorator.MockedDocument;
import ua.edu.ucu.apps.decorator.TimedDocument;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

class TimedDocumentTest {

    @Test
    void testParseTimeLogging() {
        String gcsPath = "gs://mocked-bucket/mock-file.jpg";
        Document mockedDocument = new MockedDocument(gcsPath);
        TimedDocument timedDocument = new TimedDocument(mockedDocument);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        String result = timedDocument.parse();
        System.setOut(originalOut);
        assertEquals("Mocked Document Parse", result);
        String consoleOutput = outputStream.toString().trim();
        assertTrue(consoleOutput.contains("Time:"));
    }

    @Test
    void testGetGcsPath() {
        String gcsPath = "gs://mocked-bucket/mock-file.jpg";
        Document mockedDocument = new MockedDocument(gcsPath);
        TimedDocument timedDocument = new TimedDocument(mockedDocument);
        String path = timedDocument.getGcsPath();
        assertEquals(gcsPath, path);
    }
}
