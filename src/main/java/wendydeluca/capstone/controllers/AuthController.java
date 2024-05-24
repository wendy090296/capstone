package wendydeluca.capstone.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import wendydeluca.capstone.exceptions.BadRequestException;
import wendydeluca.capstone.payloads.user.UserDTO;
import wendydeluca.capstone.payloads.user.UserLoginDTO;
import wendydeluca.capstone.payloads.user.UserLoginRespDTO;
import wendydeluca.capstone.payloads.user.UserResponseDTO;
import wendydeluca.capstone.services.AuthService;
import wendydeluca.capstone.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    public AuthService authService;

    @Autowired
    public UserService userService;


    @PostMapping("/login")
    public UserLoginRespDTO login(@RequestBody UserLoginDTO payload){
        return new UserLoginRespDTO(this.authService.authenticateUserAndGenerateToken(payload));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO saveUser(@RequestBody @Validated UserDTO body, BindingResult validation){
        if(validation.hasErrors()){
            System.out.println(validation.getAllErrors());
            throw new BadRequestException(validation.getAllErrors());
        }
        return new UserResponseDTO(this.userService.saveNewUser(body).getId());
    }
}
