package io.plugbox.dummy.constants;

public class Constants {
    public static final String HEALTH_ENDPOINT_URL = "/health";
    public static final String APP_VERSION_URL = "/api/version";
    public static final String POST_BOOK_REQUEST = "/api/books";
    public static final String BOOK_REQUEST_WITH_ID_URL = POST_BOOK_REQUEST + "/{id}";

    public static final String BOOK_ID_KEY = "book_id";
    private Constants() { /* empty */}
}