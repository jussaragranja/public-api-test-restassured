package publicapi.core;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import publicapi.support.Constants;

import static io.restassured.RestAssured.baseURI;

/**
 * @author jussaragranja
 * Base Class for Test Execution
 */

public class BaseTest {

    protected RequestSpecification requestSpec;

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = Constants.BASE_URI;
        requestSpec = RestAssured.given()
                .header(Constants.AUTHORIZATION, Constants.BEARER_TOKEN + Constants.TOKEN)
                .contentType("application/json");
    }

}
