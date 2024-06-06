package wendydeluca.capstone.payloads.host;

import wendydeluca.capstone.entities.Role;

public record HostLoginDTO (String email, String password, String role){
}
