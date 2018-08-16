package br.project.account.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Project Account
 * @author Ronaldo Torre 
 */
public class Person implements Serializable{
    
    private static final long serialVersionUID = 4656440217310053483L;
    
    private Integer Id;
    private Integer TypeId;
    private PersonType.Type Type;
    private String Name;
    private String SocialName;
    private Integer GenderId;
    private PersonType.Gender Gender;
    
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date BirthDate;
    private String Document1;
    private String Document2;
    private String Document3;
    private Address Address;
    private String MotherName;
    private String FatherName;
    
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date AddDate;
    private String AddUser;
    
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date UpdateDate;
    private String UpdateUser;
    
    private List<Contact> LstContact = new ArrayList<>();

    public Person() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public Integer getTypeId() {
        return TypeId;
    }

    public void setTypeId(Integer TypeId) {
        this.TypeId = TypeId;
    }
    
    public PersonType.Type getType() {
        return Type;
    }

    public void setType(PersonType.Type Type) {
        this.Type = Type;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getSocialName() {
        return SocialName;
    }

    public void setSocialName(String SocialName) {
        this.SocialName = SocialName;
    }

    public Integer getGenderId() {
        return GenderId;
    }

    public void setGenderId(Integer GenderId) {
        this.GenderId = GenderId;
    }
    
    public PersonType.Gender getGender() {
        return Gender;
    }

    public void setGender(PersonType.Gender Gender) {
        this.Gender = Gender;
    }

    public Date getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(Date BirthDate) {
        this.BirthDate = BirthDate;
    }

    public String getDocument1() {
        return Document1;
    }

    public void setDocument1(String Document1) {
        this.Document1 = Document1;
    }

    public String getDocument2() {
        return Document2;
    }

    public void setDocument2(String Document2) {
        this.Document2 = Document2;
    }
    
    public String getDocument3() {
        return Document3;
    }

    public void setDocument3(String Document3) {
        this.Document3 = Document3;
    }

    public Address getAddress() {
        return Address;
    }

    public void setAddress(Address Address) {
        this.Address = Address;
    }

    public String getMotherName() {
        return MotherName;
    }

    public void setMotherName(String MotherName) {
        this.MotherName = MotherName;
    }

    public String getFatherName() {
        return FatherName;
    }

    public void setFatherName(String FatherName) {
        this.FatherName = FatherName;
    }

    public Date getAddDate() {
        return AddDate;
    }

    public void setAddDate(Date AddDate) {
        this.AddDate = AddDate;
    }

    public String getAddUser() {
        return AddUser;
    }

    public void setAddUser(String AddUser) {
        this.AddUser = AddUser;
    }

    public Date getUpdateDate() {
        return UpdateDate;
    }

    public void setUpdateDate(Date UpdateDate) {
        this.UpdateDate = UpdateDate;
    }

    public String getUpdateUser() {
        return UpdateUser;
    }

    public void setUpdateUser(String UpdateUser) {
        this.UpdateUser = UpdateUser;
    }

    public List<Contact> getLstContact() {
        return LstContact;
    }

    public void setLstContact(List<Contact> LstContact) {
        this.LstContact = LstContact;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.Id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (!Objects.equals(this.Name, other.Name)) {
            return false;
        }
        if (!Objects.equals(this.SocialName, other.SocialName)) {
            return false;
        }
        if (!Objects.equals(this.Document1, other.Document1)) {
            return false;
        }
        if (!Objects.equals(this.Document2, other.Document2)) {
            return false;
        }
        if (!Objects.equals(this.Document3, other.Document3)) {
            return false;
        }
        if (!Objects.equals(this.MotherName, other.MotherName)) {
            return false;
        }
        if (!Objects.equals(this.FatherName, other.FatherName)) {
            return false;
        }
        if (!Objects.equals(this.AddUser, other.AddUser)) {
            return false;
        }
        if (!Objects.equals(this.UpdateUser, other.UpdateUser)) {
            return false;
        }
        if (!Objects.equals(this.Id, other.Id)) {
            return false;
        }
        if (this.Type != other.Type) {
            return false;
        }
        if (this.Gender != other.Gender) {
            return false;
        }
        if (!Objects.equals(this.BirthDate, other.BirthDate)) {
            return false;
        }
        if (!Objects.equals(this.Address, other.Address)) {
            return false;
        }
        if (!Objects.equals(this.AddDate, other.AddDate)) {
            return false;
        }
        if (!Objects.equals(this.UpdateDate, other.UpdateDate)) {
            return false;
        }
        if (!Objects.equals(this.LstContact, other.LstContact)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Person{" + "Id=" + Id + ", Type=" + Type + ", Name=" + Name + ", SocialName=" + SocialName + ", Gender=" + Gender + ", BirthDate=" + BirthDate + ", Document1=" + Document1 + ", Document2=" + Document2 + ", Document3=" + Document3 + ", Address=" + Address + ", MotherName=" + MotherName + ", FatherName=" + FatherName + ", AddDate=" + AddDate + ", AddUser=" + AddUser + ", UpdateDate=" + UpdateDate + ", UpdateUser=" + UpdateUser + ", LstContact=" + LstContact + '}';
    }
    
}