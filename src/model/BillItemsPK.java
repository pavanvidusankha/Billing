/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Pavan
 */
@Embeddable
public class BillItemsPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "BILL_ID")
    private int billId;
    @Basic(optional = false)
    @Column(name = "ITEM_ID")
    private int itemId;
    private int quantity;

    public BillItemsPK() {
    }

    public BillItemsPK(int billId, int itemId) {
        this.billId = billId;
        this.itemId = itemId;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getItemId() {
        return itemId;
    }
    
    

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
    public void setquantity(int quantity){
                this.quantity=quantity;
}
     public int  getquantity() {
        return quantity;
    }
       
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) billId;
        hash += (int) itemId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BillItemsPK)) {
            return false;
        }
        BillItemsPK other = (BillItemsPK) object;
        if (this.billId != other.billId) {
            return false;
        }
        if (this.itemId != other.itemId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Billing.model.BillItemsPK[ billId=" + billId + ", itemId=" + itemId + " ]";
    }
    
}
