package pages.api.users;

import api.base_paths.BasePath;
import api.base_uris.BaseURI;
import api.driver.APIActions;
import api.driver.RequestMethod;
import api.context.Context;
import api.context.ScenarioContext;
import api.pojo.requests.Users.PostUsers_Req;
import api.pojo.responses.Users.PostUsers_Res;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utiltites.assertions.Assertions;
import utiltites.logger.Log4JLogger;
import org.apache.http.HttpStatus;
import utiltites.readers.json_reader.JSONDataManager;

import java.util.HashMap;
import java.util.Map;

public class PostUser {

    private final static String jsonFilePath = ("src/test/resources/test_data/api/request/Users/PostUser.json");
    private static final String userName = JSONDataManager.getJSONData(jsonFilePath, "USERNAME", JSONDataManager.Types.STRING).toString();
    private static final String userJob = JSONDataManager.getJSONData(jsonFilePath, "JOB", JSONDataManager.Types.STRING).toString();

    private static Response response;
    private static final Map<String, String> header = new HashMap<>();

    public static void invokePostUserEndpointWithValidKey() {
        PostUsers_Req postUsersReq = new PostUsers_Req();
        postUsersReq.setName(userName);
        postUsersReq.setJob(userJob);

        header.put("x-api-key","reqres-free-v1");
        response =
                APIActions
                        .setRequestSpecifications()
                        .setRequestMethod(RequestMethod.POST)
                        .setBaseUri(BaseURI.REQRES_BASE.getBaseURI())
                        .setBasePath(BasePath.POST_USERS.getBasePath())
                        .setContentType(ContentType.JSON)
                        .setBody(postUsersReq)
                        .addHeaders(header)
                        .setExpectedStatusCode(HttpStatus.SC_CREATED)
                        .sendRequest();

                ScenarioContext.setContext(Context.RESPONSE_PAYLOAD, response);
        PostUsers_Res userID = response.as(PostUsers_Res.class);
        ScenarioContext.setContext(Context.USER_ID, userID.getId());
        Log4JLogger.logINFO(PostUser.class,"USER ID :\n" + ScenarioContext.getContext(Context.USER_ID));

    }

    public static void assertResponsePayloadContainsCorrectParameters()
    {
        PostUsers_Res postUserRes = response.as(new TypeRef<>() {});

        Assertions.hardAssert().objectEquals(postUserRes.getName(),userName);
        Assertions.hardAssert().objectEquals(postUserRes.getJob(),userJob);
        Assertions.hardAssert().assertNotNull(postUserRes.getId());
        Assertions.hardAssert().assertNotNull(postUserRes.getCreatedAt());
    }
}