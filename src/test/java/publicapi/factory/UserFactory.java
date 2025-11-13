package publicapi.factory;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import publicapi.model.UserModel;
import publicapi.support.Constants;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static publicapi.support.Constants.*;

/**
 * @author jussaragranja
 * User Factory
 */

public class UserFactory {


    public static JSONObject createValidUser(int random) {
        return new JSONObject()
                .put("name", Constants.NAME_VALUE)
                .put("email", "jussarateste" + random + "@gmail.com")
                .put("gender", Constants.GENDER_FEMALE)
                .put("status", Constants.STATUS_ACTIVE);
    }

    public static JSONObject createValidEmail(int random) {
        return new JSONObject()
                .put("email", "jussarateste" + random + "@gmail.com");
    }

}
