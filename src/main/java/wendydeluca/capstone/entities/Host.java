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
    private String wifi;
    private String pets;
    @ManyToOne
    @JoinColumn(name = "traveller_id")
    @JsonIgnore
    @ToString.Exclude
    private Traveller traveller;
    @OneToOne
    private User user;

    public Host(String name,String surname,String age,String location,String projectDescription,String avatar,
                String spokenLanguages,
                String maxOccupancy,String workingHours,
                String wifi,
                String pets){
        this.name=name;
        this.surname=surname;
        this.age=age;
        this.location=location;
        this.projectDescription=projectDescription;
        this.avatar=avatar;
        this.spokenLanguages=spokenLanguages;
        this.maxOccupancy=maxOccupancy;
        this.workingHours=workingHours;
        this.wifi=wifi;
        this.pets=pets;
    }

}
