package ua.edu.ucu.apps;

import ua.edu.ucu.apps.decorator.MockedDocument;
import ua.edu.ucu.apps.decorator.TimedDocument;

public class Main {
    public static void main(String[] args) {
        MockedDocument mockedDocument = new MockedDocument("path");

        TimedDocument timedDocument = new TimedDocument(mockedDocument);

        System.out.println(timedDocument.parse());
    }

}