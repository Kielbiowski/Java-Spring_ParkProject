package com.kielbiowski.parkproject.dto;

import com.kielbiowski.parkproject.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Data
public class RoleDTO {
    private Integer id;

    @NotNull
    private String name;

    private List<UserDTO> userDTOs;

    public static Role toRole(RoleDTO roleDTO) {
        return new Role(
                roleDTO.getId(),
                roleDTO.getName(),
                //Null-safe stream done with Java 9' Stream.ofNullable
                Stream.ofNullable(roleDTO.getUserDTOs())
                        .flatMap(Collection::stream)
                        .map(UserDTO::toUser)
                        .collect(Collectors.toList()));
    }

    public static RoleDTO toRoleDTO(Role role) {
        return new RoleDTO(
                role.getId(),
                role.getName(),
                //Null-safe streams done with Java 8' Optionals
                Optional.ofNullable(role.getUsers())
                        .stream()
                        .flatMap(Collection::stream)
                        .map(UserDTO::toUserDTO)
                        .collect(Collectors.toList()));
    }
}
