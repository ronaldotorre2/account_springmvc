package br.project.account.dao;

import br.project.account.generic.DaoGeneric;
import br.project.account.generic.InterfaceGeneric;
import br.project.account.model.Address;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Project Account
 * @author Ronaldo Torre
 * @Description Inclusão de metodos de presistencia
 */
public class AddressDao extends DaoGeneric<Address> implements InterfaceGeneric<Address> {

    private String sql;
    private Statement stm;
    private ResultSet rs;

    @Override
    public boolean insert(Address address) {
        try {
            String[] param = new String[3];
            String[] value = new String[3];
                                
            param[0] = "Name";
            param[1] = "Number";
            param[2] = "Complement";
            value[0] = address.getName();
            value[1] = address.getNumber();
            value[2] = address.getComplement();
                                
            if(this.getByParamter(param, value)== null){
                if ((!address.getName().isEmpty() && address.getName() != null)
                    && (address.getTypeId()!= null && !address.getNumber().isEmpty())
                    && (address.getNumber() != null) && (!address.getZipCode().isEmpty()
                    && address.getZipCode() != null) && (!address.getDistrict().isEmpty()
                    && address.getDistrict() != null) && (!address.getCity().isEmpty()
                    && address.getCity() != null) && (!address.getState().isEmpty()
                    && address.getState() != null) && (address.getAddDate()!= null)
                    && (!address.getAddUser().isEmpty() && address.getAddUser() != null)
                   ) {
                    
                    String compl = null;                  

                    if (address.getComplement() != null) {
                        compl = "'" + address.getComplement() + "'";
                    }

                    sql = "Insert into Address(TypeId,Name,Number,Complement,ZipCode,District,City,State,AddDate,AddUser)values("
                           + address.getType().getCode() + ",'" + address.getName() + "','" + address.getNumber() + "'," + compl + ",'"
                           + address.getZipCode() + "','" + address.getDistrict() + "','" + address.getCity() + "','"
                           + address.getState() + "',current_timestamp, '"
                           + address.getAddUser()+"');";
                    
                    stm = getConnection().createStatement();
                    stm.execute(sql);

                    return true;
                } else {
                    return false;
                }
            }
            else{
                 return false;
            }
        } 
        catch(SQLException e){
            throw new IllegalArgumentException("Error while persisting address! "+e.getMessage());
        }
    }

    @Override
    public boolean update(Address address) {
        try{
            if((address.getId() > 0)
                &&(!address.getName().isEmpty() && address.getName() != null)
                &&(address.getType() != null) && (!address.getNumber().isEmpty()
                && address.getNumber() != null) && (!address.getZipCode().isEmpty()
                && address.getZipCode() != null) && (!address.getDistrict().isEmpty()
                && address.getDistrict() != null) && (!address.getCity().isEmpty()
                && address.getCity() != null) && (!address.getState().isEmpty()
                && address.getState() != null) && (address.getUpdateDate()!= null) 
                && (!address.getUpdateUser().isEmpty() && address.getUpdateUser() != null)
               ){
                
            	String complement = address.getComplement();
            	
            	if(address.getComplement() == null){
            		complement = null;
            	}
            	else{
            		complement = "'"+address.getComplement()+"'";
            	}
            	
                sql= "Update Address set TypeId= "+address.getTypeId()+", Name='"+address.getName()+"', "
                	 +"Number='"+address.getNumber()+"', Complement="+complement+", District='"+address.getDistrict()+"', "
                	 +"City= '"+address.getCity()+"', State= '"+address.getState()+"', ZipCode='"+address.getZipCode()+"',"
                	 +"UpdateDate= current_timestamp, UpdateUser= '"
                	 +address.getAddUser()+"' where IdAddress= "+address.getId()+";";
                
                stm = getConnection().createStatement();
                stm.execute(sql);

                return true;
            }
            else{
                return false;
            }
        }
        catch(SQLException e){
            throw new IllegalArgumentException("Error while update address! "+e.getMessage());
        }
    }

    @Override
    public boolean delete(Address address) {
        try{
            if(address.getId() > 0){
                sql = "delete from Address where IdAddress= "+address.getId()+";";

                stm = getConnection().createStatement();
                stm.execute(sql);

                return true;
            }
            else{
                return false;
            }
        }
        catch(SQLException e){
            throw new IllegalArgumentException("Error while delete address! "+e.getMessage());
        }
    }

    @Override
    public Address getById(Integer id) {
        try{
            Address address = null;
            if(id > 0){
                sql = "Select * from Address where IdAddress= "+id;
            }
            else {
                return null;
            }
            
            stm = getConnection().createStatement();
            rs = stm.executeQuery(sql);
            
            while(rs.next()){
                address = new Address();
                address.setId(rs.getInt("IdAddress"));
                address.setTypeId(rs.getInt("TypeId"));
                address.setName(rs.getString("Name"));
                address.setNumber(rs.getString("Number"));
                address.setComplement(rs.getString("Complement"));
                address.setZipCode(rs.getString("ZipCode"));
                address.setDistrict(rs.getString("District"));
                address.setCity(rs.getString("City"));
                address.setState(rs.getString("State"));
                address.setZipCode(rs.getString("ZipCode"));
                address.setAddDate(rs.getTimestamp("AddDate"));
                address.setAddUser(rs.getString("AddUser"));
                address.setUpdateDate(rs.getTimestamp("UpdateDate"));
                address.setUpdateUser(rs.getString("UpdateUser"));
            }
            
            return address;
        }
        catch(SQLException ex){
            Logger.getLogger(PersonDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Address getByName(String name) {
        try{
            Address address = null;
            if(!name.isEmpty() && !name.equals("")){
                sql = "Select * from Address where Name= "+name;
            }
            else {
                return null;
            }
            
            stm = getConnection().createStatement();
            rs = stm.executeQuery(sql);
            
            while(rs.next()){
                address = new Address();
                address.setId(rs.getInt("IdAddress"));
                address.setTypeId(rs.getInt("TypeId"));
                address.setName(rs.getString("Name"));
                address.setNumber(rs.getString("Number"));
                address.setComplement(rs.getString("Complement"));
                address.setZipCode(rs.getString("ZipCode"));
                address.setDistrict(rs.getString("District"));
                address.setCity(rs.getString("City"));
                address.setState(rs.getString("State"));
                address.setZipCode(rs.getString("ZipCode"));
                address.setAddDate(rs.getTimestamp("AddDate"));
                address.setAddUser(rs.getString("AddUser"));
                address.setUpdateDate(rs.getTimestamp("UpdateDate"));
                address.setUpdateUser(rs.getString("UpdateUser"));
            }
            
            return address;
        }
        catch(SQLException ex){
            Logger.getLogger(PersonDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Address getByParamter(String[] NameParam, String[] ValueParam) {
        try{
            Address address = null;
            
            sql = "Select * from Address ";
                    
            if((NameParam.length == 1)&&(ValueParam.length == 1)){
                sql = sql+"where "+NameParam[0]+"= "+ValueParam[0]+";";
            }
            else{
                sql = sql+"where ";
                int count = NameParam.length-1;
                for(int i=0; i <= count; i++){
                    if(i < count)
                        sql = sql+NameParam[i]+"= '"+ValueParam[i]+"' and ";
                    else if (i == count)
                        sql = sql+NameParam[i]+"= '"+ValueParam[i]+"';";
                }
            }
            
            stm = getConnection().createStatement();
            rs = stm.executeQuery(sql);
            
            while(rs.next()){
                address = new Address();
                address.setId(rs.getInt("IdAddress"));
                address.setTypeId(rs.getInt("TypeId"));
                address.setName(rs.getString("Name"));
                address.setNumber(rs.getString("Number"));
                address.setComplement(rs.getString("Complement"));
                address.setZipCode(rs.getString("ZipCode"));
                address.setDistrict(rs.getString("District"));
                address.setCity(rs.getString("City"));
                address.setState(rs.getString("State"));
                address.setZipCode(rs.getString("ZipCode"));
                address.setAddDate(rs.getTimestamp("AddDate"));
                address.setAddUser(rs.getString("AddUser"));
                address.setUpdateDate(rs.getTimestamp("UpdateDate"));
                address.setUpdateUser(rs.getString("UpdateUser"));
            }
            
            return address;
        }
        catch(SQLException ex){
            Logger.getLogger(PersonDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<Address> getAll() {
        try{
            Address address;
            List<Address> list = new ArrayList<>();
            
            sql = "Select * from Address;";
            
            stm = getConnection().createStatement();
            rs = stm.executeQuery(sql);
            
            while(rs.next()){
                address = new Address();
                address.setId(rs.getInt("IdAddress"));
                address.setTypeId(rs.getInt("TypeId"));
                address.setName(rs.getString("Name"));
                address.setNumber(rs.getString("Number"));
                address.setComplement(rs.getString("Complement"));
                address.setZipCode(rs.getString("ZipCode"));
                address.setDistrict(rs.getString("District"));
                address.setCity(rs.getString("City"));
                address.setState(rs.getString("State"));
                address.setZipCode(rs.getString("ZipCode"));
                address.setAddDate(rs.getTimestamp("AddDate"));
                address.setAddUser(rs.getString("AddUser"));
                address.setUpdateDate(rs.getTimestamp("UpdateDate"));
                address.setUpdateUser(rs.getString("UpdateUser"));
                list.add(address);
            }
            
            return list;
        }
        catch(SQLException ex){
            Logger.getLogger(PersonDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<Address> getAllByParamters(String[] NameParam, String[] ValueParam) {
        try{
            Address address;
            List<Address> list = new ArrayList<>();
            
            sql = "Select * from Address ";
                    
            if((NameParam.length == 1)&&(ValueParam.length == 1)){
                sql = sql+"where "+NameParam[0]+"= "+ValueParam[0]+";";
            }
            else{
                sql = sql+"where ";
                for(int i=0; i < NameParam.length; i++){
                    if(i < NameParam.length)
                        sql = sql+NameParam[i]+"= "+ValueParam[i]+" and ";
                    else if (i == NameParam.length)
                        sql = sql+NameParam[i]+"= "+ValueParam[i]+";";
                }
            }
            
            stm = getConnection().createStatement();
            rs = stm.executeQuery(sql);
            
            while(rs.next()){
                address = new Address();
                address.setId(rs.getInt("IdAddress"));
                address.setTypeId(rs.getInt("TypeId"));
                address.setName(rs.getString("Name"));
                address.setNumber(rs.getString("Number"));
                address.setComplement(rs.getString("Complement"));
                address.setZipCode(rs.getString("ZipCode"));
                address.setDistrict(rs.getString("District"));
                address.setCity(rs.getString("City"));
                address.setState(rs.getString("State"));
                address.setZipCode(rs.getString("ZipCode"));
                address.setAddDate(rs.getTimestamp("AddDate"));
                address.setAddUser(rs.getString("AddUser"));
                address.setUpdateDate(rs.getTimestamp("UpdateDate"));
                address.setUpdateUser(rs.getString("UpdateUser"));
                list.add(address);
            }
            
            return list;
        }
        catch(SQLException ex){
            Logger.getLogger(PersonDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}