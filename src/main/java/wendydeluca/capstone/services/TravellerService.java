package wendydeluca.capstone.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import wendydeluca.capstone.entities.Role;
import wendydeluca.capstone.entities.Traveller;
import wendydeluca.capstone.entities.User;
import wendydeluca.capstone.exceptions.NotFoundException;
import wendydeluca.capstone.payloads.traveller.TravellerDTO;
import wendydeluca.capstone.repositories.TravellerDAO;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class TravellerService{
    @Autowired
    public TravellerDAO tdao;
    @Autowired
    PasswordEncoder bcrypt;

    @Autowired
    private Cloudinary cloudinaryUploader;


    public List<Traveller> findAllTravellers (int page, int size, String sortBy){
        if (size>50) size = 50;
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy));
        return tdao.findAll(); // quando sistemo la paginazione uso pageable
    }

    public Traveller findById(UUID tId){
        return tdao.findById(tId).orElseThrow(()->new NotFoundException("The user with id " + tId + "has not been found!"));
    }

    public Traveller saveTraveller(TravellerDTO body){
        Traveller traveller = new Traveller();
        traveller.setName(body.name());
        traveller.setSurname(body.surname());
        traveller.setAge((body.age()));
        traveller.setEmail(body.email());
        traveller.setAvatar("https://t3.ftcdn.net/jpg/03/18/84/50/240_F_318845025_Ti0cVVtf9xIj4YW5a9sgi4ecfykXUqbo.jpg");// aggiungi url avatar
        traveller.setDescription(body.description());
        traveller.setCountry("https://t3.ftcdn.net/jpg/01/07/55/24/360_F_107552414_hO3KD0wmRFypTpzpwf7LTYg2oWpYYGm6.jpg");
        traveller.setTravelDestination(body.travelDestination());
        traveller.setSpokenLanguages(body.spokenLanguages());
        traveller.setInterests(body.interests());
        User user = new User();
        user.setEmail(traveller.getEmail());
        user.setPassword(bcrypt.encode(body.password()));
        user.setName(traveller.getName());
        user.setSurname(traveller.getSurname());
        user.setRole(Role.TRAVELLER);
        traveller.setUser(user);
        return tdao.save(traveller);

    }

    public Traveller updateTraveller(UUID tId, TravellerDTO body){
        Traveller found = tdao.findById(tId).orElseThrow(()-> new NotFoundException(tId));
        found.setName(body.name());
        found.setSurname(body.surname());
//        found.setAge(body.age());
//        found.setEmail(body.email());
//        found.setAvatar(body.avatar());
        found.setDescription(body.description());
//        found.setCountry(body.country());
        found.setTravelDestination(body.travelDestination());
        found.setSpokenLanguages(body.spokenLanguages());
        found.setInterests(body.interests());
        found.getUser().setName(body.name());
        found.getUser().setSurname(body.surname());
//        found.getUser().setEmail(body.email());
        return tdao.save(found);

    }

    public void deleteTraveller(UUID tId){
        Traveller found = tdao.findById(tId).orElseThrow(()-> new NotFoundException(tId));
        tdao.delete(found);
    }



}
