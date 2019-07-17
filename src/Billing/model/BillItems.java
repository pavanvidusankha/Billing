/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Billing.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pavan
 */
@Entity
@Table(name = "BILL_ITEMS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BillItems.findAll", query = "SELECT b FROM BillItems b")
    , @NamedQuery(name = "BillItems.findByBillId", query = "SELECT b FROM BillItems b WHERE b.billItemsPK.billId = :billId")
    , @NamedQuery(name = "BillItems.findByItemId", query = "SELECT b FROM BillItems b WHERE b.billItemsPK.itemId = :itemId")
    , @NamedQuery(name = "BillItems.findByQuantity", query = "SELECT b FROM BillItems b WHERE b.quantity = :quantity")})
public class BillItems implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected BillItemsPK billItemsPK;
    @Column(name = "QUANTITY")
    private Integer quantity;

    public BillItems() {
    }

    public BillItems(BillItemsPK billItemsPK) {
        this.billItemsPK = billItemsPK;
    }

    public BillItems(int billId, int itemId) {
        this.billItemsPK = new BillItemsPK(billId, itemId);
    }

    public BillItemsPK getBillItemsPK() {
        return billItemsPK;
    }

    public void setBillItemsPK(BillItemsPK billItemsPK) {
        this.billItemsPK = billItemsPK;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (billItemsPK != null ? billItemsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BillItems)) {
            return false;
        }
        BillItems other = (BillItems) object;
        if ((this.billItemsPK == null && other.billItemsPK != null) || (this.billItemsPK != null && !this.billItemsPK.equals(other.billItemsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Billing.model.BillItems[ billItemsPK=" + billItemsPK + " ]";
    }
    
}
