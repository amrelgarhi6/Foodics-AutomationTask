package api.base_uris;

import lombok.Getter;

@Getter
public enum BaseURI {
    REQRES_BASE("https://reqres.in");

    private final String baseURI;

    BaseURI(String baseURI) {
        this.baseURI = baseURI;
    }
}