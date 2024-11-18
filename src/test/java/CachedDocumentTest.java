import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ua.edu.ucu.apps.decorator.CachedDocument;
import ua.edu.ucu.apps.decorator.DBConnection;
import ua.edu.ucu.apps.decorator.Document;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CachedDocumentTest {

    private DBConnection dbConnectionMock;
    private Document mockDocument;

    @BeforeEach
    void setUp() {
        dbConnectionMock = mock(DBConnection.class);
        mockDocument = mock(Document.class);
        DBConnection.dbConnection = dbConnectionMock;
    }

    @Test
    void testParseWithCache() {
        when(mockDocument.getGcsPath()).thenReturn("mock/path");
        when(dbConnectionMock.getDocument("mock/path")).thenReturn("Cached Content");

        CachedDocument cachedDocument = new CachedDocument(mockDocument);
        String result = cachedDocument.parse();

        assertEquals("Cached Content", result);
        verify(dbConnectionMock, never()).createDocument(anyString(), anyString());
    }

    @Test
    void testParseWithoutCache() {
        when(mockDocument.getGcsPath()).thenReturn("mock/path");
        when(dbConnectionMock.getDocument("mock/path")).thenReturn(null);
        when(mockDocument.parse()).thenReturn("Parsed Content");

        CachedDocument cachedDocument = new CachedDocument(mockDocument);
        String result = cachedDocument.parse();

        assertEquals("Parsed Content", result);
        verify(dbConnectionMock).createDocument("mock/path", "Parsed Content");
    }
}
