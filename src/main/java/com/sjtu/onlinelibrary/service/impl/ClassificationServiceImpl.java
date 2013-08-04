package com.sjtu.onlinelibrary.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.sjtu.onlinelibrary.DataAccessException;
import com.sjtu.onlinelibrary.MutableDataAccess;
import com.sjtu.onlinelibrary.entity.Classification;
import com.sjtu.onlinelibrary.service.BaseService;
import com.sjtu.onlinelibrary.service.IClassificationService;
import com.sjtu.onlinelibrary.web.viewmodel.ClassificationEditModel;
import com.sjtu.onlinelibrary.web.viewmodel.Pager;
import com.sjtu.onlinelibrary.web.viewmodel.Pagination;

/**
 * @author Crystal
 *
 */
public class ClassificationServiceImpl extends BaseService implements 	IClassificationService {

	public ClassificationServiceImpl(MutableDataAccess mutableDataAccess) {
		super(mutableDataAccess);
	}

	@Override
	public void save(Classification classification) throws DataAccessException {
		mutableDataAccess.save(classification);
	}

	@Override
	public Pager<ClassificationEditModel> findAll(int pageIndex) 	throws DataAccessException {
		if (pageIndex <= 0) {
            pageIndex = 1;
        }
        final List<Classification> classifications = mutableDataAccess.paging(Classification.class, pageIndex, Pagination.DEFAULT_PAGE_SIZE);
        final List<ClassificationEditModel> classificationEditModelList = new ArrayList<ClassificationEditModel>();
        for (final Classification classification : classifications) {
        	classificationEditModelList.add(new ClassificationEditModel("", classification));
        }
        final Pager<ClassificationEditModel> classificationPager = new Pager<ClassificationEditModel>(pageIndex);
        classificationPager.setListObject(classificationEditModelList);
        classificationPager.setTotalCount(mutableDataAccess.count(Classification.class));
        return classificationPager;
	}

	@Override
	public ClassificationEditModel findById(String id) throws DataAccessException {
		Classification classification = mutableDataAccess.findById(Classification.class, id);
        return new ClassificationEditModel("编辑类别", classification);
	}

	@Override
	public boolean delete(String id) {
		 try {
	            mutableDataAccess.delete(Classification.class, id);
	            return true;
	        } catch (DataAccessException e) {
	            return false;
	        }
	}

}
