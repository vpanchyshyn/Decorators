import org.junit.jupiter.api.Test;

import ua.edu.ucu.apps.decorator.MockedDocument;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SmartMockedDocumentTest {

    @Test
    void testParseWithMockedDocument() {
        String gcsPath = "gs://mocked-bucket/mock-file.jpg";
        MockedDocument mockedDocument = new MockedDocument(gcsPath);
        String result = mockedDocument.parse();
        assertEquals("Mocked Document Parse", result);
    }
}
