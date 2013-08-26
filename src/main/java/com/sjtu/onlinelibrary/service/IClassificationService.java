package com.sjtu.onlinelibrary.service;

import java.util.List;

import com.sjtu.onlinelibrary.DataAccessException;
import com.sjtu.onlinelibrary.entity.Classification;
import com.sjtu.onlinelibrary.web.viewmodel.ClassificationEditModel;
import com.sjtu.onlinelibrary.web.viewmodel.Pager;

/**
 * @author Crystal
 *
 */
public interface IClassificationService {

	void save(final Classification classification) throws DataAccessException;
	
    Pager<ClassificationEditModel> findAll(int pageIndex) throws DataAccessException;
    
    ClassificationEditModel findById(String id) throws DataAccessException;
    
    boolean delete(String id);
    
    List<Classification> findAll() throws DataAccessException;
    
}
