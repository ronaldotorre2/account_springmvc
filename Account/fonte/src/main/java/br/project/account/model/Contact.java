package br.project.account.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


/**
 * Project Account
 * @author Ronaldo
 */
public class Contact implements Serializable {

    private static final long serialVersionUID = 6619594249919287973L;

    private Integer Id;
    private ContactType.Type Type;
    private String Description;
    private Date AddDate;
    private String AddUser;
    private Date UpdateDate;
    private String UpdateUser;

    public Contact() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public ContactType.Type getType() {
        return Type;
    }

    public void setType(ContactType.Type Type) {
        this.Type = Type;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
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
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.Id);
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
        final Contact other = (Contact) obj;
        if (!Objects.equals(this.Description, other.Description)) {
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
        if (!Objects.equals(this.Type, other.Type)) {
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
        return "Contact{" + "Id=" + Id + ", Type=" + Type + ", Description=" + Description + ", AddDate=" + AddDate + ", AddUser=" + AddUser + ", UpdateDate=" + UpdateDate + ", UpdateUser=" + UpdateUser + '}';
    }
    
}