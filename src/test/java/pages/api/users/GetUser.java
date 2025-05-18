package pages.api.users;

import api.base_paths.BasePath;
import api.base_uris.BaseURI;
import api.driver.APIActions;
import api.driver.RequestMethod;
import api.context.Context;
import api.context.ScenarioContext;
import api.pojo.responses.Users.GetUsers_Res;
import api.pojo.responses.Users.PostUsers_Res;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import utiltites.assertions.Assertions;
import utiltites.readers.json_reader.JSONDataManager;

import java.util.HashMap;
import java.util.Map;

public class GetUser {
    private static Response response;

    private static final Map<String, String> header = new HashMap<>();
    private static final Map<String, String> queryParam = new HashMap<>();

    private final static String jsonFilePath = ("src/test/resources/test_data/api/response/Users/GetUser.json");
    private final static String FIRSTNAME = JSONDataManager.getJSONData(jsonFilePath, "DATA.first_name", JSONDataManager.Types.STRING).toString();
    private final static String LASTNAME = JSONDataManager.getJSONData(jsonFilePath, "DATA.last_name", JSONDataManager.Types.STRING).toString();
    private final static String EMAIL = JSONDataManager.getJSONData(jsonFilePath, "DATA.email", JSONDataManager.Types.STRING).toString();
    private final static String AVATAR = JSONDataManager.getJSONData(jsonFilePath, "DATA.avatar", JSONDataManager.Types.STRING).toString();
    private final static String userID = ScenarioContext.getContext(Context.USER_ID).toString();

    public static void invokeGetUserEndpointWithValidKey() {
        header.put("x-api-key","reqres-free-v1");
//        queryParam.put("id",userID);       // THERE IS AN ISSUE WITH REQRES SO AFTER I USE THE USER ID I CAN"T GET ITS DATA
        queryParam.put("id","2");


        response =
                APIActions
                        .setRequestSpecifications()
                        .setRequestMethod(RequestMethod.GET)
                        .setBaseUri(BaseURI.REQRES_BASE.getBaseURI())
                        .setBasePath(BasePath.GET_USERS.getBasePath())
                        .setContentType(ContentType.JSON)
                        .addQueryParams(queryParam)
                        .addHeaders(header)
                        .setExpectedStatusCode(HttpStatus.SC_OK)
                        .sendRequest();

        ScenarioContext.setContext(Context.RESPONSE_PAYLOAD, response);
    }

    public static void assertResponsePayloadContainsCorrectParameters()
    {
        GetUsers_Res getUserRes = response.as(new TypeRef<>() {});

        Assertions.hardAssert().assertNotNull(getUserRes.getData().getId());
        Assertions.hardAssert().assertNotNull(getUserRes.getData().getEmail());
        Assertions.hardAssert().assertNotNull(getUserRes.getData().getFirst_name());
        Assertions.hardAssert().assertNotNull(getUserRes.getData().getLast_name());
        Assertions.hardAssert().assertNotNull(getUserRes.getData().getAvatar());

        Assertions.hardAssert().assertNotNull(getUserRes.getSupport().getText());
        Assertions.hardAssert().assertNotNull(getUserRes.getSupport().getUrl());

        Assertions.hardAssert().objectEquals(getUserRes.getData().getEmail(),EMAIL);
        Assertions.hardAssert().objectEquals(getUserRes.getData().getFirst_name(),FIRSTNAME);
        Assertions.hardAssert().objectEquals(getUserRes.getData().getLast_name(),LASTNAME);
        Assertions.hardAssert().objectEquals(getUserRes.getData().getAvatar(),AVATAR);


    }
}