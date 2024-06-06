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
import wendydeluca.capstone.entities.Traveller;
import wendydeluca.capstone.exceptions.BadRequestException;
import wendydeluca.capstone.payloads.traveller.TravellerDTO;
import wendydeluca.capstone.payloads.traveller.TravellerResponseDTO;
import wendydeluca.capstone.services.TravellerService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/travellers")
public class TravellerController {
    @Autowired
    public TravellerService tService;

    @GetMapping
    public List<Traveller> getAllTravellers(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size,
                                            @RequestParam(defaultValue = "name") String sortBy){
    return tService.findAllTravellers(page,size,sortBy);
}

@GetMapping("/{tId}")
    public Traveller getById(@PathVariable UUID tId){
    return tService.findById(tId);
}

@PostMapping
//@PreAuthorize("hasAuthority('ADMIN')")
@ResponseStatus(HttpStatus.CREATED)
    public TravellerResponseDTO saveTraveller(@RequestBody @Validated TravellerDTO body, BindingResult validation){
        if(validation.hasErrors()){
            System.out.println(validation.getAllErrors());
            throw new BadRequestException(validation.getAllErrors());
        }
    return new TravellerResponseDTO(this.tService.saveTraveller(body).getId());
}

@PutMapping("/{tId}")
//@PreAuthorize("hasAuthority('ADMIN')")
@ResponseStatus(HttpStatus.ACCEPTED)
    public Traveller getByIdAndUpdate(@PathVariable UUID tId, @RequestBody TravellerDTO body){
   return tService.updateTraveller(tId, body);

}

@DeleteMapping("/{tId}")
//@PreAuthorize("hasAuthority('ADMIN')")
@ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTraveller(@PathVariable UUID tId){
    tService.deleteTraveller(tId);

}
    @GetMapping("/me")
    public Traveller getProfile(@AuthenticationPrincipal Traveller authTraveller){
        return authTraveller;
    }



    @PutMapping("/me/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED  )
    public Traveller updateProfile(@PathVariable UUID id, @RequestBody TravellerDTO body){
        return tService.updateTraveller(id,body);
    }


   @DeleteMapping("/me/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfile(@AuthenticationPrincipal Traveller authTraveller){
        tService.deleteTraveller(authTraveller.getId());
    }



}

