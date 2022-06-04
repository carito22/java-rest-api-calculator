package com.coralogix.calculator.services;

import java.util.List;

public interface CrudService<T,ID>{
    
    List<T> getAll() throws Exception;
    
}
