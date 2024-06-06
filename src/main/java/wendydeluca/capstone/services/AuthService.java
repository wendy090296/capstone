package wendydeluca.capstone.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import wendydeluca.capstone.entities.Host;
import wendydeluca.capstone.entities.Role;
import wendydeluca.capstone.entities.Traveller;
import wendydeluca.capstone.entities.User;
import wendydeluca.capstone.exceptions.UnauthorizedException;
import wendydeluca.capstone.payloads.user.UserLoginDTO;
import wendydeluca.capstone.payloads.user.UserLoginRespDTO;
import wendydeluca.capstone.repositories.HostDAO;
import wendydeluca.capstone.repositories.TravellerDAO;
import wendydeluca.capstone.security.JWTTools;

import java.util.UUID;

@Service
public class AuthService {
    @Autowired
    public UserService userService;

    @Autowired
    public JWTTools jwtTools;
    @Autowired
    PasswordEncoder bCrypt;

    @Autowired
    public HostDAO hDAO;
    @Autowired
    public TravellerDAO tDAO;

    public UserLoginRespDTO authenticateUserAndGenerateToken(UserLoginDTO payload){
        // 1. Fare un controllo delle credenziali d'accesso dell'utente
        // 1.1 Verificare che l'email del db sia quella ricevuta nel payload
        User user = userService.findByEmail(payload.email());
        UUID responseId = null;
        // 1.2 Verificare che la password del db corrisponda a quella in entrata nel payload
        if(bCrypt.matches(payload.password(), user.getPassword())){
            // 2. Se i controlli sono OK, genera il token
            String name = null;
            String surname=null;
            String role= null;
            if(user.getRole().equals(Role.TRAVELLER)){
                Traveller traveller  = tDAO.findByUser(user);
                responseId = traveller.getId();
                name= traveller.getName();
                surname=traveller.getSurname();
                role="TRAVELLER";

            }else{
                Host host  = hDAO.findByUser(user);
                responseId = host.getId();
                name= host.getName();
                surname=host.getSurname();
                role="HOST";

            }

            UserLoginRespDTO response = new UserLoginRespDTO(this.jwtTools.createToken(user), responseId,name,surname,role);
            return response;

        } else{
            // 3. Se non sono ok, 401 (unauthorized)
            throw new UnauthorizedException("Invalid login credentials! Try again.");
        }
    }
}
