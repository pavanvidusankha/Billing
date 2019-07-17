/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Billing.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pavan
 */
@Entity
@Table(name = "CASHPAYMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cashpayment.findAll", query = "SELECT c FROM Cashpayment c")
    , @NamedQuery(name = "Cashpayment.findByPayid", query = "SELECT c FROM Cashpayment c WHERE c.payid = :payid")
    , @NamedQuery(name = "Cashpayment.findByBillid", query = "SELECT c FROM Cashpayment c WHERE c.billid = :billid")
    , @NamedQuery(name = "Cashpayment.findByGivenmoney", query = "SELECT c FROM Cashpayment c WHERE c.givenmoney = :givenmoney")
    , @NamedQuery(name = "Cashpayment.findByAmount", query = "SELECT c FROM Cashpayment c WHERE c.amount = :amount")})
public class Cashpayment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PAYID")
    private Integer payid;
    @Column(name = "BILLID")
    private Integer billid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "GIVENMONEY")
    private Double givenmoney;
    @Column(name = "AMOUNT")
    private Double amount;

    public Cashpayment() {
    }

    public Cashpayment(Integer payid) {
        this.payid = payid;
    }

    public Integer getPayid() {
        return payid;
    }

    public void setPayid(Integer payid) {
        this.payid = payid;
    }

    public Integer getBillid() {
        return billid;
    }

    public void setBillid(Integer billid) {
        this.billid = billid;
    }

    public Double getGivenmoney() {
        return givenmoney;
    }

    public void setGivenmoney(Double givenmoney) {
        this.givenmoney = givenmoney;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (payid != null ? payid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cashpayment)) {
            return false;
        }
        Cashpayment other = (Cashpayment) object;
//        if ((this.payid == null && other.payid != null) || (this.payid != null && !this.payid.equals(other.payid))) {
//            return false;
//        }
        return true;
    }

    @Override
    public String toString() {
        return "Billing.model.Cashpayment[ payid=" + payid + " ]";
    }
    
}
