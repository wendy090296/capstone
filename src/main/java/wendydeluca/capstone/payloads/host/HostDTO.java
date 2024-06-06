package wendydeluca.capstone.payloads.host;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

public record HostDTO(@NotEmpty(message = "Name cannot be empty")
                      @NotNull(message = "Name cannot be null")
                      @Size(min = 2, max = 20, message = "Name must be between 2 and 30 chars")
                      String name,
                      @NotNull(message = "Surname cannot be null")
                      @NotEmpty(message = "Surname cannot be empty")
                      @Size(min = 3, max = 20, message = "Surname must be between 3 and 30 chars")
                      String surname,
                      @NotNull(message = "Email cannot be null")
                      @NotEmpty(message = "Email cannot be empty")
                      @Email(message = "Please insert a valid email format!")
                      String email,

                      @NotEmpty(message = "The age field must be filled")
                      @Max(value=60)
                      String age,
                      @NotEmpty(message = "Location must not be empty")
                      @NotNull(message = "Location cannot be null")
                      String location,
                      @NotEmpty(message = "ProjectDescription must not be empty")
                      @NotNull(message = "ProjectDescription cannot be null")
                      @Column(length = 3000)
                      String projectDescription,
                      @NotEmpty(message = "Image must not be empty")
                      @NotNull(message = "Image cannot be null")
                      String avatar,
                      @NotEmpty(message = "Languages must not be empty")
                      @NotNull(message = "Languages cannot be null")
                      String spokenLanguages,

                      @NotEmpty(message = "MaxOccupancy must not be empty")
                      @NotNull(message = "MaxOccupancy must not be null")
                      String maxOccupancy,

                      @NotEmpty(message = "Working hours must not be empty")
                      @NotNull(message = "Working hours must not be null")
                      String workingHours,
                      @NotEmpty(message = "Wifi must not be empty")
                      @NotNull(message = "Wifi hours must not be null")
                      String wifi,
//                      @NotEmpty(message = "Pets field must not be empty")
//                      @NotNull(message = "Pets field must not be null")
                        String pets,
//                      @NotEmpty(message = "Country field must not be empty")
//                      @NotNull(message = "Country field must not be null")
                      String flag,
                      @NotEmpty(message = "Password field must not be empty")
                      @NotNull(message = "Password must not be null")
                      @Size(message = "Password must be at least 8 chars.")
                      String password) {
}
