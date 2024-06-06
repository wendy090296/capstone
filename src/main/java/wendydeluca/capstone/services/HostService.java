package wendydeluca.capstone.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import wendydeluca.capstone.entities.Host;
import wendydeluca.capstone.entities.Role;
import wendydeluca.capstone.entities.User;
import wendydeluca.capstone.exceptions.NotFoundException;
import wendydeluca.capstone.payloads.host.HostDTO;
import wendydeluca.capstone.repositories.HostDAO;

import java.util.UUID;

@Service
public class HostService {
    @Autowired
    public HostDAO hdao;
    @Autowired
    PasswordEncoder bcrypt;

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
        host.setEmail(body.email());
        host.setAge(body.age());
        host.setLocation(body.location());
        host.setProjectDescription(body.projectDescription());
        host.setAvatar("https://t3.ftcdn.net/jpg/02/97/70/22/240_F_297702222_mCM7M1DNxx4bXubVXUdYc03bBc1vpBaS.jpg");
        host.setSpokenLanguages(body.spokenLanguages());
        host.setMaxOccupancy(body.maxOccupancy());
        host.setWorkingHours(body.workingHours());
        host.setFlag("https://t3.ftcdn.net/jpg/01/08/88/60/360_F_108886037_5RMqRn0kavfVgfBFgeKWXdxlRSc6myci.jpg");
        host.setWifi(body.wifi());
        host.setPets(body.pets());
        User user  = new User();
        user.setEmail(host.getEmail());
        user.setPassword(bcrypt.encode(body.password()));
        user.setName(host.getName());
        user.setSurname(host.getSurname());
        user.setRole(Role.HOST);
        host.setUser(user);
        return hdao.save(host);
    }

    public Host updateHost(UUID hId,HostDTO body){
        Host found = findById(hId);
        found.setName(body.name());
        found.setSurname(body.surname());
//        found.setAge(body.age());
        found.setLocation(body.location());
        found.setProjectDescription(body.projectDescription());
//        found.setAvatar(body.avatar());
        found.setSpokenLanguages(body.spokenLanguages());
        found.setMaxOccupancy(body.maxOccupancy());
//        found.setFlag(body.flag());
        found.setWorkingHours(body.workingHours());
        found.setWifi(body.wifi());
        found.setPets(body.pets());
        found.getUser().setEmail(body.email());
        found.getUser().setName(body.name());
        found.getUser().setSurname(body.surname());
        return hdao.save(found);
    }

    public void deleteHost(UUID hId){
        Host found = this.findById(hId);
        hdao.delete(found);
    }


}
