package api.pojo.responses.Users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetUsers_Res {

        private Data data;
        private Support support;

        @Getter
        @Setter
        public static class Data {
            private int id;
            private String email;
            private String first_name;
            private String last_name;
            private String avatar;
        }

        @Getter
        @Setter
        public static class Support {
            private String url;
            private String text;
        }
}