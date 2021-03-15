package publicapi.core;

import org.junit.jupiter.api.BeforeEach;

import static io.restassured.RestAssured.baseURI;

/**
 * @author jussaragranja
 * Base Class for Test Execution
 */

public class BaseTest {

    @BeforeEach
    public void testeUri() {
        baseURI = "https://gorest.co.in/public-api";
    }

}
