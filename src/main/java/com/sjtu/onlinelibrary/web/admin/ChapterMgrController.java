package com.sjtu.onlinelibrary.web.admin;

import com.sjtu.onlinelibrary.DataAccessException;
import com.sjtu.onlinelibrary.entity.Chapter;
import com.sjtu.onlinelibrary.service.impl.BookServiceImpl;
import com.sjtu.onlinelibrary.service.impl.ChapterServiceImpl;
import com.sjtu.onlinelibrary.util.LangUtil;
import com.sjtu.onlinelibrary.web.viewmodel.ChapterModel;
import com.sjtu.onlinelibrary.web.viewmodel.Pager;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/admin/book/{bookId}/chapter")
public class ChapterMgrController {
    public static final String ADMIN_CHAPTER_MGR_EDIT = "admin/chapterMgr/edit";
    public static final String ADMIN_CHAPTER_MGR_LIST = "admin/chapterMgr/list";
    public static final String CHAPTER = "chapter";
    private ChapterServiceImpl chapterService;
    public static final String PAGE_DATA = "pageData";
    private BookServiceImpl bookService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Integer.class, "orderNumber", new CustomNumberEditor(Integer.class, null, false));
    }

    public void setChapterService(ChapterServiceImpl chapterService) {
        this.chapterService = chapterService;
    }

    public void setBookService(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/list.do")
    public ModelAndView list(@PathVariable("bookId") String bookId, @RequestParam(value = "pageIndex", required = false) final String pageIndex) {
        try {
            int index = 0;
            if (!LangUtil.isNullOrEmpty(pageIndex)) {
                index = Integer.parseInt(pageIndex);
            }
            final Map<String, Object> map = getMapForEdit(bookId);
            final Pager<ChapterModel> chapters = this.chapterService.findAll(bookId, index);
            map.put(PAGE_DATA, chapters);

            return new ModelAndView(ADMIN_CHAPTER_MGR_LIST, map);

        } catch (DataAccessException e) {
            return new ModelAndView("error");
        }
    }

    @RequestMapping("/create.do")
    public ModelAndView create(@PathVariable("bookId") String bookId) throws DataAccessException {
        final Map<String, Object> map = getMapForEdit(bookId);
        ChapterModel chapterModel=new ChapterModel();
        chapterModel.setOrderNumber(this.chapterService.getNextOrderNumber(bookId));
        map.put(CHAPTER, chapterModel);
        return new ModelAndView(ADMIN_CHAPTER_MGR_EDIT, map);
    }

    @RequestMapping("/{id}/edit.do")
    public ModelAndView edit(@PathVariable("bookId") String bookId, @PathVariable("id") final String id) {
        try {
            final ChapterModel chapter = this.chapterService.findById(id);
            final Map<String, Object> map = getMapForEdit(bookId);
            map.put(CHAPTER, chapter);
            return new ModelAndView(ADMIN_CHAPTER_MGR_EDIT, map);

        } catch (DataAccessException e) {
            return new ModelAndView("error");
        }
    }

    @RequestMapping(value = "/save.do", method = RequestMethod.POST)
    public ModelAndView save(@PathVariable("bookId") String bookId, @Valid @ModelAttribute("chapter") final ChapterModel chapterModel, final BindingResult bindingResult) throws DataAccessException {
        if (bindingResult.hasErrors()) {
            final Map<String, Object> map = getMapForEdit(bookId);
            chapterModel.setEditType("编辑书籍");
            map.put(CHAPTER, chapterModel);
            return new ModelAndView(ADMIN_CHAPTER_MGR_EDIT, map);
        }

        chapterModel.getChapterEntity().setBookId(bookId);
        chapterModel.getChapterEntity().setContent(chapterModel.getContent().replaceAll("&nbsp;", " ").replaceAll("<br>", "").replaceAll("<br />", ""));
        ChapterModel chapter = this.chapterService.findById(chapterModel.getId());
        if (chapter.getChapterEntity() == null) {
            chapterService.save(chapterModel.getChapterEntity());
        } else {
            chapter.setBookId(bookId);
            chapter.setContent(chapterModel.getContent());
            chapter.setTitle(chapterModel.getTitle());
            chapterService.save(chapter.getChapterEntity());
        }
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", "保存章节成功！");
        map.put("url", "/admin/book/" + bookId + "/chapter/list.do");
        return new ModelAndView("forward:/success.jsp", map);

    }

    @RequestMapping("/{id}/delete.do")
    public ModelAndView delete(@PathVariable("bookId") String bookId, @PathVariable("id") final String id) {
        String result = "删除章节失败！";
        if (chapterService.delete(id)) {
            result = "删除章节成功！";
        }
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", result);
        map.put("url", "/admin/book/" + bookId + "/chapter/list.do");
        return new ModelAndView("forward:/success.jsp", map);
    }

    private Map<String, Object> getMapForEdit(String bookId) throws DataAccessException {
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("book", this.bookService.findById(bookId));
        return map;
    }
}
