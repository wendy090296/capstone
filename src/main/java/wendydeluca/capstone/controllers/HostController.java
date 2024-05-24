package wendydeluca.capstone.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import wendydeluca.capstone.entities.Host;
import wendydeluca.capstone.entities.Traveller;
import wendydeluca.capstone.exceptions.BadRequestException;
import wendydeluca.capstone.payloads.host.HostDTO;
import wendydeluca.capstone.payloads.host.HostResponseDTO;
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
//    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public HostResponseDTO saveHost(@RequestBody @Validated HostDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            System.out.println(validation.getAllErrors());
            throw new BadRequestException(validation.getAllErrors());
        }
        System.out.println(body);
        return new HostResponseDTO(this.hService.saveHost(body).getId());
    }

    @PutMapping("/{hId}")
//    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Host updateHost(@PathVariable UUID hId, @RequestBody HostDTO body){
        return hService.updateHost(hId,body);
    }

    @DeleteMapping("/{hId}")
//    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHost(@PathVariable UUID hId){
        hService.deleteHost(hId);
    }

    @GetMapping("/me")
    public Host getProfile(@AuthenticationPrincipal Host authHost){
        return authHost;
    }

    @PutMapping("/me")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Host updateProfile(@AuthenticationPrincipal Host authHost, @RequestBody HostDTO body){
        return hService.updateHost(authHost.getId(),body);
    }

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfile(@AuthenticationPrincipal Host authHost){
        hService.deleteHost(authHost.getId());
    }




}
