/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

/**
 *
 * @author kvash
 */
public final class Mission implements Comparable<Mission>{
    
    private int id;
    private String name;
    private String description;

    public Mission() {
    }

    public Mission(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Mission(int id, String name, String description) {
        this(name, description);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
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
        final Mission other = (Mission) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Mission o) {
        return Integer.valueOf(this.id).compareTo(o.id);
    }
}
