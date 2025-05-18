package api.base_paths;

import lombok.Getter;

@Getter
public enum BasePath {
    POST_USERS("/api/users"),
    GET_USERS("/api/users"),
    PUT_USERS("/api/users/2");
    public final String basePath;

    BasePath(String basePath) {
        this.basePath = basePath;
    }
}