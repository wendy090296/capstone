package wendydeluca.capstone.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import wendydeluca.capstone.entities.Host;
import wendydeluca.capstone.exceptions.NotFoundException;
import wendydeluca.capstone.payloads.host.HostDTO;
import wendydeluca.capstone.repositories.HostDAO;

import java.util.UUID;

@Service
public class HostService {
    @Autowired
    public HostDAO hdao;

    public Page<Host> findAllHosts(int page, int size, String sortBy){
        if(size>50) size=50;
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy));
        return hdao.findAll(pageable);

    }

    public Host findById(UUID hId){
       return hdao.findById(hId).orElseThrow(()->new NotFoundException(hId));
    }

    public Host saveHost(HostDTO body){
        Host host = new Host();
        host.setName(body.name());
        host.setSurname(body.surname());
        host.setAge(body.age());
        host.setLocation(body.location());
        host.setProjectDescription(body.projectDescription());
        host.setAvatar(body.avatar());
        host.setSpokenLanguages(body.spokenLanguages());
        host.setMaxOccupancy(body.maxOccupancy());
        host.setWorkingHours(body.workingHours());
        host.setWifi(body.wifi());
        host.setPets(body.pets());
        return hdao.save(host);
    }

    public Host updateHost(UUID hId,HostDTO body){
        Host found = findById(hId);
        found.setName(body.name());
        found.setSurname(body.surname());
        found.setAge(body.age());
        found.setLocation(body.location());
        found.setProjectDescription(body.projectDescription());
        found.setAvatar(body.avatar());
        found.setSpokenLanguages(body.spokenLanguages());
        found.setMaxOccupancy(body.maxOccupancy());
        found.setWorkingHours(body.workingHours());
        found.setWifi(body.wifi());
        found.setPets(body.pets());
        return hdao.save(found);
    }

    public void deleteHost(UUID hId){
        Host found = this.findById(hId);
        hdao.delete(found);
    }


}
