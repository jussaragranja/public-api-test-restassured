package publicapi.test;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import publicapi.core.BaseTest;
import publicapi.model.UserModel;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;
import static publicapi.factory.UserFactory.getUser;
import static publicapi.factory.UserFactory.userNotExist;
import static publicapi.support.Constants.*;

/**
 * @author jussaragranja
 * Test Class
 */

public class DeleteUsersTest extends BaseTest {

    @Test
    public void return204_deleteUser(){

        List<UserModel> userModelList = getUser();

        given()
            .header(AUTHORIZATION, BEARER_TOKEN.concat(TOKEN))
            .contentType(ContentType.JSON)
            .pathParam(ID, userModelList.get(0).getId())
        .when()
            .delete(PATH_USERS_ID)
        .then()
            .statusCode(HttpStatus.SC_OK)
            .body(CODE, equalTo(HttpStatus.SC_NO_CONTENT));
    }

    @Test
    public void return204_deleteUserAssert(){

        List<UserModel> userModelList = getUser();
        int idUser = userModelList.get(0).getId();

        given()
            .header(AUTHORIZATION, BEARER_TOKEN.concat(TOKEN))
            .contentType(ContentType.JSON)
            .pathParam(ID, idUser)
        .when()
            .delete(PATH_USERS_ID)
        .then()
            .statusCode(HttpStatus.SC_OK)
            .body(CODE, equalTo(HttpStatus.SC_NO_CONTENT));

        assertTrue(userNotExist(idUser));
    }

    @Test
    public void return404_deleteUserNotExist(){

        given()
            .header(AUTHORIZATION, BEARER_TOKEN.concat(TOKEN))
            .contentType(ContentType.JSON)
            .pathParam(ID, INVALID_ID)
        .when()
            .delete(PATH_USERS_ID)
        .then()
            .body(CODE, equalTo(HttpStatus.SC_NOT_FOUND));
    }

}
