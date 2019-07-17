/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pavan
 */
@Entity
@Table(name = "CHEQPAYMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cheqpayment.findAll", query = "SELECT c FROM Cheqpayment c")
    , @NamedQuery(name = "Cheqpayment.findByPayid", query = "SELECT c FROM Cheqpayment c WHERE c.payid = :payid")
    , @NamedQuery(name = "Cheqpayment.findByBillid", query = "SELECT c FROM Cheqpayment c WHERE c.billid = :billid")
    , @NamedQuery(name = "Cheqpayment.findByAmount", query = "SELECT c FROM Cheqpayment c WHERE c.amount = :amount")
    , @NamedQuery(name = "Cheqpayment.findByCheqdate", query = "SELECT c FROM Cheqpayment c WHERE c.cheqdate = :cheqdate")
    , @NamedQuery(name = "Cheqpayment.findByBank", query = "SELECT c FROM Cheqpayment c WHERE c.bank = :bank")
    , @NamedQuery(name = "Cheqpayment.findByCheqno", query = "SELECT c FROM Cheqpayment c WHERE c.cheqno = :cheqno")})
public class Cheqpayment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PAYID")
    private Integer payid;
    @Column(name = "BILLID")
    private Integer billid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "AMOUNT")
    private Double amount;
    @Column(name = "CHEQDATE")
    @Temporal(TemporalType.DATE)
    private String cheqdate;
    @Column(name = "BANK")
    private String bank;
    @Column(name = "CHEQNO")
    private String cheqno;

    public Cheqpayment() {
    }

    public Cheqpayment(Integer payid) {
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCheqdate() {
        return cheqdate;
    }

    public void setCheqdate(String cheqdate) {
        this.cheqdate = cheqdate;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getCheqno() {
        return cheqno;
    }

    public void setCheqno(String cheqno) {
        this.cheqno = cheqno;
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
        if (!(object instanceof Cheqpayment)) {
            return false;
        }
        Cheqpayment other = (Cheqpayment) object;
        if ((this.payid == null && other.payid != null) || (this.payid != null && !this.payid.equals(other.payid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Billing.model.Cheqpayment[ payid=" + payid + " ]";
    }
    
}
