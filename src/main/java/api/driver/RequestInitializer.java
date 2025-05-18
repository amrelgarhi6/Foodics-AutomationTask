package api.driver;

import com.fasterxml.jackson.databind.ObjectMapper;
import utiltites.exceptions.Exceptions;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utiltites.logger.Log4JLogger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Objects;

public class RequestInitializer {
    private String uri = null;
    private URL url = null;
    private Integer port = null;
    private String basePath = null;
    private RequestMethod requestMethod = null;
    private Object requestBody = null;
    private Map<String, String> headers = null;
    private Map<String, String> pathParams = null;
    private Map<String, String> queryParams = null;
    private Map<String, String> formParams = null;
    private ContentType contentType = null;
    private Boolean urlEncodingEnabled = null;
    private Integer httpStatusCode = null;
    private RestAssuredConfig restAssuredConfig = null;
    private AuthenticationScheme authenticationScheme = null;

    public RequestInitializer setRequestMethod(@Nonnull final RequestMethod requestMethod) {
        this.requestMethod = requestMethod;
        return this;
    }

    public RequestInitializer setBaseUri(@Nonnull final String uri) {
        this.uri = uri;
        return this;
    }

    public RequestInitializer setBaseUri(@Nonnull final URL uri) {
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public RequestInitializer setBasePath(@Nonnull final String basePath) {
        this.basePath = basePath;
        return this;
    }

    public RequestInitializer setPort(@Nullable final Integer port) {
        this.port = port;
        Log4JLogger.logINFO(getClass(), "Port: " + port);
        return this;
    }

    public RequestInitializer setPort(@Nullable final String port) {
        this.port = Integer.parseInt(Objects.requireNonNull(port));
        return this;
    }

    public RequestInitializer addHeaders(@Nullable final Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    public RequestInitializer addPathParams(@Nullable final Map<String, String> pathParams) {
        this.pathParams = pathParams;
        return this;
    }

    public RequestInitializer addQueryParams(@Nullable final Map<String, String> queryParams) {
        this.queryParams = queryParams;
        return this;
    }

    public RequestInitializer addFormParams(@Nullable final Map<String, String> formParams) {
        this.formParams = formParams;
        return this;
    }

    public RequestInitializer setBody(@Nullable final Object requestBody) {
        this.requestBody = requestBody;
        return this;
    }
    public RequestInitializer setExpectedStatusCode(@Nullable final Integer httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
        return this;
    }

    public RequestInitializer setConfig(@Nullable final RestAssuredConfig restAssuredConfig) {
        this.restAssuredConfig = restAssuredConfig;
        return this;
    }

    public RequestInitializer setContentType(@Nullable final ContentType contentType) {
        this.contentType = contentType;
        return this;
    }

    public RequestInitializer setUrlEncodingEnabled(@Nullable final Boolean urlEncodingEnabled) {
        this.urlEncodingEnabled = urlEncodingEnabled;
        return this;
    }

    public RequestInitializer setAuth(@Nullable final AuthenticationScheme authenticationScheme) {
        this.authenticationScheme = authenticationScheme;
        return this;
    }

    public Response sendRequest() {
        return send();
    }

    /**
     * Build API Request Specification
     */
    private RequestSpecification buildRequest() {
        Log4JLogger.logINFO(getClass(), "------------------------------------------------------------------------------------------------------------------------------------");
        if (uri != null) {
            Log4JLogger.logINFO(getClass(), new Object() {
            }.getClass().getEnclosingMethod().getName(), "Printing out all request specification details log for {Service URL: " + uri + basePath + "}");
        } else {
            Log4JLogger.logINFO(getClass(), new Object() {
            }.getClass().getEnclosingMethod().getName(), "Printing out all request specification details log for {Service URL: " + url + basePath + "}");
        }
        Log4JLogger.logINFO(getClass(), "------------------------------------------------------------------------------------------------------------------------------------");
        Log4JLogger.logINFO(getClass(), "RequestMethod: " + requestMethod);
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        try {
            if (uri != null) {
                requestSpecBuilder.setBaseUri(uri);
                Log4JLogger.logINFO(getClass(), new Object() {
                }.getClass().getEnclosingMethod().getName(), "BaseUri: " + uri);
            } else {
                requestSpecBuilder.setBaseUri(url.toURI());
                Log4JLogger.logINFO(getClass(), new Object() {
                }.getClass().getEnclosingMethod().getName(), "BaseUri: " + url);
            }
            if (basePath != null) {
                requestSpecBuilder.setBasePath(basePath);
                Log4JLogger.logINFO(getClass(), new Object() {
                }.getClass().getEnclosingMethod().getName(), "BasePath: " + basePath);
            }
            if (port != null) {
                requestSpecBuilder.setPort(port);
                Log4JLogger.logINFO(getClass(), new Object() {
                }.getClass().getEnclosingMethod().getName(), "Port: " + port);
            }
            if (headers != null && !headers.isEmpty()) {
                requestSpecBuilder.addHeaders(headers);
                Log4JLogger.logINFO(getClass(), new Object() {
                }.getClass().getEnclosingMethod().getName(), "Headers: " + headers);
            }
            if (pathParams != null && !pathParams.isEmpty()) {
                requestSpecBuilder.addPathParams(pathParams);
                Log4JLogger.logINFO(getClass(), new Object() {
                }.getClass().getEnclosingMethod().getName(), "Path Params: " + pathParams);
            }
            if (queryParams != null && !queryParams.isEmpty()) {
                requestSpecBuilder.addQueryParams(queryParams);
                Log4JLogger.logINFO(getClass(), new Object() {
                }.getClass().getEnclosingMethod().getName(), "Query Params: " + queryParams);
            }
            if (formParams != null && !formParams.isEmpty()) {
                requestSpecBuilder.addFormParams(formParams);
                Log4JLogger.logINFO(getClass(), new Object() {
                }.getClass().getEnclosingMethod().getName(), "Form Params: " + formParams);
            }
            if (requestBody != null) {
                requestSpecBuilder.setBody(requestBody);
                Log4JLogger.logINFO(getClass(), new Object() {
                }.getClass().getEnclosingMethod().getName(), "Body JSON Object: {} " + new ObjectMapper().writeValueAsString(requestBody));
            }
            if (httpStatusCode != null) {
                Log4JLogger.logINFO(getClass(), new Object() {
                }.getClass().getEnclosingMethod().getName(), "ExpectedStatusCode: " + httpStatusCode);
            }
            if (restAssuredConfig != null) {
                requestSpecBuilder.setConfig(restAssuredConfig);
                Log4JLogger.logINFO(getClass(), new Object() {
                }.getClass().getEnclosingMethod().getName(), "Rest-assured Config: " + restAssuredConfig);
            }
            if (contentType != null) {
                requestSpecBuilder.setContentType(contentType);
                Log4JLogger.logINFO(getClass(), new Object() {
                }.getClass().getEnclosingMethod().getName(), "Content Type: " + contentType);
            }
            if (urlEncodingEnabled != null) {
                requestSpecBuilder.setUrlEncodingEnabled(urlEncodingEnabled);
                Log4JLogger.logINFO(getClass(), new Object() {
                }.getClass().getEnclosingMethod().getName(), "URL Encoding Enabled: " + urlEncodingEnabled);
            }
            if (authenticationScheme != null) {
                requestSpecBuilder.setAuth(authenticationScheme);
                Log4JLogger.logINFO(getClass(), new Object() {
                }.getClass().getEnclosingMethod().getName(), "Authentication Scheme: " + authenticationScheme);
            }
            requestSpecBuilder.setRelaxedHTTPSValidation();
        } catch (Exception e) {
            Exceptions.handle(getClass(), e);
        }
        Log4JLogger.logINFO(getClass(), "------------------------------------------------------------------------------------------------------------------------------------");
        if (uri != null) {
            Log4JLogger.logINFO(getClass(), new Object() {
            }.getClass().getEnclosingMethod().getName(), "All request specification details log have been logged for {Service URL: " + uri + basePath + "}");
        } else {
            Log4JLogger.logINFO(getClass(), new Object() {
            }.getClass().getEnclosingMethod().getName(), "All request specification details log have been logged for {Service URL: " + url + basePath + "}");
        }
        Log4JLogger.logINFO(getClass(), "------------------------------------------------------------------------------------------------------------------------------------");
        return requestSpecBuilder.build();
    }

    /**
     * Perform API Request
     */
    private Response send() {
        Response response = null;
        try {
            if (httpStatusCode != null) {
                switch (requestMethod) {
                    case GET -> response = new GetRequest().send(buildRequest(), httpStatusCode);
                    case POST -> response = new PostRequest().send(buildRequest(), httpStatusCode);
                    case PUT -> response = new PutRequest().send(buildRequest(), httpStatusCode);
                    case DELETE -> response = new DeleteRequest().send(buildRequest(), httpStatusCode);
                    case PATCH -> response = new PatchRequest().send(buildRequest(), httpStatusCode);
                    default -> Log4JLogger.logWARN(getClass(), new Object() {
                    }.getClass().getEnclosingMethod().getName(), "Kindly select valid HTTP request method");
                }
            } else {
                switch (requestMethod) {
                    case GET -> response = new GetRequest().send(buildRequest());
                    case POST -> response = new PostRequest().send(buildRequest());
                    case PUT -> response = new PutRequest().send(buildRequest());
                    case DELETE -> response = new DeleteRequest().send(buildRequest());
                    case PATCH -> response = new PatchRequest().send(buildRequest());
                    default -> Log4JLogger.logWARN(getClass(), "Kindly select valid HTTP request method");
                }
            }
        } catch (Exception e) {
            Exceptions.handle(getClass(), e);
        }
        Log4JLogger.logINFO(getClass(), new Object() {}.getClass().getEnclosingMethod().getName(), "Response payload: " + Objects.requireNonNull(response).getBody().asPrettyString());
        return response;
    }
}