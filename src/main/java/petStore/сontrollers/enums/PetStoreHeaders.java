package petStore.сontrollers.enums;

import io.restassured.http.ContentType;

public enum PetStoreHeaders {

    KEY("api_key", "maryTest062019"),
    CONTENT_TYPE("Content-Type", ContentType.JSON.toString());

    private String headerName;
    private String headerValue;

    PetStoreHeaders(String headerName, String headerValue) {
        this.headerName = headerName;
        this.headerValue = headerValue;
    }

    public String getHeaderName() {
        return headerName;
    }

    public String getHeaderValue() {
        return headerValue;
    }
}
