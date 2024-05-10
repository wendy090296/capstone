package wendydeluca.capstone.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import wendydeluca.capstone.entities.Traveller;
import wendydeluca.capstone.payloads.traveller.TravellerDTO;
import wendydeluca.capstone.services.TravellerService;

import java.util.UUID;

@RestController
@RequestMapping("/travellers")
public class TravellerController {
    @Autowired
    public TravellerService tService;

@GetMapping
    public Page<Traveller> getAllTravellers(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size,
                                            @RequestParam(defaultValue = "name") String sortBy){
    return tService.findAllTravellers(page,size,sortBy);
}

@GetMapping("/{tId}")
    public Traveller getById(@PathVariable UUID tId){
    return tService.findById(tId);
}

@PostMapping
    public Traveller saveTraveller(@RequestBody TravellerDTO body){
    return tService.saveTraveller(body);
}

@PutMapping("/{tId}")
    public Traveller getByIdAndUpdate(@PathVariable UUID tId, @RequestBody TravellerDTO body){
   return tService.updateTraveller(tId, body);

}

@DeleteMapping("/{tId}")
    public void deleteTraveller(@PathVariable UUID tId){
    tService.deleteTraveller(tId);

}

}
