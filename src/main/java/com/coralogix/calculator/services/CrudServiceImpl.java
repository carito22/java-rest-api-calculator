package com.coralogix.calculator.services;

import java.util.List;

import com.coralogix.calculator.repository.GenericRepository;

public abstract class CrudServiceImpl<T,ID> implements CrudService<T,ID>{

    protected abstract GenericRepository<T,ID> getRepository();

    @Override
    public List<T> getAll() throws Exception {
        return getRepository().findAll();
    }
    
}
