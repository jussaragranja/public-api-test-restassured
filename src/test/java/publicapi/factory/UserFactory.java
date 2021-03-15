package publicapi.factory;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import publicapi.model.UserModel;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static publicapi.support.Constants.*;

/**
 * @author jussaragranja
 * User Factory
 */

public class UserFactory {

    public static JSONObject validBody(int random) {
        JSONObject userJson = new JSONObject();
        userJson.accumulate("name", NAME_VALUE);
        userJson.accumulate("email", "jussarateste"+random+"@gmail.com");
        userJson.accumulate("gender", GENDER_FEMALE);
        userJson.accumulate("status", STATUS_ACTIVE);
        return userJson;
    }

    public static JSONObject validBodyEmail(int random) {
        JSONObject mailJson = new JSONObject();
        mailJson.accumulate("email", "jussarateste"+random+"@gmail.com");
        return mailJson;
    }

    public static List<UserModel> getUser(){
        List<UserModel> userModelList = given()
            .header(AUTHORIZATION, BEARER_TOKEN.concat(TOKEN))
            .contentType(ContentType.JSON)
        .when()
            .get(PATH_USERS)
        .then()
            .statusCode(HttpStatus.SC_OK)
            .body(CODE, equalTo(HttpStatus.SC_OK)).extract().jsonPath().getList("data", UserModel.class);

        return userModelList;
    }

    public static boolean userExist(int id){
        given()
            .header(AUTHORIZATION, BEARER_TOKEN.concat(TOKEN))
            .contentType(ContentType.JSON)
            .pathParam(ID, id)
        .when()
            .get(PATH_USERS_ID)
        .then()
            .statusCode(HttpStatus.SC_OK)
            .body(CODE, equalTo(HttpStatus.SC_OK));

        return true;
    }

    public static boolean userNotExist(int id){
        given()
            .header(AUTHORIZATION, BEARER_TOKEN.concat(TOKEN))
            .contentType(ContentType.JSON)
            .pathParam(ID, id)
        .when()
            .get(PATH_USERS_ID)
        .then()
            .body(CODE, equalTo(HttpStatus.SC_NOT_FOUND));

        return true;
    }

}
