package com.sjtu.onlinelibrary.service.impl;

import com.sjtu.onlinelibrary.DataAccessException;
import com.sjtu.onlinelibrary.MutableDataAccess;
import com.sjtu.onlinelibrary.entity.Chapter;
import com.sjtu.onlinelibrary.service.BaseService;
import com.sjtu.onlinelibrary.service.IChapterService;
import com.sjtu.onlinelibrary.web.viewmodel.ChapterModel;
import com.sjtu.onlinelibrary.web.viewmodel.ChapterReaderModel;
import com.sjtu.onlinelibrary.web.viewmodel.Pager;
import com.sjtu.onlinelibrary.web.viewmodel.Pagination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChapterServiceImpl extends BaseService implements IChapterService {
    public ChapterServiceImpl(final MutableDataAccess mutableDataAccess) {
        super(mutableDataAccess);
    }

    @Override
    public void save(final Chapter chapter) throws DataAccessException {
        mutableDataAccess.save(chapter);
    }

    @Override
    public Pager<ChapterModel> findAll(String bookId, int pageIndex) throws DataAccessException {
        if (pageIndex <= 0) {
            pageIndex = 1;
        }
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("bookId", bookId);
        final List<Chapter> chapters = mutableDataAccess.paging(Chapter.class, pageIndex, Pagination.DEFAULT_PAGE_SIZE, condition, "orderNumber");
        final List<ChapterModel> chapterModels = new ArrayList<ChapterModel>();
        for (final Chapter book : chapters) {
            chapterModels.add(new ChapterModel("", book));
        }
        final Pager<ChapterModel> chapterModelPager = new Pager<ChapterModel>(pageIndex);
        chapterModelPager.setListObject(chapterModels);
        chapterModelPager.setTotalCount(mutableDataAccess.count(Chapter.class, condition));
        return chapterModelPager;
    }

    @Override
    public ChapterModel findById(String id) throws DataAccessException {
        Chapter chapter = mutableDataAccess.findById(Chapter.class, id);
        return new ChapterModel("编辑章节", chapter);
    }

    @Override
    public ChapterReaderModel findForReader(String bookId,String id) throws DataAccessException {
        ChapterReaderModel chapterReaderModel=new ChapterReaderModel(this.findById(id).getChapterEntity());
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("bookId", bookId);
        condition.put("orderNumber >",chapterReaderModel.getCurrent().getOrderNumber());
        List<Chapter> chaptersNexts=this.mutableDataAccess.paging(Chapter.class,1,1,condition,"orderNumber");
        chapterReaderModel.setHasNext(chaptersNexts.size()>0);

        if(chapterReaderModel.isHasNext()){
            chapterReaderModel.setNext(chaptersNexts.get(0));
        }
        condition.remove("orderNumber >");
        condition.put("orderNumber <",chapterReaderModel.getCurrent().getOrderNumber());
        List<Chapter> chaptersPrevious=this.mutableDataAccess.paging(Chapter.class,1,1,condition,"orderNumber");

        chapterReaderModel.setHasPrevious(chaptersPrevious.size()>0);
        if(chapterReaderModel.isHasPrevious()){
            chapterReaderModel.setPrevious(chaptersPrevious.get(0));
        }
        return chapterReaderModel;
    }

    @Override
    public boolean delete(String id) {
        try {
            mutableDataAccess.delete(Chapter.class, id);
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }

    @Override
    public int getNextOrderNumber(String bookId) throws DataAccessException {
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("bookId", bookId);
        List<Chapter> chapters = mutableDataAccess.paging(Chapter.class, 1, 1, condition, "-orderNumber");
        if (chapters == null || chapters.size() == 0) {
            return 1;
        } else {
            return chapters.get(0).getOrderNumber() + 1;
        }
    }

    @Override
    public List<Chapter> findAll(String bookId) throws DataAccessException {
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("bookId", bookId);
        return mutableDataAccess.listByFilter(Chapter.class, condition);
    }
}
