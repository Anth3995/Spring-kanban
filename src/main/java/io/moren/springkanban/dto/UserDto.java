package io.moren.springkanban.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
