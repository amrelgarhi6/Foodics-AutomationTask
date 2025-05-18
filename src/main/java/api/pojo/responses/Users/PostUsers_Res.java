package api.pojo.responses.Users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostUsers_Res {

    @Getter
    @Setter
    private String name;
    private String job;
    private String id;
    private String createdAt;

}