package top.lyijian.community.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GiteeUser {
    private String name;
    private Long id;
    private String email;
    private String avatar_url;
}
