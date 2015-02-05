/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nosov.client.messages.db;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nosov
 */
@Entity
@Table(name = "jobs", catalog = "mydbProbaPera", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jobs.findAll", query = "SELECT j FROM Jobs j"),
    @NamedQuery(name = "Jobs.findById", query = "SELECT j FROM Jobs j WHERE j.id = :id"),
    @NamedQuery(name = "Jobs.findByCompany", query = "SELECT j FROM Jobs j WHERE j.company = :company"),
    @NamedQuery(name = "Jobs.findByRegion", query = "SELECT j FROM Jobs j WHERE j.region = :region"),
    @NamedQuery(name = "Jobs.findByCompanyfield", query = "SELECT j FROM Jobs j WHERE j.companyfield = :companyfield"),
    @NamedQuery(name = "Jobs.findByPosition", query = "SELECT j FROM Jobs j WHERE j.position = :position"),
    @NamedQuery(name = "Jobs.findByBeginning", query = "SELECT j FROM Jobs j WHERE j.beginning = :beginning"),
    @NamedQuery(name = "Jobs.findByCompletion", query = "SELECT j FROM Jobs j WHERE j.completion = :completion"),
    @NamedQuery(name = "Jobs.findByFunction", query = "SELECT j FROM Jobs j WHERE j.function = :function")})
public class Jobs implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "company")
    private String company;
    @Size(max = 45)
    @Column(name = "region")
    private String region;
    @Size(max = 45)
    @Column(name = "companyfield")
    private String companyfield;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "position")
    private String position;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "beginning")
    private String beginning;
    @Size(max = 45)
    @Column(name = "completion")
    private String completion;
    @Size(max = 45)
    @Column(name = "function")
    private String function;
    @JoinColumn(name = "departments_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Departments departmentsId;
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Users usersId;

    public Jobs() {
    }

    public Jobs(Long id) {
        this.id = id;
    }

    public Jobs(Long id, String company, String position, String beginning) {
        this.id = id;
        this.company = company;
        this.position = position;
        this.beginning = beginning;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCompanyfield() {
        return companyfield;
    }

    public void setCompanyfield(String companyfield) {
        this.companyfield = companyfield;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getBeginning() {
        return beginning;
    }

    public void setBeginning(String beginning) {
        this.beginning = beginning;
    }

    public String getCompletion() {
        return completion;
    }

    public void setCompletion(String completion) {
        this.completion = completion;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public Departments getDepartmentsId() {
        return departmentsId;
    }

    public void setDepartmentsId(Departments departmentsId) {
        this.departmentsId = departmentsId;
    }

    public Users getUsersId() {
        return usersId;
    }

    public void setUsersId(Users usersId) {
        this.usersId = usersId;
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
        if (!(object instanceof Jobs)) {
            return false;
        }
        Jobs other = (Jobs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.nosov.server.db.tables.Jobs[ id=" + id + " ]";
    }
    
}
