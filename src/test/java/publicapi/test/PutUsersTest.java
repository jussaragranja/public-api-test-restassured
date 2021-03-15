package publicapi.test;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import publicapi.core.BaseTest;
import publicapi.model.UserModel;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static publicapi.factory.UserFactory.getUser;
import static publicapi.factory.UserFactory.validBodyEmail;
import static publicapi.support.Constants.*;
import static publicapi.support.Util.randomValue;

/**
 * @author jussaragranja
 * Test Class
 */

public class PutUsersTest extends BaseTest {

    public int random = randomValue(10000);

    @Test
    public void return200_updateUser(){

        List<UserModel> userModelList = getUser();

        given()
            .header(AUTHORIZATION, BEARER_TOKEN.concat(TOKEN))
            .contentType(ContentType.JSON)
            .pathParam(ID, userModelList.get(0).getId())
            .body(validBodyEmail(random).toString())
        .when()
            .put(PATH_USERS_ID)
        .then()
            .statusCode(HttpStatus.SC_OK)
            .body(CODE, equalTo(HttpStatus.SC_OK));
    }

    @Test
    public void return404_updateUserNotExist(){

        given()
            .header(AUTHORIZATION, BEARER_TOKEN.concat(TOKEN))
            .contentType(ContentType.JSON)
            .pathParam(ID, INVALID_ID)
            .body(validBodyEmail(random).toString())
        .when()
            .put(PATH_USERS_ID)
        .then()
            .body(CODE, equalTo(HttpStatus.SC_NOT_FOUND));
    }

}
