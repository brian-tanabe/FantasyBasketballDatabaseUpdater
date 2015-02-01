package com.btanabe2.fbdu.dm.models;

import javax.persistence.*;

/**
 * Created by brian on 11/5/14.
 */
@Entity
@Table(name = "positions")
public class PositionsEntity {
    private int id;
    private String fullname;
    private String abbreviation;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "fullname")
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Basic
    @Column(name = "abbreviation")
    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PositionsEntity that = (PositionsEntity) o;

        if (id != that.id) return false;
        if (abbreviation != null ? !abbreviation.equals(that.abbreviation) : that.abbreviation != null) return false;
        if (fullname != null ? !fullname.equals(that.fullname) : that.fullname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (fullname != null ? fullname.hashCode() : 0);
        result = 31 * result + (abbreviation != null ? abbreviation.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("ID=[%d]; abbreviation=[%s]; fullname=[%s]", id, abbreviation, fullname);
    }
}
