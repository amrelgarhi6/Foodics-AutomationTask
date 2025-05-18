package pages.api.users;

import api.base_paths.BasePath;
import api.base_uris.BaseURI;
import api.context.Context;
import api.context.ScenarioContext;
import api.driver.APIActions;
import api.driver.RequestMethod;
import api.pojo.responses.Users.PutUsers_Res;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import utiltites.assertions.Assertions;
import utiltites.readers.json_reader.JSONDataManager;

import java.util.HashMap;
import java.util.Map;

public class PutUser {

    private static Response response;
    private static final Map<String, String> header = new HashMap<>();
    private static final Map<String, String> queryParam = new HashMap<>();
    private static final Map<String, String> body = new HashMap<>();

    private final static String jsonFilePath = ("src/test/resources/test_data/api/request/Users/PutUser.json");
    private final static String USERNAME = JSONDataManager.getJSONData(jsonFilePath, "USERNAME", JSONDataManager.Types.STRING).toString();
    private final static String USERJOB = JSONDataManager.getJSONData(jsonFilePath, "JOB", JSONDataManager.Types.STRING).toString();

    private final static String userID = ScenarioContext.getContext(Context.USER_ID).toString();

    public static void invokePutUserEndpointWithValidKey() {
        header.put("x-api-key","reqres-free-v1");
//        queryParam.put("id",userID);       // THERE IS AN ISSUE WITH REQRES SO AFTER I USE THE USER ID I CAN"T GET ITS DATA
//        queryParam.put("id","2");
        body.put("name",USERNAME);
        body.put("job",USERJOB);

        response =
                APIActions
                        .setRequestSpecifications()
                        .setRequestMethod(RequestMethod.PUT)
                        .setBaseUri(BaseURI.REQRES_BASE.getBaseURI())
                        .setBasePath(BasePath.PUT_USERS.getBasePath())
                        .setContentType(ContentType.JSON)
//                        .addQueryParams(queryParam)
                        .setBody(body)
                        .addHeaders(header)
                        .sendRequest();

        ScenarioContext.setContext(Context.RESPONSE_PAYLOAD, response);
    }

    public static void assertResponsePayloadUpdatedCorrectly()
    {
        PutUsers_Res putUserRes = response.as(new TypeRef<>() {});

        Assertions.hardAssert().assertNotNull(putUserRes.getName());
        Assertions.hardAssert().assertNotNull(putUserRes.getJob());
        Assertions.hardAssert().assertNotNull(putUserRes.getUpdatedAt());

        Assertions.hardAssert().objectEquals(putUserRes.getName(),USERNAME);
        Assertions.hardAssert().objectEquals(putUserRes.getJob(),USERJOB);


    }
}