package wendydeluca.capstone.payloads.traveller;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

public record TravellerDTO(@NotEmpty(message = "Name cannot be empty")
                           @NotNull(message = "Name cannot be null")
                           @Size(min = 2, max = 20, message = "Name must be between 2 and 30 chars")
                           String name,
                           @NotNull(message = "Surname cannot be null")
                           @NotEmpty(message = "Surname cannot be empty")
                           @Size(min = 3, max = 20, message = "Surname must be between 3 and 30 chars")
                           String surname,
                           @Min(value = 18)
                           @NotEmpty(message = "The age field must be filled")
                           @Max(value = 35)
                           String age,
                           @NotEmpty(message = "Image must not be empty")
                           @NotNull(message = "Image cannot be null")
                           String avatar,
                           @NotNull(message = "Email cannot be null")
                           @Email(message = "Insert a valid email format")
                           String email,
                           @NotEmpty(message = "Country must not be empty")
                           @NotNull(message = "Country cannot be null")
                           String country,
                           @NotEmpty(message = "TravelDestination must not be empty")
                           @NotNull(message = "TravelDestination cannot be null")
                           String travelDestination,
                           @NotEmpty(message = "Description must not be empty")
                           @NotNull(message = "Description cannot be null")
//                           @Min(value =20)
//                           @Max(value = 100)
                           String description,
                           @NotEmpty(message = "Languages must not be empty")
                           @NotNull(message = "Languages cannot be null")
                           String spokenLanguages,
                           @NotEmpty(message = "Interests must not be empty")
                           @NotNull(message = "Interests cannot be null")
                           String interests

                           ) {
}
