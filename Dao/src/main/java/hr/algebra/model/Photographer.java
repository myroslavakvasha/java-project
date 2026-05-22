/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

/**
 *
 * @author kvash
 */
public final class Photographer implements Comparable<Photographer>{
    
    private int id;
    private String name;
    private String surname;

    public Photographer() {
    }

    public Photographer(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Photographer(int id, String name, String surname) {
        this(name, surname);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Photographer other = (Photographer) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }

    @Override
    public int compareTo(Photographer o) {
        return Integer.valueOf(this.id).compareTo(o.id);
    }
}
