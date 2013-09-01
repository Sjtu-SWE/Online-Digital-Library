package com.sjtu.onlinelibrary.web.book;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.sjtu.onlinelibrary.DataAccessException;
import com.sjtu.onlinelibrary.service.AmountType;
import com.sjtu.onlinelibrary.service.impl.BookServiceImpl;
import com.sjtu.onlinelibrary.service.impl.ChapterServiceImpl;
import com.sjtu.onlinelibrary.service.impl.ClassificationServiceImpl;
import com.sjtu.onlinelibrary.service.impl.CommentService;
import com.sjtu.onlinelibrary.util.LangUtil;
import com.sjtu.onlinelibrary.util.SpringSecurityUtils;
import com.sjtu.onlinelibrary.web.viewmodel.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/book")
public class BookController {

    public static final String BOOK_CHAPTER_LIST = "book/chapterList";
    public static final String BOOK_BOOK_DETAIL = "book/bookDetail";
    public static final String BOOK_READER = "book/reader";
    public static final String BOOK_LIST_BYTYPE = "book/bookListByType";
    public static final String BOOK_SEARCH_RESULT = "book/searchResult";
    public static final String BOOK_SEARCH_BOOK = "book/searchBook";
    public static final String PAGE_DATE = "pageData";
    private BookServiceImpl bookService;
    private ChapterServiceImpl chapterService;
    private CommentService commentService;
    private ClassificationServiceImpl classificationService;

    public void setBookService(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    public void setChapterService(ChapterServiceImpl chapterService) {
        this.chapterService = chapterService;
    }

    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    public void setClassificationService(ClassificationServiceImpl classificationService) {
		this.classificationService = classificationService;
	}

	@RequestMapping("/{id}.do")
    public ModelAndView book(@PathVariable("id") String id) throws DataAccessException {
        BookViewModel bookViewModel = new BookViewModel();
        bookViewModel.setBook(this.bookService.findById(id).innerBookEntity());
        bookViewModel.setComments(this.commentService.findAll(id, 1));
//        return bookViewModel;
        ModelMap map = new ModelMap();
        map.put("book", bookViewModel);

        map.put("loginbtnClass", SpringSecurityUtils.isAuthenticated() ? "" : "unlogined");
        return new ModelAndView(BOOK_BOOK_DETAIL, map);
    }

    @RequestMapping("/{id}/read.do")
    public ModelAndView read(@PathVariable("id") String id) throws DataAccessException {
        ModelMap map = getMap(id);
        map.put("chapters", this.chapterService.findAll(id));
        return new ModelAndView(BOOK_CHAPTER_LIST, map);
    }

    @RequestMapping("/{bookId}/chapter/{id}.do")
    public ModelAndView chapter(@PathVariable("bookId") String bookId, @PathVariable("id") String id) throws DataAccessException {
        ModelMap map = getMap(bookId);
        ChapterReaderModel chapter= this.chapterService.findForReader(bookId,id);
        map.put("chapter",chapter);
        return new ModelAndView(BOOK_READER, map);
    }


    @RequestMapping("/increase.do")
    @ResponseBody
    public String increase(@RequestParam(required = true) String bookId, @RequestParam(required = true) AmountType amountType) throws DataAccessException {
        this.bookService.increaseAmount(bookId, amountType);
        return "ok";
    }

    /**
     * 根据图书类别分页显示图书信息
     * @param bookType
     */
    @RequestMapping("/{bookType}/list.do")
    public ModelAndView listBooksByType(@PathVariable("bookType") String bookId,@RequestParam(value = "pageIndex", required = false) final String pageIndex
    		,HttpServletRequest request) throws DataAccessException{
    	int index = 0;
        if (!LangUtil.isNullOrEmpty(pageIndex)) {
            index = Integer.parseInt(pageIndex);
        }
    	String bookType = classificationService.findById(bookId).getClassificationName();
    	 final Pager<BookEditModel> books = this.bookService.findBooksByType(index, bookId);
    	 request.setAttribute("category", bookType);
    	return new ModelAndView(BOOK_LIST_BYTYPE , PAGE_DATE, books);
    }

    /**
     * 显示搜索图书的结果
     * @param bookType
     */
    @RequestMapping("/searchBook.do")
    public ModelAndView searchBook(@RequestParam(required = true)  String name,@RequestParam(value = "pageIndex", required = false) final String pageIndex
    		,HttpServletRequest request) throws DataAccessException{
    	int index = 0;
        if (!LangUtil.isNullOrEmpty(pageIndex)) {
            index = Integer.parseInt(pageIndex);
        }
        Map<String, Object> condition = new HashMap<String, Object>();
        if( name!=null && !"".equals(name.trim()) ){
        	Pattern pattern = Pattern.compile(".*" + name.trim()+ ".*", Pattern.CASE_INSENSITIVE);
        	condition.put("name", pattern);
        }
        if(name==null || "".equals(name.trim())){
        	return new ModelAndView(BOOK_SEARCH_RESULT);
        }else{
        	 final Pager<BookEditModel> books = this.bookService.findBooksByName(index, condition);
        	 request.setAttribute("name", name);
        	return new ModelAndView(BOOK_SEARCH_RESULT , PAGE_DATE, books);
        }
    }
    
    /**
     * 图书高级搜索
     * @param bookType
     */
    @RequestMapping("/searchBooks.do")
    public ModelAndView searchBooks(@RequestParam  String name,@RequestParam String author,@RequestParam String bookNumber,@RequestParam String publisher,
    		@RequestParam String keywords,@RequestParam(value = "pageIndex", required = false) final String pageIndex ,HttpServletRequest request) throws DataAccessException{
    	int index = 0;
        if (!LangUtil.isNullOrEmpty(pageIndex)) {
            index = Integer.parseInt(pageIndex);
        }
        Map<String, Object> condition = new HashMap<String, Object>();
        Pattern pattern = null;
        if( name!=null && !"".equals(name.trim()) ){
        	pattern = Pattern.compile(".*" + name.trim()+ ".*", Pattern.CASE_INSENSITIVE);
        	condition.put("name", pattern);
        }
        if( author!=null && !"".equals(author.trim()) ){
        	pattern = Pattern.compile(".*" + author.trim()+ ".*", Pattern.CASE_INSENSITIVE);
        	condition.put("author", pattern);
        }
        if( bookNumber!=null && !"".equals(bookNumber.trim()) ){
        	pattern = Pattern.compile(".*" + bookNumber.trim()+ ".*", Pattern.CASE_INSENSITIVE);
        	condition.put("bookNumber", pattern);
        }
        if( publisher!=null && !"".equals(publisher.trim()) ){
        	pattern = Pattern.compile(".*" + publisher.trim()+ ".*", Pattern.CASE_INSENSITIVE);
        	condition.put("publisher", pattern);
        }
        if( keywords!=null && !"".equals(keywords.trim()) ){
        	pattern = Pattern.compile(".*" + keywords.trim()+ ".*", Pattern.CASE_INSENSITIVE);
        	condition.put("keywords", pattern);
        }
        if( (name==null || "".equals(name.trim()) ) && (author==null || "".equals(author.trim()) ) && (bookNumber==null || "".equals(bookNumber.trim()) )
        		&& (publisher==null || "".equals(publisher.trim()) ) && (keywords==null || "".equals(keywords.trim()) )){
        	return new ModelAndView(BOOK_SEARCH_BOOK );
        }else{
        	 final Pager<BookEditModel> books = this.bookService.findBooksByName(index, condition);
        	 request.setAttribute("name", name);
        	 request.setAttribute("author", author);
        	 request.setAttribute("bookNumber", bookNumber);
        	 request.setAttribute("publisher", publisher);
        	 request.setAttribute("keywords", keywords);
        	return new ModelAndView(BOOK_SEARCH_BOOK , PAGE_DATE, books);
        }
    }
    
    public ModelMap getMap(String bookId) throws DataAccessException {
        ModelMap map = new ModelMap();
        map.put("book", this.bookService.findById(bookId));
        return map;
    }
}
