package wendydeluca.capstone.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import wendydeluca.capstone.entities.Host;
import wendydeluca.capstone.entities.User;
import wendydeluca.capstone.exceptions.BadRequestException;
import wendydeluca.capstone.exceptions.NotFoundException;
import wendydeluca.capstone.payloads.user.UserDTO;
import wendydeluca.capstone.repositories.UserDAO;

@Service
public class UserService {

    @Autowired
    public UserDAO uDAO;

    @Autowired
    public PasswordEncoder bcrypt;



    public Page<User> findAllUsers(int page, int size, String sortBy) {
        if (size < 50) size = 50;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return uDAO.findAll(pageable);
    }

    public User findById(Long userId) {
        return uDAO.findById(userId).orElseThrow(() -> new NotFoundException("The user with id " + userId + "has not been found!"));
    }

    public User findByEmail(String email) {
        return uDAO.findByEmail(email).orElseThrow(() -> new NotFoundException("User with email " + email + " not found!"));
    }


    public User saveNewUser(UserDTO payload) {
        // 1. Se l'email dello user non é presente,
        if (!uDAO.existsByEmail(payload.email())) {
            // 2. creo un nuovo oggetto User "modellato" sul payload
            User newUser = new User(payload.name(), payload.surname(), payload.email(), bcrypt.encode(payload.password()));
            return uDAO.save(newUser);
            // Se é già presente, lancio eccezione :
        } else throw new BadRequestException("User with email '" + payload.email() + "  already exists.");

    }

    public User findByIdAndUpdate(Long userId, UserDTO payload) {
        User found = this.findById(userId);
        found.setName(payload.name());
        found.setSurname(payload.surname());
        found.setEmail(payload.email());
        uDAO.save(found);
        return found;

    }

    public void deleteUser(Long userId) {
        User found = this.findById(userId);
        uDAO.delete(found);
    }



}
