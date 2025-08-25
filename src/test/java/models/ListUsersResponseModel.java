package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ListUsersResponseModel {
    Integer page, total;
    @JsonProperty("per_page")
    Integer perPage;
    @JsonProperty("total_pages")
    Integer totalPages;
    List<User> data;
    Support support;
    @Data
    public static class User {
        Integer id;
        String email, avatar;
        @JsonProperty("first_name")
        String firstName;
        @JsonProperty("last_name")
        String lastName;
    }
    @Data
    public static class Support {
        String url, text;
    }
}

