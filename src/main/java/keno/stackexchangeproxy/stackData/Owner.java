package keno.stackexchangeproxy.stackData;

import keno.stackexchangeproxy.dto.OwnerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor
public class Owner {

    private Long account_id;

    private int reputation;
    @Id
    private Long user_id;
    private String user_type;

    @Column(columnDefinition = "TEXT")
    private String profile_image;
    private String display_name;

    @Column(columnDefinition = "TEXT")
    private String link;

    protected Owner() {}

    public static Owner createOwner(OwnerDto dto) {
        return Owner.builder()
                .account_id(dto.getAccount_id())
                .display_name(dto.getDisplay_name())
                .link(dto.getLink())
                .profile_image(dto.getProfile_image())
                .reputation(dto.getReputation())
                .user_id(dto.getUser_id())
                .user_type(dto.getUser_type())
                .build();
    }
}

