package publicapi.test;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import publicapi.core.BaseTest;
import publicapi.model.UserModel;
import publicapi.support.Constants;
import publicapi.support.UserUtils;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * @author jussaragranja
 * Test Class
 */

public class DeleteUsersTest extends BaseTest {

    @Test
    @DisplayName("Deve deletar usuário existente e retornar 204")
    void shouldDeleteUserAndReturn204() {
        List<UserModel> users = UserUtils.getUsers(requestSpec);
        int userId = users.get(0).getId();

        requestSpec.pathParam(Constants.ID, String.valueOf(userId))
                .when()
                .delete(Constants.PATH_USERS_ID)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(Constants.CODE, equalTo(HttpStatus.SC_NO_CONTENT));

        assertFalse(UserUtils.userExists(requestSpec, userId));
    }

    @Test
    @DisplayName("Deve retornar 404 ao tentar deletar usuário inexistente")
    void shouldReturn404ForNonExistentUser() {
        requestSpec.pathParam(Constants.ID, Constants.INVALID_ID)
                .when()
                .delete(Constants.PATH_USERS_ID)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(Constants.CODE, equalTo(HttpStatus.SC_NOT_FOUND));
    }

}
