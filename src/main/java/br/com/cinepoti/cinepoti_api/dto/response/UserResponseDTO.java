package br.com.cinepoti.cinepoti_api.dto.response;

import br.com.cinepoti.cinepoti_api.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public record UserResponseDTO(
        Long id,
        String username,
        String email,
        String name,
        String cpf,
        String phone,
        Gender gender,
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate birthdate
) {}
