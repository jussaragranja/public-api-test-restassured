package publicapi.test;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import publicapi.core.BaseTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;
import static publicapi.factory.UserFactory.userExist;
import static publicapi.factory.UserFactory.validBody;
import static publicapi.support.Constants.*;
import static publicapi.support.Util.randomValue;

/**
 * @author jussaragranja
 * Test Class
 */

public class PostUsersTest extends BaseTest {

    public int random = randomValue(10000);

    @Test
    public void return201_createNewUser(){

        given()
            .header(AUTHORIZATION, BEARER_TOKEN.concat(TOKEN))
            .contentType(ContentType.JSON)
            .body(validBody(random).toString())
        .when()
            .post(PATH_USERS)
        .then()
            .statusCode(HttpStatus.SC_OK)
            .body(CODE, equalTo(HttpStatus.SC_CREATED));

    }

    @Test
    public void return201_createNewUserAssert(){

        int id = given()
            .header(AUTHORIZATION, BEARER_TOKEN.concat(TOKEN))
            .contentType(ContentType.JSON)
            .body(validBody(random).toString())
        .when()
            .post(PATH_USERS)
        .then()
            .statusCode(HttpStatus.SC_OK)
            .body(CODE, equalTo(HttpStatus.SC_CREATED)).extract().path("data.id");

        assertTrue(userExist(id));
    }

    @Test
    public void return422_createNewUserBlank(){

        given()
            .header(AUTHORIZATION, BEARER_TOKEN.concat(TOKEN))
            .contentType(ContentType.JSON)
            .body("{}")
        .when()
            .post(PATH_USERS)
        .then()
            .body(CODE, equalTo(HttpStatus.SC_UNPROCESSABLE_ENTITY));
    }

    @Test
    public void return422_createNewUserNull(){

        given()
            .header(AUTHORIZATION, BEARER_TOKEN.concat(TOKEN))
            .contentType(ContentType.JSON)
        .when()
            .post(PATH_USERS)
        .then()
            .body(CODE, equalTo(HttpStatus.SC_UNPROCESSABLE_ENTITY));
    }

}
