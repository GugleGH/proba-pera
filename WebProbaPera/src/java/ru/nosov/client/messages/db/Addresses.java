/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nosov.client.messages.db;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nosov
 */
@Entity
@Table(name = "addresses", catalog = "mydbProbaPera", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Addresses.findAll", query = "SELECT a FROM Addresses a"),
    @NamedQuery(name = "Addresses.findById", query = "SELECT a FROM Addresses a WHERE a.id = :id"),
    @NamedQuery(name = "Addresses.findByStreet1", query = "SELECT a FROM Addresses a WHERE a.street1 = :street1"),
    @NamedQuery(name = "Addresses.findByStreet2", query = "SELECT a FROM Addresses a WHERE a.street2 = :street2"),
    @NamedQuery(name = "Addresses.findByCity", query = "SELECT a FROM Addresses a WHERE a.city = :city"),
    @NamedQuery(name = "Addresses.findByRegion", query = "SELECT a FROM Addresses a WHERE a.region = :region"),
    @NamedQuery(name = "Addresses.findByPostcode", query = "SELECT a FROM Addresses a WHERE a.postcode = :postcode"),
    @NamedQuery(name = "Addresses.findByCountryCode", query = "SELECT a FROM Addresses a WHERE a.countryCode = :countryCode"),
    @NamedQuery(name = "Addresses.findByCreatedAt", query = "SELECT a FROM Addresses a WHERE a.createdAt = :createdAt"),
    @NamedQuery(name = "Addresses.findByUpdatedAt", query = "SELECT a FROM Addresses a WHERE a.updatedAt = :updatedAt")})
public class Addresses implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "street1")
    private String street1;
    @Size(max = 255)
    @Column(name = "street2")
    private String street2;
    @Size(max = 255)
    @Column(name = "city")
    private String city;
    @Size(max = 255)
    @Column(name = "region")
    private String region;
    @Size(max = 255)
    @Column(name = "postcode")
    private String postcode;
    @Size(max = 2)
    @Column(name = "country_code")
    private String countryCode;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Users usersId;
    @JoinColumn(name = "addresses_types_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AddressesTypes addressesTypesId;

    public Addresses() {
    }

    public Addresses(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Users getUsersId() {
        return usersId;
    }

    public void setUsersId(Users usersId) {
        this.usersId = usersId;
    }

    public AddressesTypes getAddressesTypesId() {
        return addressesTypesId;
    }

    public void setAddressesTypesId(AddressesTypes addressesTypesId) {
        this.addressesTypesId = addressesTypesId;
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
        if (!(object instanceof Addresses)) {
            return false;
        }
        Addresses other = (Addresses) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.nosov.server.db.tables.Addresses[ id=" + id + " ]";
    }
    
}
