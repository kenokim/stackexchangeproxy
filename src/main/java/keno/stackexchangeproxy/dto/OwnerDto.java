package keno.stackexchangeproxy.dto;

import lombok.Data;
import lombok.ToString;

@Data @ToString
public class OwnerDto {
    private Long account_id;
    private int reputation;
    private Long user_id;
    private String user_type;
    private String profile_image;
    private String display_name;
    private String link;
}

