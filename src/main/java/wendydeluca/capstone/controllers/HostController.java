package wendydeluca.capstone.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import wendydeluca.capstone.entities.Host;
import wendydeluca.capstone.payloads.host.HostDTO;
import wendydeluca.capstone.services.HostService;

import java.util.UUID;

@RestController
@RequestMapping("/hosts")
public class HostController {

    @Autowired
    public HostService hService;

    @GetMapping
    public Page<Host> getAllHosts(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "name") String sortBy){
        return hService.findAllHosts(page,size,sortBy);
    }


    @GetMapping("/{hId}")
    public Host getById(@PathVariable UUID hId){
        return hService.findById(hId);
    }

    @PostMapping
    public Host saveHost(@RequestBody HostDTO body){
       return  hService.saveHost(body);
    }

    @PutMapping("/{hId}")
    public Host updateHost(@PathVariable UUID hId, @RequestBody HostDTO body){
        return hService.updateHost(hId,body);
    }

    @DeleteMapping("/{hId}")
    public void deleteHost(@PathVariable UUID hId){
        hService.deleteHost(hId);
    }


}
