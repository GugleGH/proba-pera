/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nosov.client.messages.db;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author nosov
 */
@Entity
@Table(name = "users_types", catalog = "mydbProbaPera", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsersTypes.findAll", query = "SELECT u FROM UsersTypes u"),
    @NamedQuery(name = "UsersTypes.findById", query = "SELECT u FROM UsersTypes u WHERE u.id = :id"),
    @NamedQuery(name = "UsersTypes.findByName", query = "SELECT u FROM UsersTypes u WHERE u.name = :name"),
    @NamedQuery(name = "UsersTypes.findByDescription", query = "SELECT u FROM UsersTypes u WHERE u.description = :description")})
public class UsersTypes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Short id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usersTypesId", fetch = FetchType.LAZY)
    private Collection<Users> usersCollection;

    public UsersTypes() {
    }

    public UsersTypes(Short id) {
        this.id = id;
    }

    public UsersTypes(Short id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
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

    @XmlTransient
    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsersTypes)) {
            return false;
        }
        UsersTypes other = (UsersTypes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.nosov.server.db.tables.UsersTypes[ id=" + id + " ]";
    }
    
}
