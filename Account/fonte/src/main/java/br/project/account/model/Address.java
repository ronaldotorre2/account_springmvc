package br.project.account.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Project Account
 * @author Ronaldo Torre
 */
public class Address implements Serializable {

    private static final long serialVersionUID = 2757813264295815530L;

    private Integer Id;
    private Integer TypeId;
    private AddressType.Type Type;
    private String Name;
    private String Number;
    private String Complement;
    private String District;
    private String ZipCode;
    private String City;
    private String State;
    
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date AddDate;
    private String AddUser;
    
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date UpdateDate;
    private String UpdateUser;

    public Address() {
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

    public AddressType.Type getType() {
        return Type;
    }

    public void setType(AddressType.Type Type) {
        this.Type = Type;
    }
    
    public void setTypeById(int typeid){
        if(typeid > 0){
            switch (typeid) {
                case 0:
                    this.Type= br.project.account.model.AddressType.Type.comercial;
                    break;
                case 1:
                    this.Type= br.project.account.model.AddressType.Type.delivery;
                    break;
                case 2:
                    this.Type= br.project.account.model.AddressType.Type.residential;
                    break;
                case 3:
                    this.Type= br.project.account.model.AddressType.Type.others;
                    break;
                default:
                    break;
            }
        }
        
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String Number) {
        this.Number = Number;
    }

    public String getComplement() {
        return Complement;
    }

    public void setComplement(String Complement) {
        this.Complement = Complement;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String District) {
        this.District = District;
    }

    public String getZipCode() {
        return ZipCode;
    }

    public void setZipCode(String ZipCode) {
        this.ZipCode = ZipCode;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.Id;
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
        final Address other = (Address) obj;
        if (this.Id != other.Id) {
            return false;
        }
        if (!Objects.equals(this.Name, other.Name)) {
            return false;
        }
        if (!Objects.equals(this.Number, other.Number)) {
            return false;
        }
        if (!Objects.equals(this.Complement, other.Complement)) {
            return false;
        }
        if (!Objects.equals(this.District, other.District)) {
            return false;
        }
        if (!Objects.equals(this.ZipCode, other.ZipCode)) {
            return false;
        }
        if (!Objects.equals(this.City, other.City)) {
            return false;
        }
        if (!Objects.equals(this.State, other.State)) {
            return false;
        }
        if (!Objects.equals(this.AddUser, other.AddUser)) {
            return false;
        }
        if (!Objects.equals(this.UpdateUser, other.UpdateUser)) {
            return false;
        }
        if (this.Type != other.Type) {
            return false;
        }
        if (!Objects.equals(this.AddDate, other.AddDate)) {
            return false;
        }
        if (!Objects.equals(this.UpdateDate, other.UpdateDate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Address{" + "Id=" + Id + ", Type=" + Type + ", Name=" + Name + ", Number=" + Number + ", Complement=" + Complement + ", District=" + District + ", ZipCode=" + ZipCode + ", City=" + City + ", State=" + State + ", AddDate=" + AddDate + ", AddUser=" + AddUser + ", UpdateDate=" + UpdateDate + ", UpdateUser=" + UpdateUser + '}';
    }
    
}