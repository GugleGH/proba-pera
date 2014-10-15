/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nosov.server.db.tables;

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
@Table(name = "addresses_types", catalog = "mydbProbaPera", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AddressesTypes.findAll", query = "SELECT a FROM AddressesTypes a"),
    @NamedQuery(name = "AddressesTypes.findById", query = "SELECT a FROM AddressesTypes a WHERE a.id = :id"),
    @NamedQuery(name = "AddressesTypes.findByName", query = "SELECT a FROM AddressesTypes a WHERE a.name = :name"),
    @NamedQuery(name = "AddressesTypes.findByDescription", query = "SELECT a FROM AddressesTypes a WHERE a.description = :description")})
public class AddressesTypes implements Serializable {
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "addressesTypesId", fetch = FetchType.LAZY)
    private Collection<Addresses> addressesCollection;

    public AddressesTypes() {
    }

    public AddressesTypes(Short id) {
        this.id = id;
    }

    public AddressesTypes(Short id, String name, String description) {
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
    public Collection<Addresses> getAddressesCollection() {
        return addressesCollection;
    }

    public void setAddressesCollection(Collection<Addresses> addressesCollection) {
        this.addressesCollection = addressesCollection;
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
        if (!(object instanceof AddressesTypes)) {
            return false;
        }
        AddressesTypes other = (AddressesTypes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.nosov.server.db.tables.AddressesTypes[ id=" + id + " ]";
    }
    
}
