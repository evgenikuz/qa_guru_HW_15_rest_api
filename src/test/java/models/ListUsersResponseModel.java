package models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListUsersResponseModel {
    Integer page, total, per_page, total_pages;
    List<User> data;
    Support support;
    @Data
    public static class User {
        Integer id;
        String email, first_name, last_name, avatar;
    }
    @Data
    public static class Support {
        String url, text;
    }
}

