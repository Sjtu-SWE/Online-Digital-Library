package com.sjtu.onlinelibrary.service;

import com.sjtu.onlinelibrary.DataAccessException;
import com.sjtu.onlinelibrary.entity.Chapter;
import com.sjtu.onlinelibrary.web.viewmodel.ChapterModel;
import com.sjtu.onlinelibrary.web.viewmodel.Pager;

import java.util.List;

public interface IChapterService {
    void save(final Chapter chapter) throws DataAccessException;
    Pager<ChapterModel> findAll(String bookId, int pageIndex) throws DataAccessException;
    ChapterModel findById(String id) throws DataAccessException;
    boolean delete(String id);
    int getNextOrderNumber(String bookId) throws DataAccessException;

    List<Chapter> findAll(String bookId) throws DataAccessException;
}
