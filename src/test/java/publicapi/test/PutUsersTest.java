package publicapi.test;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import publicapi.core.BaseTest;
import publicapi.factory.UserFactory;
import publicapi.model.UserModel;
import publicapi.support.Constants;
import publicapi.support.UserUtils;
import publicapi.support.Util;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;

/**
 * @author jussaragranja
 * Test Class
 */

public class PutUsersTest extends BaseTest {

    @Test
    @DisplayName("Deve atualizar usuário existente e retornar 200")
    void shouldUpdateUserAndReturn200() {
        int random = Util.randomValue(10000);
        List<UserModel> users = UserUtils.getUsers(requestSpec);

        requestSpec
                .pathParam(Constants.ID, users.get(0).getId())
                .body(UserFactory.createValidEmail(random).toString())
                .when()
                .put(Constants.PATH_USERS_ID)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(Constants.CODE, equalTo(HttpStatus.SC_OK));
    }

    @Test
    @DisplayName("Deve retornar 404 ao tentar atualizar usuário inexistente")
    void shouldReturn404ForNonExistentUser() {
        int random = Util.randomValue(10000);

        requestSpec
                .pathParam(Constants.ID, Constants.INVALID_ID)
                .body(UserFactory.createValidEmail(random).toString())
                .when()
                .put(Constants.PATH_USERS_ID)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(Constants.CODE, equalTo(HttpStatus.SC_NOT_FOUND));
    }
}
