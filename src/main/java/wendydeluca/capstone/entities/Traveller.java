package wendydeluca.capstone.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "travellers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Traveller  {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String name;
    private String surname;
    private String age;
    private String email;
    private String country;
    @Column(name = "travel_destination")
    private String travelDestination;
    private String description;
    @Column(name = "spoken_languages")
    private String spokenLanguages;
    private String avatar;
    private String interests;
    @OneToMany(mappedBy = "traveller")
    @JsonIgnore
    @ToString.Exclude
    private List<Host> host;
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE} )
    private User user;

    public Traveller(String name, String surname,String age,String email,String country,String travelDestination,String description,String spokenLanguages,String avatar,String interests){
this.name=name;
this.surname=surname;
this.age=age;
this.email=email;
this.country=country;
this.travelDestination=travelDestination;
this.description=description;
this.spokenLanguages=spokenLanguages;
this.avatar=avatar;
this.interests=interests;
    }

}
