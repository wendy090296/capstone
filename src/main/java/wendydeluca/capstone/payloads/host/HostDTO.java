package wendydeluca.capstone.payloads.host;

import jakarta.validation.constraints.*;

public record HostDTO(@NotEmpty(message = "Name cannot be empty")
                      @NotNull(message = "Name cannot be null")
                      @Size(min = 2, max = 20, message = "Name must be between 2 and 30 chars")
                      String name,
                      @NotNull(message = "Surname cannot be null")
                      @NotEmpty(message = "Surname cannot be empty")
                      @Size(min = 3, max = 20, message = "Surname must be between 3 and 30 chars")
                      String surname,
                      @Min(value = 18)
                      @NotEmpty(message = "The age field must be filled")
                      @Max(value=60)
                      String age,
                      @NotEmpty(message = "Location must not be empty")
                      @NotNull(message = "Location cannot be null")
                      String location,
                      @NotEmpty(message = "ProjectDescription must not be empty")
                      @NotNull(message = "ProjectDescription cannot be null")
                      @Min(value =20)
                      @Max(value = 100)
                      String projectDescription,
                      @NotEmpty(message = "Image must not be empty")
                      @NotNull(message = "Image cannot be null")
                      String avatar,
                      @NotEmpty(message = "Languages must not be empty")
                      @NotNull(message = "Languages cannot be null")
                      String spokenLanguages,
                      @Min(value=1)
                      @Max(value=5)
                      @NotEmpty(message = "MaxOccupancy must not be empty")
                      @NotNull(message = "MaxOccupancy must not be null")
                      String maxOccupancy,
                      @Min(value=3)
                      @Max(value=5)
                      @NotEmpty(message = "Working hours must not be empty")
                      @NotNull(message = "Working hours must not be null")
                      String workingHours,
                      @NotEmpty(message = "Wifi must not be empty")
                      @NotNull(message = "Wifi hours must not be null")
                      String wifi,
                      @NotEmpty(message = "Pets field must not be empty")
                      @NotNull(message = "Pets field must not be null")
                      String pets) {
}
