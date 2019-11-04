package common.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Member {
    String id;
    String password;
    String name;
    String type;
    String auth;
    String certificate;
}
