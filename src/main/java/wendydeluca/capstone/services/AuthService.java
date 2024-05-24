package wendydeluca.capstone.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import wendydeluca.capstone.entities.User;
import wendydeluca.capstone.exceptions.UnauthorizedException;
import wendydeluca.capstone.payloads.user.UserLoginDTO;
import wendydeluca.capstone.security.JWTTools;

@Service
public class AuthService {
    @Autowired
    public UserService userService;

    @Autowired
    public JWTTools jwtTools;
    @Autowired
    PasswordEncoder bCrypt;

    public String authenticateUserAndGenerateToken(UserLoginDTO payload){
        // 1. Fare un controllo delle credenziali d'accesso dell'utente
        // 1.1 Verificare che l'email del db sia quella ricevuta nel payload
        User user = userService.findByEmail(payload.email());
        // 1.2 Verificare che la password del db corrisponda a quella in entrata nel payload
        if(bCrypt.matches(payload.password(), user.getPassword())){
            // 2. Se i controlli sono OK, genera il token
            return this.jwtTools.createToken(user);
        } else{
            // 3. Se non sono ok, 401 (unauthorized)
            throw new UnauthorizedException("Invalid login credentials! Try again.");
        }
    }
}
