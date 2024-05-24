package wendydeluca.capstone.payloads.user;

import jakarta.validation.constraints.*;

public record UserDTO(@NotEmpty(message = "Name cannot be empty")
                      @NotNull(message = "Name cannot be null")
                      @Size(min = 2, max = 20, message = "Name must be between 2 and 30 chars")
                      String name,
                      @NotNull(message = "Surname cannot be null")
                      @NotEmpty(message = "Surname cannot be empty")
                      @Size(min = 3, max = 20, message = "Surname must be between 3 and 30 chars")
                      String surname,
                      @NotNull(message = "Email cannot be null")
                      @Email(message = "Insert a valid email format")
                      String email,
                      @NotBlank(message = "Password must not be be null")
                      @NotNull(message = "Password cannot be null")
                      @NotEmpty(message = "Password cannot be empty")
                      @Size(min = 15, message = "Password must be at least 15 chars long!")
                      String password) {
}
