package br.project.account.generic;

import java.util.List;

/**
 * Project Account
 * @author Ronaldo Torre
 */
public interface InterfaceGeneric<E> {

    public boolean insert(E entity);
    public boolean update(E entity);
    public boolean delete(E entity);
    public E getById(Integer id);
    public E getByName(String name);
    public E getByParamter(String[] NameParam, String[] ValueParam);
    public List<E> getAll();
    public List<E> getAllByParamters(String[] NameParam, String[] ValueParam);

}
