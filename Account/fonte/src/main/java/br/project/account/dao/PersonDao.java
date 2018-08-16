package br.project.account.dao;

import br.project.account.generic.DaoGeneric;
import br.project.account.generic.InterfaceGeneric;
import br.project.account.model.Address;
import br.project.account.model.AddressType;
import br.project.account.model.Person;
import br.project.account.model.PersonType;
import br.project.account.util.NewDate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Project Account
 * @author Ronaldo Torre
 */
public class PersonDao extends DaoGeneric<Person> implements InterfaceGeneric<Person> {

    private String sql;
    private Statement stm;
    private ResultSet rs;

    private Person person;
    private Address address;

    private AddressDao addressDao;
    
    protected NewDate udate; 

    public PersonDao() {
        address = new Address();
        person = new Person();
        addressDao = new AddressDao();
        udate = new NewDate();
    }

    @Override
    public boolean insert(Person person) {
        try {
            if((!person.getName().isEmpty() && person.getName() != null)
                && (!person.getDocument1().isEmpty() && person.getDocument1() != null)
                && (person.getAddDate() != null) && (!person.getAddUser().isEmpty() && person.getAddUser() != null)
               ){
                    if((!person.getAddress().getName().isEmpty() && person.getAddress().getName() != null)
                        && (person.getAddress().getTypeId() != null) && (!person.getAddress().getNumber().isEmpty()
                        && person.getAddress().getNumber() != null) && (!person.getAddress().getZipCode().isEmpty()
                        && person.getAddress().getZipCode() != null) && (!person.getAddress().getDistrict().isEmpty()
                        && person.getAddress().getDistrict() != null) && (!person.getAddress().getCity().isEmpty()
                        && person.getAddress().getCity() != null) && (!person.getAddress().getState().isEmpty()
                        && person.getAddress().getState() != null)
                    ) {
                   
                    address = person.getAddress();
                    addressDao.insert(address);
                    
                    String[] param = new String[3];
                    String[] value = new String[3];
                    
                    param[0] = "Name";
                    param[1] = "Number";
                    param[2] = "Complement";
                    value[0] =  person.getAddress().getName();
                    value[1] =  person.getAddress().getNumber();
                    value[2] =  person.getAddress().getComplement();
                    
                    address = addressDao.getByParamter(param, value);
                    person.setAddress(address);

                    if (person.getTypeId() == 1) {
                        if((person.getGender() != null) && (person.getBirthDate() != null)
                            && (!person.getDocument2().isEmpty() && person.getDocument2() != null)
                          ) {
                            
                        	String MotherName = null;
                            String FatherName = null;
                            String BirthDate  = null;
                            
                            if(person.getBirthDate()!= null){
                                System.out.println(person.getBirthDate().toString());
                                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                                BirthDate = df.format(person.getBirthDate());
                                //System.out.println(BirthDate);
                            }

                            if (!person.getMotherName().isEmpty() && person.getMotherName() != null) {
                                MotherName = "'" + person.getMotherName() + "'";
                            }

                            if (!person.getFatherName().isEmpty() && person.getFatherName() != null) {
                                FatherName = "'" + person.getFatherName() + "'";
                            }
                            
                            sql = "Insert into Person(TypePerson,Name,Gender,BirthDate,Document1,Document2,AddressId,"
                                   + "MotherName,FatherName,AddDate,AddUser)values(" + person.getTypeId() + ",'" + person.getName() 
                                   + "'," + person.getGender().getCode()+"," + "STR_TO_DATE('"+BirthDate+"','%d/%m/%Y')"+",'"
                                   + person.getDocument1() + "','" + person.getDocument2() + "'," + person.getAddress().getId() + "," 
                                   + MotherName + "," + FatherName + "," + "current_timestamp,'" + address.getAddUser() + "');";

                        } 
                        else {
                            return false;
                        }
                    } 
                    else if (person.getTypeId() == 2) {
                        if (!person.getSocialName().isEmpty() && person.getSocialName() != null) {
                            String document2 = null;
                            String document3 = null;
                            
                            if (!person.getDocument2().isEmpty() && person.getDocument2() != null) {
                                document2 = "'" + person.getDocument2() + "'";
                            }

                            if (!person.getDocument3().isEmpty() && person.getDocument3() != null) {
                                document3 = "'" + person.getDocument3() + "'";
                            }

                            sql = "Insert into Person(TypePerson,Name,SocialName,Document1,Document2,Document3,AddressId,"
                                    + "AddDate,AddUser)values(" + person.getTypeId() + ",'" + person.getName() + "','" + person.getSocialName() + "','"
                                    + person.getDocument1() + "'," + document2 + "," + document3 + "," + person.getAddress().getId()
                                    + ", current_timestamp ,'" + address.getAddUser() + "');";
                        } 
                        else {
                            return false;
                        }
                    }

                    stm = getConnection().createStatement();
                    stm.execute(sql);

                    return true;

                } 
                else {
                    return false;
                }
            } 
            else {
                return false;
            }
        } 
        catch (SQLException e) {
            throw new IllegalArgumentException("Error while persisting person! " + e.getMessage());
        }
    }

    @Override
    public boolean update(Person person) {
        try{
            if((person.getId() > 0)
                &&(!person.getName().isEmpty() && person.getName() != null)
                && (!person.getDocument1().isEmpty() && person.getDocument1() != null)
                && (person.getUpdateDate() != null) && (!person.getUpdateUser().isEmpty() && person.getUpdateUser() != null)     
               ){
                  addressDao.update(person.getAddress());
                  
                  if(person.getType().getCode() == 1) {
                        if((person.getGender() != null) && (person.getBirthDate() != null)
                            && (!person.getDocument2().isEmpty() && person.getDocument2() != null)
                        ) {
                                String MotherName = null;
                                String FatherName = null;
                                String BirthDate  = null;
                                
                                if(person.getBirthDate()!= null){
                                    System.out.println(person.getBirthDate().toString());
                                    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                                    BirthDate = df.format(person.getBirthDate());
                                    //System.out.println(BirthDate);
                                }
                                
                                if (!person.getMotherName().isEmpty() && person.getMotherName() != null) {
                                    MotherName = "'" + person.getMotherName() + "'";
                                }

                                if (!person.getFatherName().isEmpty() && person.getFatherName() != null) {
                                    FatherName = "'" + person.getFatherName() + "'";
                                }
                                
                                sql = "Update Person set TypePerson= "+person.getType().getCode()+", Name= '"+person.getName()
                                	  +"', Gender= "+person.getGender().getCode()+"," 
                                	  +"BirthDate= STR_TO_DATE('" + BirthDate + "','%d/%m/%Y'), Document1= '"
                                      +person.getDocument1()+"', Document2= '"+person.getDocument2()+"', AddressId= "
                                      +person.getAddress().getId()+", MotherName= "+MotherName+", FatherName= "+FatherName+","
                                      +"UpdateDate= current_timestamp, UpdateUser= '" +person.getUpdateUser()
                                      +"' where IdPerson= "+person.getId()+";";
                                      
                      }
                      else {
                          return false;
                      }
                  }
                  else if (person.getType().getCode() == 2) {
                      if (!person.getSocialName().isEmpty() && person.getSocialName() != null) {
                            String document2 = null;
                            String document3 = null;

                            if (!person.getDocument2().isEmpty() && person.getDocument2() != null) {
                                document2 = "'" + person.getDocument2() + "'";
                            }

                            if (!person.getDocument3().isEmpty() && person.getDocument3() != null) {
                                document3 = "'" + person.getDocument3() + "'";
                            }
                            
                            sql = "Update Person set TypePerson= "+person.getTypeId()+", Name= '"+person.getName()
                            	  +"', SocialName= '"+person.getSocialName()+"'," +"Document1= '"+person.getDocument1()
                            	  +"', Document2= "+document2+", Document3= "+document3+","+"AddressId= " +person.getAddress().getId()+", " 
                            	  +"UpdateDate= current_timestamp, UpdateUser= '"+person.getUpdateUser()
                                  +"' where IdPerson= "+person.getId()+";";
                                    
                      }
                      else {
                          return false;
                      }
                  }
                  else {
                    return false;
                  }
                  
                  stm = getConnection().createStatement();
                  stm.execute(sql);

                  return true;
            } 
            else {
                return false;
            }
        }
        catch(SQLException e){
            throw new IllegalArgumentException("Error while update person! " + e.getMessage());
        }
    }

    @Override
    public boolean delete(Person person) {
        try{
            if(person.getId() > 0){
                sql = "delete from Person where IdPerson= "+person.getId()+";";

                stm = getConnection().createStatement();
                stm.execute(sql);

                return true;
            }
            else{
                return false;
            }
        }
        catch(SQLException e){
            throw new IllegalArgumentException("Error while delete person! "+e.getMessage());
        }
    }

    @Override
    public Person getById(Integer id) {
        try {
            sql = "select p.IdPerson, p.TypePerson, p.Name, p.SocialName, p.Gender, p.BirthDate,"
                   + "p.Document1, p.Document2, p.Document3, p.MotherName, p.FatherName, p.AddDate,"
                   + "p.AddUser, p.UpdateDate, p.UpdateUser, a.IdAddress, a.TypeId, a.Name as AddressName, "
                   + "a.Number, a.Complement, a.District, a.City, a.State, a.ZipCode, a.AddDate, "
                   + "a.AddUser, a.UpdateDate, a.UpdateUser from person p "
                   + "inner join address a on a.IdAddress = p.AddressId "
                   + "where p.IdPerson= "+id;
           
            stm = getConnection().createStatement();
            rs = stm.executeQuery(sql);

            while (rs.next()) {
                person = new Person();
                address = new Address();

                person.setId(rs.getInt("IdPerson"));

                if (rs.getInt("TypePerson") == 1) {
                	person.setTypeId(1);
                    person.setType(PersonType.Type.physical);

                    if (rs.getInt("Gender") == 1) {
                    	person.setGenderId(1);
                        person.setGender(PersonType.Gender.female);
                    } 
                    else if (rs.getInt("Gender") == 2) {
                    	person.setGenderId(2);
                        person.setGender(PersonType.Gender.male);
                    }

                    person.setBirthDate(rs.getDate("BirthDate"));
                    person.setMotherName(rs.getString("MotherName"));
                    person.setFatherName(rs.getString("FatherName"));
                } 
                else if (rs.getInt("TypePerson") == 2) {
                	person.setTypeId(2);
                    person.setType(PersonType.Type.juridical);
                    person.setSocialName(rs.getString("SocialName"));
                    person.setDocument3(rs.getString("Document3"));
                }

                person.setName(rs.getString("Name"));
                person.setDocument1(rs.getString("Document1"));
                person.setDocument2(rs.getString("Document2"));
                person.setAddDate(rs.getDate("AddDate"));
                person.setAddUser(rs.getString("AddUser"));
                person.setUpdateDate(rs.getDate("UpdateDate"));
                person.setUpdateUser(rs.getString("UpdateUser"));

                address.setId(rs.getInt("IdAddress"));
                address.setTypeId(rs.getInt("TypeId"));
                
                if(address.getTypeId() == 0){
                	address.setType(AddressType.Type.comercial);
                }
                else if(address.getTypeId() == 1){
                	address.setType(AddressType.Type.delivery);
                }
                else if(address.getTypeId() == 2){
                	address.setType(AddressType.Type.residential);
                }
                else if(address.getTypeId() == 3){
                	address.setType(AddressType.Type.others);
                }
                
                address.setName(rs.getString("AddressName"));
                address.setNumber(rs.getString("Number"));
                address.setComplement(rs.getString("Complement"));
                address.setDistrict(rs.getString("District"));
                address.setCity(rs.getString("City"));
                address.setState(rs.getString("State"));
                address.setZipCode(rs.getString("ZipCode"));
                address.setAddDate(rs.getTimestamp("AddDate"));
                address.setAddUser(rs.getString("AddUser"));
                address.setUpdateDate(rs.getTimestamp("UpdateDate"));
                address.setUpdateUser(rs.getString("UpdateUser"));
                person.setAddress(address);
            }

            return person;
        } 
        catch (SQLException ex) {
            Logger.getLogger(PersonDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Person getByName(String name) {
        try {
            sql = "select p.IdPerson, p.TypePerson, p.Name, p.SocialName, p.Gender, p.BirthDate,"
                  + "p.Document1, p.Document2, p.Document3, p.MotherName, p.FatherName, p.AddDate,"
                  + "p.AddUser, p.UpdateDate, p.UpdateUser, a.IdAddress, a.TypeId, a.Name as AddressName, "
                  + "a.Number, a.Complement, a.District, a.City, a.State, a.ZipCode, a.AddDate, "
                  + "a.AddUser, a.UpdateDate, a.UpdateUser from person p "
                  + "inner join address a on a.IdAddress = p.AddressId "
                  + "where p.Name= "+name;
            
            stm = getConnection().createStatement();
            rs = stm.executeQuery(sql);

            while (rs.next()) {
                person = new Person();
                address = new Address();

                person.setId(rs.getInt("IdPerson"));

                if (rs.getInt("TypePerson") == 1) {
                	person.setTypeId(1);
                    person.setType(PersonType.Type.physical);

                    if (rs.getInt("Gender") == 1) {
                    	person.setGenderId(1);
                        person.setGender(PersonType.Gender.female);
                    } 
                    else if (rs.getInt("Gender") == 2) {
                    	person.setGenderId(2);
                        person.setGender(PersonType.Gender.male);
                    }

                    person.setBirthDate(rs.getDate("BirthDate"));
                    person.setMotherName(rs.getString("MotherName"));
                    person.setFatherName(rs.getString("FatherName"));
                } 
                else if (rs.getInt("TypePerson") == 2) {
                	person.setTypeId(2);
                    person.setType(PersonType.Type.juridical);
                    person.setSocialName(rs.getString("SocialName"));
                    person.setDocument3(rs.getString("Document3"));
                }

                person.setName(rs.getString("Name"));
                person.setDocument1(rs.getString("Document1"));
                person.setDocument2(rs.getString("Document2"));
                person.setAddDate(rs.getDate("AddDate"));
                person.setAddUser(rs.getString("AddUser"));
                person.setUpdateDate(rs.getDate("UpdateDate"));
                person.setUpdateUser(rs.getString("UpdateUser"));

                address.setId(rs.getInt("IdAddress"));
                address.setTypeId(rs.getInt("TypeId"));
                
                if(address.getTypeId() == 0){
                	address.setType(AddressType.Type.comercial);
                }
                else if(address.getTypeId() == 1){
                	address.setType(AddressType.Type.delivery);
                }
                else if(address.getTypeId() == 2){
                	address.setType(AddressType.Type.residential);
                }
                else if(address.getTypeId() == 3){
                	address.setType(AddressType.Type.others);
                }
                
                address.setName(rs.getString("AddressName"));
                address.setNumber(rs.getString("Number"));
                address.setComplement(rs.getString("Complement"));
                address.setDistrict(rs.getString("District"));
                address.setCity(rs.getString("City"));
                address.setState(rs.getString("State"));
                address.setZipCode(rs.getString("ZipCode"));
                address.setAddDate(rs.getTimestamp("AddDate"));
                address.setAddUser(rs.getString("AddUser"));
                address.setUpdateDate(rs.getTimestamp("UpdateDate"));
                address.setUpdateUser(rs.getString("UpdateUser"));
                person.setAddress(address);
            }

            return person;
        } 
        catch (SQLException ex) {
            Logger.getLogger(PersonDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Person getByParamter(String[] NameParam, String[] ValueParam) {
        try {
            sql = "select p.IdPerson, p.TypePerson, p.Name, p.SocialName, p.Gender, p.BirthDate,"
                  + "p.Document1, p.Document2, p.Document3, p.MotherName, p.FatherName, p.AddDate,"
                  + "p.AddUser, p.UpdateDate, p.UpdateUser, a.IdAddress, a.TypeId, a.Name as AddressName, "
                  + "a.Number, a.Complement, a.District, a.City, a.State, a.ZipCode, a.AddDate, "
                  + "a.AddUser, a.UpdateDate, a.UpdateUser from person p "
                  + "inner join address a on a.IdAddress = p.AddressId ";
            
            if((NameParam.length == 1)&&(ValueParam.length == 1)){
                sql = sql+"where "+NameParam[0]+"= "+ValueParam[0]+";";
            }
            else{
                sql = sql+"where ";
                for(int i=0; i < NameParam.length-1; i++){
                    if(i < NameParam.length)
                        sql = sql+NameParam[i]+"= "+ValueParam[i]+" and ";
                    else if (i == NameParam.length)
                        sql = sql+NameParam[i]+"= "+ValueParam[i]+";";
                }
            }
            
            stm = getConnection().createStatement();
            rs = stm.executeQuery(sql);

            while (rs.next()) {
                person = new Person();
                address = new Address();

                person.setId(rs.getInt("IdPerson"));

                if (rs.getInt("TypePerson") == 1) {
                	person.setTypeId(1);
                    person.setType(PersonType.Type.physical);

                    if (rs.getInt("Gender") == 1) {
                    	person.setGenderId(1);
                        person.setGender(PersonType.Gender.female);
                    } 
                    else if (rs.getInt("Gender") == 2) {
                    	person.setGenderId(2);
                        person.setGender(PersonType.Gender.male);
                    }

                    person.setBirthDate(rs.getDate("BirthDate"));
                    person.setMotherName(rs.getString("MotherName"));
                    person.setFatherName(rs.getString("FatherName"));
                } 
                else if (rs.getInt("TypePerson") == 2) {
                	person.setTypeId(2);
                    person.setType(PersonType.Type.juridical);
                    person.setSocialName(rs.getString("SocialName"));
                    person.setDocument3(rs.getString("Document3"));
                }

                person.setName(rs.getString("Name"));
                person.setDocument1(rs.getString("Document1"));
                person.setDocument2(rs.getString("Document2"));
                person.setAddDate(rs.getDate("AddDate"));
                person.setAddUser(rs.getString("AddUser"));
                person.setUpdateDate(rs.getDate("UpdateDate"));
                person.setUpdateUser(rs.getString("UpdateUser"));

                address.setId(rs.getInt("IdAddress"));
                address.setTypeId(rs.getInt("TypeId"));
                
                if(address.getTypeId() == 0){
                	address.setType(AddressType.Type.comercial);
                }
                else if(address.getTypeId() == 1){
                	address.setType(AddressType.Type.delivery);
                }
                else if(address.getTypeId() == 2){
                	address.setType(AddressType.Type.residential);
                }
                else if(address.getTypeId() == 3){
                	address.setType(AddressType.Type.others);
                }
                
                address.setName(rs.getString("AddressName"));
                address.setNumber(rs.getString("Number"));
                address.setComplement(rs.getString("Complement"));
                address.setDistrict(rs.getString("District"));
                address.setCity(rs.getString("City"));
                address.setState(rs.getString("State"));
                address.setZipCode(rs.getString("ZipCode"));
                address.setAddDate(rs.getTimestamp("AddDate"));
                address.setAddUser(rs.getString("AddUser"));
                address.setUpdateDate(rs.getTimestamp("UpdateDate"));
                address.setUpdateUser(rs.getString("UpdateUser"));
                person.setAddress(address);
            }

            return person;
        } 
        catch (SQLException ex) {
            Logger.getLogger(PersonDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<Person> getAll() {
        try {
            sql = "select p.IdPerson, p.TypePerson, p.Name, p.SocialName, p.Gender, p.BirthDate,"
                    + "p.Document1, p.Document2, p.Document3, p.MotherName, p.FatherName, p.AddDate,"
                    + "p.AddUser, p.UpdateDate, p.UpdateUser, a.IdAddress, a.TypeId, a.Name as AddressName, "
                    + "a.Number, a.Complement, a.District, a.City, a.State, a.ZipCode, a.AddDate, "
                    + "a.AddUser, a.UpdateDate, a.UpdateUser from person p "
                    + "inner join address a on a.IdAddress = p.AddressId "
                    + "order by p.Name ";

            List<Person> list = new ArrayList<>();

            stm = getConnection().createStatement();
            rs = stm.executeQuery(sql);

            while (rs.next()) {
                person = new Person();
                address = new Address();

                person.setId(rs.getInt("IdPerson"));

                if (rs.getInt("TypePerson") == 1) {
                	person.setTypeId(1);
                    person.setType(PersonType.Type.physical);

                    if (rs.getInt("Gender") == 1) {
                    	person.setGenderId(1);
                        person.setGender(PersonType.Gender.female);
                    } 
                    else if (rs.getInt("Gender") == 2) {
                    	person.setGenderId(2);
                        person.setGender(PersonType.Gender.male);
                    }

                    person.setBirthDate(rs.getDate("BirthDate"));
                    person.setMotherName(rs.getString("MotherName"));
                    person.setFatherName(rs.getString("FatherName"));
                } 
                else if (rs.getInt("TypePerson") == 2) {
                	person.setTypeId(2);
                    person.setType(PersonType.Type.juridical);
                    person.setSocialName(rs.getString("SocialName"));
                    person.setDocument3(rs.getString("Document3"));
                }

                person.setName(rs.getString("Name"));
                person.setDocument1(rs.getString("Document1"));
                person.setDocument2(rs.getString("Document2"));
                person.setAddDate(rs.getDate("AddDate"));
                person.setAddUser(rs.getString("AddUser"));
                person.setUpdateDate(rs.getDate("UpdateDate"));
                person.setUpdateUser(rs.getString("UpdateUser"));

                address.setId(rs.getInt("IdAddress"));
                address.setTypeId(rs.getInt("TypeId"));
                
                if(address.getTypeId() == 0){
                	address.setType(AddressType.Type.comercial);
                }
                else if(address.getTypeId() == 1){
                	address.setType(AddressType.Type.delivery);
                }
                else if(address.getTypeId() == 2){
                	address.setType(AddressType.Type.residential);
                }
                else if(address.getTypeId() == 3){
                	address.setType(AddressType.Type.others);
                }
                
                address.setName(rs.getString("AddressName"));
                address.setNumber(rs.getString("Number"));
                address.setComplement(rs.getString("Complement"));
                address.setDistrict(rs.getString("District"));
                address.setCity(rs.getString("City"));
                address.setState(rs.getString("State"));
                address.setZipCode(rs.getString("ZipCode"));
                address.setAddDate(rs.getTimestamp("AddDate"));
                address.setAddUser(rs.getString("AddUser"));
                address.setUpdateDate(rs.getTimestamp("UpdateDate"));
                address.setUpdateUser(rs.getString("UpdateUser"));
                person.setAddress(address);

                list.add(person);
            }

            return list;
        } 
        catch (SQLException ex) {
            Logger.getLogger(PersonDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<Person> getAllByParamters(String[] NameParam, String[] ValueParam) {
        try {
            sql = "select p.IdPerson, p.TypePerson, p.Name, p.SocialName, p.Gender, p.BirthDate,"
                    + "p.Document1, p.Document2, p.Document3, p.MotherName, p.FatherName, p.AddDate,"
                    + "p.AddUser, p.UpdateDate, p.UpdateUser, a.IdAddress, a.TypeId, a.Name as AddressName, "
                    + "a.Number, a.Complement, a.District, a.City, a.State, a.ZipCode, a.AddDate, "
                    + "a.AddUser, a.UpdateDate, a.UpdateUser from person p "
                    + "inner join address a on a.IdAddress = p.AddressId ";
            
            if((NameParam.length == 1)&&(ValueParam.length == 1)){
                sql = sql+"where "+NameParam[0]+" like '"+ValueParam[0]+"%';";
            }
            else{
                sql = sql+"where ";
                for(int i=0; i < NameParam.length-1; i++){
                    if(i < NameParam.length)
                        sql = sql+NameParam[i]+" like '"+ValueParam[i]+"%' and ";
                    else if (i == NameParam.length)
                        sql = sql+NameParam[i]+" like '"+ValueParam[i]+"%';";
                }
            }

            List<Person> list = new ArrayList<>();

            stm = getConnection().createStatement();
            rs = stm.executeQuery(sql);

            while (rs.next()) {
                person = new Person();
                address = new Address();

                person.setId(rs.getInt("IdPerson"));

                if (rs.getInt("TypePerson") == 1) {
                	person.setTypeId(1);
                    person.setType(PersonType.Type.physical);

                    if (rs.getInt("Gender") == 1) {
                    	person.setGenderId(1);
                        person.setGender(PersonType.Gender.female);
                    } 
                    else if (rs.getInt("Gender") == 2) {
                    	person.setGenderId(2);
                        person.setGender(PersonType.Gender.male);
                    }

                    person.setBirthDate(rs.getDate("BirthDate"));
                    person.setMotherName(rs.getString("MotherName"));
                    person.setFatherName(rs.getString("FatherName"));
                } 
                else if (rs.getInt("TypePerson") == 2) {
                	person.setTypeId(2);
                    person.setType(PersonType.Type.juridical);
                    person.setSocialName(rs.getString("SocialName"));
                    person.setDocument3(rs.getString("Document3"));
                }

                person.setName(rs.getString("Name"));
                person.setDocument1(rs.getString("Document1"));
                person.setDocument2(rs.getString("Document2"));
                person.setAddDate(rs.getDate("AddDate"));
                person.setAddUser(rs.getString("AddUser"));
                person.setUpdateDate(rs.getDate("UpdateDate"));
                person.setUpdateUser(rs.getString("UpdateUser"));

                address.setId(rs.getInt("IdAddress"));
                address.setTypeId(rs.getInt("TypeId"));
                
                if(address.getTypeId() == 0){
                	address.setType(AddressType.Type.comercial);
                }
                else if(address.getTypeId() == 1){
                	address.setType(AddressType.Type.delivery);
                }
                else if(address.getTypeId() == 2){
                	address.setType(AddressType.Type.residential);
                }
                else if(address.getTypeId() == 3){
                	address.setType(AddressType.Type.others);
                }
                
                address.setName(rs.getString("AddressName"));
                address.setNumber(rs.getString("Number"));
                address.setComplement(rs.getString("Complement"));
                address.setDistrict(rs.getString("District"));
                address.setCity(rs.getString("City"));
                address.setState(rs.getString("State"));
                address.setZipCode(rs.getString("ZipCode"));
                address.setAddDate(rs.getTimestamp("AddDate"));
                address.setAddUser(rs.getString("AddUser"));
                address.setUpdateDate(rs.getTimestamp("UpdateDate"));
                address.setUpdateUser(rs.getString("UpdateUser"));
                person.setAddress(address);

                list.add(person);
            }

            return list;
        } 
        catch (SQLException ex) {
            Logger.getLogger(PersonDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}