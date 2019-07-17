/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Billing.model;

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
@Table(name = "EMPLOYEE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e")
    , @NamedQuery(name = "Employee.findById", query = "SELECT e FROM Employee e WHERE e.id = :id")
    , @NamedQuery(name = "Employee.findByFname", query = "SELECT e FROM Employee e WHERE e.fname = :fname")
    , @NamedQuery(name = "Employee.findByLname", query = "SELECT e FROM Employee e WHERE e.lname = :lname")
    , @NamedQuery(name = "Employee.findBySname", query = "SELECT e FROM Employee e WHERE e.sname = :sname")
    , @NamedQuery(name = "Employee.findByIdnumber", query = "SELECT e FROM Employee e WHERE e.idnumber = :idnumber")
    , @NamedQuery(name = "Employee.findByBday", query = "SELECT e FROM Employee e WHERE e.bday = :bday")
    , @NamedQuery(name = "Employee.findByGender", query = "SELECT e FROM Employee e WHERE e.gender = :gender")
    , @NamedQuery(name = "Employee.findByBlood", query = "SELECT e FROM Employee e WHERE e.blood = :blood")
    , @NamedQuery(name = "Employee.findByCategory", query = "SELECT e FROM Employee e WHERE e.category = :category")
    , @NamedQuery(name = "Employee.findByPnum", query = "SELECT e FROM Employee e WHERE e.pnum = :pnum")
    , @NamedQuery(name = "Employee.findByHnum", query = "SELECT e FROM Employee e WHERE e.hnum = :hnum")
    , @NamedQuery(name = "Employee.findByPic", query = "SELECT e FROM Employee e WHERE e.pic = :pic")
    , @NamedQuery(name = "Employee.findByLine1", query = "SELECT e FROM Employee e WHERE e.line1 = :line1")
    , @NamedQuery(name = "Employee.findByLine2", query = "SELECT e FROM Employee e WHERE e.line2 = :line2")
    , @NamedQuery(name = "Employee.findByLine3", query = "SELECT e FROM Employee e WHERE e.line3 = :line3")
    , @NamedQuery(name = "Employee.findByCity", query = "SELECT e FROM Employee e WHERE e.city = :city")
    , @NamedQuery(name = "Employee.findByProvince", query = "SELECT e FROM Employee e WHERE e.province = :province")
    , @NamedQuery(name = "Employee.findByCreator", query = "SELECT e FROM Employee e WHERE e.creator = :creator")
    , @NamedQuery(name = "Employee.findByEditor", query = "SELECT e FROM Employee e WHERE e.editor = :editor")})
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "ID")
    private int id;
    @Basic(optional = false)
    @Column(name = "FNAME")
    private String fname;
    @Column(name = "LNAME")
    private String lname;
    @Basic(optional = false)
    @Column(name = "SNAME")
    private String sname;
    @Id
    @Basic(optional = false)
    @Column(name = "IDNUMBER")
    private String idnumber;
    @Basic(optional = false)
    @Column(name = "BDAY")
    @Temporal(TemporalType.DATE)
    private Date bday;
    @Column(name = "GENDER")
    private String gender;
    @Column(name = "BLOOD")
    private String blood;
    @Column(name = "CATEGORY")
    private String category;
    @Basic(optional = false)
    @Column(name = "PNUM")
    private int pnum;
    @Column(name = "HNUM")
    private Integer hnum;
    @Column(name = "PIC")
    private String pic;
    @Basic(optional = false)
    @Column(name = "LINE1")
    private String line1;
    @Column(name = "LINE2")
    private String line2;
    @Column(name = "LINE3")
    private String line3;
    @Column(name = "CITY")
    private String city;
    @Column(name = "PROVINCE")
    private String province;
    @Column(name = "CREATOR")
    private String creator;
    @Column(name = "EDITOR")
    private String editor;

    public Employee() {
    }

    public Employee(String idnumber) {
        this.idnumber = idnumber;
    }

    public Employee(String idnumber, int id, String fname, String sname, Date bday, int pnum, String line1) {
        this.idnumber = idnumber;
        this.id = id;
        this.fname = fname;
        this.sname = sname;
        this.bday = bday;
        this.pnum = pnum;
        this.line1 = line1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public Date getBday() {
        return bday;
    }

    public void setBday(Date bday) {
        this.bday = bday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPnum() {
        return pnum;
    }

    public void setPnum(int pnum) {
        this.pnum = pnum;
    }

    public Integer getHnum() {
        return hnum;
    }

    public void setHnum(Integer hnum) {
        this.hnum = hnum;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getLine3() {
        return line3;
    }

    public void setLine3(String line3) {
        this.line3 = line3;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnumber != null ? idnumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.idnumber == null && other.idnumber != null) || (this.idnumber != null && !this.idnumber.equals(other.idnumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Billing.model.Employee[ idnumber=" + idnumber + " ]";
    }
    
}
