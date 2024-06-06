package wendydeluca.capstone.payloads.user;

import java.util.UUID;

public record UserLoginRespDTO(String accessToken, UUID id , String name,String surname,String role) {
}
