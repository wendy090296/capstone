package wendydeluca.capstone.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import wendydeluca.capstone.entities.User;
import wendydeluca.capstone.exceptions.BadRequestException;
import wendydeluca.capstone.payloads.user.UserDTO;
import wendydeluca.capstone.payloads.user.UserResponseDTO;
import wendydeluca.capstone.services.UserService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
     @Autowired
     public UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<User> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "name") String sortBy){
        return userService.findAllUsers(page,size,sortBy);
    }

    @GetMapping("/{userId}")
    public User findUserById(@PathVariable Long userId){
        return userService.findById(userId);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO saveUser(@RequestBody @Validated UserDTO body, BindingResult validation){
        if(validation.hasErrors()){
            System.out.println(validation.getAllErrors());
            throw new BadRequestException(validation.getAllErrors());
        }
        return new UserResponseDTO(this.userService.saveNewUser(body).getId());
    }


    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public User findUserByIdAndUpdate(@PathVariable Long userId, @RequestBody @Validated UserDTO body, BindingResult validation){
        if(validation.hasErrors()){
            System.out.println(validation.getAllErrors());
            throw new BadRequestException(validation.getAllErrors());
        }
        return userService.findByIdAndUpdate(userId,body);
    }



    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);

    }

 //----------------- PERSONAL PROFILE -----------------//
    @GetMapping("/me")
    public User getProfile(@AuthenticationPrincipal User currentAuthUser){
        return currentAuthUser;
    }

    @PutMapping("/me")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public User updateProfile(@AuthenticationPrincipal User currentAuthUser, @RequestBody UserDTO updateUser){
        return userService.findByIdAndUpdate(currentAuthUser.getId(),updateUser);
    }


    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfile(@AuthenticationPrincipal User currentAuthUser){
        this.userService.deleteUser(currentAuthUser.getId());
    }
    @PostMapping("/upload")
    public String uploadAvatar(@RequestParam("avatar") MultipartFile image) throws IOException {
        // "avatar" deve corrispondere ESATTAMENTE alla chiave del Multipart dove sarà contenuto il file
        // altrimenti il file non verrà trovato
        return this.userService.uploadImage(image);

    }

}
