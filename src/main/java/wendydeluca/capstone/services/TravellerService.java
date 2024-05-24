package wendydeluca.capstone.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import wendydeluca.capstone.entities.Traveller;
import wendydeluca.capstone.exceptions.NotFoundException;
import wendydeluca.capstone.payloads.traveller.TravellerDTO;
import wendydeluca.capstone.repositories.TravellerDAO;

import java.util.UUID;

@Service
public class TravellerService{
    @Autowired
    public TravellerDAO tdao;


    public Page<Traveller> findAllTravellers (int page, int size, String sortBy){
        if (size>50) size = 50;
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy));
        return tdao.findAll(pageable);
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
        traveller.setAvatar(body.avatar());
        traveller.setDescription(body.description());
        traveller.setCountry(body.country());
        traveller.setAvatar(body.avatar());
        traveller.setTravelDestination(body.travelDestination());
        traveller.setSpokenLanguages(body.spokenLanguages());
        traveller.setInterests(body.interests());
        return tdao.save(traveller);

    }

    public Traveller updateTraveller(UUID tId, TravellerDTO body){
        Traveller found = tdao.findById(tId).orElseThrow(()-> new NotFoundException(tId));
        found.setName(body.name());
        found.setSurname(body.surname());
        found.setAge(body.age());
        found.setEmail(body.email());
        found.setAvatar(body.avatar());
        found.setDescription(body.description());
        found.setCountry(body.country());
        found.setAvatar(body.avatar());
        found.setTravelDestination(body.travelDestination());
        found.setSpokenLanguages(body.spokenLanguages());
        found.setInterests(body.interests());
        return tdao.save(found);

    }

    public void deleteTraveller(UUID tId){
        Traveller found = tdao.findById(tId).orElseThrow(()-> new NotFoundException(tId));
        tdao.delete(found);
    }
}
