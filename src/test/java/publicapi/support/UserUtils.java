package publicapi.support;

import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import publicapi.model.UserModel;

import java.util.List;

public class UserUtils {
    public static List<UserModel> getUsers(RequestSpecification spec) {
        return spec
                .when().get(Constants.PATH_USERS)
                .then().statusCode(HttpStatus.SC_OK)
                .extract().jsonPath().getList("data", UserModel.class);
    }
    public static boolean userExists(RequestSpecification spec, int id) {
        return spec.pathParam(Constants.ID, id)
                .when().get(Constants.PATH_USERS_ID)
                .then().statusCode(HttpStatus.SC_OK)
                .extract().path(Constants.CODE).equals(HttpStatus.SC_OK);
    }
}