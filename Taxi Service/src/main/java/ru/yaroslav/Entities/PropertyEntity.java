package ru.yaroslav.Entities;

import javax.persistence.*;

/**
 * Created by Yaroslav on 29.12.2016.
 */
@Entity
@Table(name = "property", schema = "lab_db", catalog = "")
public class PropertyEntity {
    private int idThing;
    private String typeProperty;

    @Id
    @Column(name = "id_thing")
    public int getIdThing() {
        return idThing;
    }

    public void setIdThing(int idThing) {
        this.idThing = idThing;
    }

    @Basic
    @Column(name = "Type_property")
    public String getTypeProperty() {
        return typeProperty;
    }

    public void setTypeProperty(String typeProperty) {
        this.typeProperty = typeProperty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PropertyEntity that = (PropertyEntity) o;

        if (idThing != that.idThing) return false;
        if (typeProperty != null ? !typeProperty.equals(that.typeProperty) : that.typeProperty != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idThing;
        result = 31 * result + (typeProperty != null ? typeProperty.hashCode() : 0);
        return result;
    }
}
