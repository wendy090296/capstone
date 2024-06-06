package wendydeluca.capstone.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "hosts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Host {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String name;
    private String surname;
    private String email;
    private String age;
    private String location;
    @Column(name = "project_description")
    private String projectDescription;
    private String avatar;
    @Column(name = "spoken_languages")
    private String spokenLanguages;
    @Column(name = "max_occupancy")
    private String maxOccupancy;
    @Column(name = "working_hours")
    private String workingHours;
    private String flag;
    private String wifi;
    private String pets;
    @ManyToOne
    @JoinColumn(name = "traveller_id")
    @JsonIgnore
    @ToString.Exclude
    private Traveller traveller;
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    public User user;

    public Host(String name,String surname,String email,String age,String location,String projectDescription,String avatar,
                String spokenLanguages,
                String maxOccupancy,String workingHours,
                String wifi,
                String pets,
                String flag){
        this.name=name;
        this.surname=surname;
        this.email=email;
        this.age=age;
        this.location=location;
        this.projectDescription=projectDescription;
        this.avatar=avatar;
        this.spokenLanguages=spokenLanguages;
        this.maxOccupancy=maxOccupancy;
        this.workingHours=workingHours;
        this.flag=flag;
        this.wifi=wifi;
        this.pets=pets;
    }

}
