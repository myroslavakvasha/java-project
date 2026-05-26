/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package hr.algebra.model;

import java.util.Optional;

/**
 *
 * @author kvash
 */
public enum Role {
    
    ADMIN("ADMIN"),
    USER("USER");
    
    private final String name;
    
    private Role(String name){
        this.name = name;
    }
    
    public static Optional<Role> from(String name) {
        for (Role value : values()) {
            if (value.name.equals(name)) {
                return Optional.of(value);
            }
        }
        return Optional.empty();
    }
}

