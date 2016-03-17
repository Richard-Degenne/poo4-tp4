/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author richou
 */
@Entity
@Table(name = "WORKSHOP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Workshop.findAll", query = "SELECT w FROM Workshop w"),
    @NamedQuery(name = "Workshop.findById", query = "SELECT w FROM Workshop w WHERE w.id = :id"),
    @NamedQuery(name = "Workshop.findAllOrderedByAvailable", query = "SELECT w FROM Workshop w ORDER BY w.available"),
    @NamedQuery(name = "Workshop.deleteAll", query = "DELETE FROM Workshop")})

public class Workshop implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "available")
    @Temporal(TemporalType.TIMESTAMP)
    private Date available;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "workshop")
    private Collection<Machine> machines;

    public Workshop() {
        machines = new HashSet<>();
        
        available = new Date(System.currentTimeMillis());
    }

    public Workshop(Integer id) {
        this();
        this.id = id;
    }

    public Workshop(Integer id, Date available) {
        this();
        this.id = id;
        this.available = available;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getAvailable() {
        return available;
    }

    public void setAvailable(Date available) {
        this.available = available;
    }

    @XmlTransient
    public Collection<Machine> getMachines() {
        return machines;
    }
    
    public boolean addMachine(Machine machine) {
        if(!machines.add(machine))
            return false;

        if(machine.getWorkshop() != null)
            machine.getWorkshop().machines.remove(machine);
        machine.setWorkshop(this);
        return true;
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
        if (!(object instanceof Workshop)) {
            return false;
        }
        Workshop other = (Workshop) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String result = "Workshop:\n" +
                "  available: " + available + "\n" +
                "  machines : { \n";
        
        for(Machine m : machines) {
            result += m+"\n";
        }
        result += "}";
        
        return result;
    }
    
}
