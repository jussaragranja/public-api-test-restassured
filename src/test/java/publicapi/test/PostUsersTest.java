package publicapi.test;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import publicapi.core.BaseTest;
import publicapi.factory.UserFactory;
import publicapi.support.Constants;
import publicapi.support.UserUtils;
import publicapi.support.Util;

import static org.hamcrest.Matchers.equalTo;

/**
 * @author jussaragranja
 * Test Class
 */

public class PostUsersTest extends BaseTest {

    @Test
    @DisplayName("Deve criar novo usuário e retornar 201")
    void shouldCreateNewUserAndReturn201() {
        int random = Util.randomValue(10000);

        requestSpec
                .body(UserFactory.createValidUser(random).toString())
                .when()
                .post(Constants.PATH_USERS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(Constants.CODE, equalTo(HttpStatus.SC_CREATED));
    }

    @Test
    @DisplayName("Deve criar novo usuário e verificar existência")
    void shouldCreateNewUserAndAssertExistence() {
        int random = Util.randomValue(10000);

        int id = requestSpec
                .body(UserFactory.createValidUser(random).toString())
                .when()
                .post(Constants.PATH_USERS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(Constants.CODE, equalTo(HttpStatus.SC_CREATED))
                .extract().path("data.id");

        Assertions.assertTrue(UserUtils.userExists(requestSpec, id));
    }

    @Test
    @DisplayName("Deve retornar 422 ao criar usuário com body vazio")
    void shouldReturn422ForBlankBody() {
        requestSpec
                .body("{}")
                .when()
                .post(Constants.PATH_USERS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(Constants.CODE, equalTo(HttpStatus.SC_UNPROCESSABLE_ENTITY));
    }

    @Test
    @DisplayName("Deve retornar 422 ao criar usuário sem body")
    void shouldReturn422ForNullBody() {
        requestSpec
                .when()
                .post(Constants.PATH_USERS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(Constants.CODE, equalTo(HttpStatus.SC_UNPROCESSABLE_ENTITY));
    }
}
