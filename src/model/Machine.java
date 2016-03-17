/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "MACHINE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Machine.findAll", query = "SELECT m FROM Machine m"),
    @NamedQuery(name = "Machine.findById", query = "SELECT m FROM Machine m WHERE m.id = :id"),
    @NamedQuery(name = "Machine.deleteAll", query = "DELETE FROM Machine")})
public class Machine implements Serializable {

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
    @Basic(optional = false)
    @Column(name = "penalty")
    private double penalty;
    @OneToMany(mappedBy = "idMachine")
    private Collection<Task> tasks;
    @JoinColumn(name = "id_workshop", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Workshop workshop;
    
    public Machine() {
        tasks = new ArrayList<>();
        
        available = new Date(System.currentTimeMillis());
    }

    public Machine(Integer id) {
        this();
        this.id = id;
    }

    public Machine(Integer id, Date available, double penalty) {
        this();
        this.id = id;
        this.available = available;
        this.penalty = penalty;
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

    public double getPenalty() {
        return penalty;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }

    @XmlTransient
    public Collection<Task> getTasks() {
        return tasks;
    }

    public boolean addTask(Task task) {
        Calendar c = Calendar.getInstance();
        c.setTime(available);
        
        if(!tasks.add(task))
            return false;
        
        if(task.getIdMachine() != null)
            task.getIdMachine().tasks.remove(task);
        task.setIdMachine(this);
        task.setStart(available);
        if(task.getEnd().after(task.getLimit())) {
            double delay = (task.getEnd().getTime() - task.getLimit().getTime()) / 60000; // Delay in minutes
            penalty += delay * task.getPenalty();
        }
            
        
        c.add(Calendar.MINUTE, task.getLength());
        available = c.getTime();
        
        return true;
    }

    public Workshop getWorkshop() {
        return workshop;
    }

    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
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
        if (!(object instanceof Machine)) {
            return false;
        }
        Machine other = (Machine) object;
        if ((this.tasks == null && other.tasks != null) || (this.tasks != null && !this.tasks.equals(other.tasks))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String result = "Machine: \n" +
                "  available: " + available + "\n" +
                "  penalty  : " + penalty   + "\n" +
                "  tasks    : {\n";
        
        for(Task t : tasks) {
            result += t+"\n";
        }
        result += "}";
        
        return result;
    }
    
}
