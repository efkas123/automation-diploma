package api.models.auth;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthResponseModel {


    @JsonProperty("access_token")
    private String accessToken;
    private String bringListUUID;
    private String email;
    private String name;
    private String photoPath;
    private String publicUuid;
    private String uuid;

    @JsonProperty("expires_in")
    private String expiresIn;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("token_type")
    private String tokenType;

}
