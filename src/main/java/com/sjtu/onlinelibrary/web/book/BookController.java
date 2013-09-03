package com.sjtu.onlinelibrary.web.book;

import com.sjtu.onlinelibrary.DataAccessException;
import com.sjtu.onlinelibrary.entity.Comment;
import com.sjtu.onlinelibrary.entity.UserBook;
import com.sjtu.onlinelibrary.service.*;
import com.sjtu.onlinelibrary.util.LangUtil;
import com.sjtu.onlinelibrary.util.SpringSecurityUtils;
import com.sjtu.onlinelibrary.web.viewmodel.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@RequestMapping("/book")
public class BookController {

    public static final String BOOK_CHAPTER_LIST = "book/chapterList";
    public static final String BOOK_BOOK_DETAIL = "book/bookDetail";
    public static final String BOOK_READER = "book/reader";
    public static final String BOOK_LIST_BYTYPE = "book/bookListByType";
    public static final String BOOK_SEARCH_RESULT = "book/searchResult";
    public static final String BOOK_SEARCH_BOOK = "book/searchBook";
    public static final String BOOK_BOOKLIBRARY = "book/bookLibrary";
    public static final String BOOK_LISTBOOKSBYCLICK = "book/listBooksByClick";
    public static final String BOOK_LISTBOOKSBYSELL = "book/listBooksBySell";
    public static final String BOOK_LISTBOOKSBYLIKE = "book/listBooksByUserLike";
    public static final String BOOK_LISTBOOKSBYFAVORITE = "book/listBooksByUserFavorite";
    public static final String BOOK_LISTBOOKSBYRECOMMEND = "book/listBooksByRecommend";
    public static final String USER_BOOK_SHELF = "user/bookShelf";

    public static final String PAGE_DATA = "pageData";
    private IBookService bookService;
    private IChapterService chapterService;
    private ICommentServcie commentService;
    private IClassificationService classificationService;
    private IBusinessService businessService;

    public void setBookService(IBookService bookService) {
        this.bookService = bookService;
    }

    public void setChapterService(IChapterService chapterService) {
        this.chapterService = chapterService;
    }

    public void setCommentService(ICommentServcie commentService) {
        this.commentService = commentService;
    }

    public void setClassificationService(IClassificationService classificationService) {
        this.classificationService = classificationService;
    }

    public void setBusinessService(IBusinessService businessService) {
        this.businessService = businessService;
    }

    @RequestMapping("/{id}.do")
    public ModelAndView book(@PathVariable("id") String id, @RequestParam(value = "pageIndex", required = false) final String pageIndex) throws DataAccessException {
        int index = 0;
        if (!LangUtil.isNullOrEmpty(pageIndex)) {
            index = Integer.parseInt(pageIndex);
        }
        BookViewModel bookViewModel = new BookViewModel();
        bookViewModel.setBook(this.bookService.findById(id).innerBookEntity());
        ModelMap map = new ModelMap();
        map.put("book", bookViewModel);
        map.put(PAGE_DATA, this.commentService.findAll(id, index));
        map.put("loginbtnClass", SpringSecurityUtils.isAuthenticated() ? "" : "unlogined");
        BusinessResult result = new BusinessResult(BusinessResult.ResultStatus.OK, "");
        if (bookViewModel.getBook().getPrice() > 0) {
            if (SpringSecurityUtils.isAuthenticated()) {
                result = this.businessService.hasPurchased(SpringSecurityUtils.getCurrentUser().getId(), id);
            } else {
                result = new BusinessResult(BusinessResult.ResultStatus.FAIL, "您还未登陆,请登录后阅读");
            }
        }
        map.put("purchased", result);
        map.put("recommendBooks", this.bookService.findAll(0, "-sellAmount,-userFavoriteAmount,-clickAmount,-userLikeAmount").getList().subList(0, 5));
        return new ModelAndView(BOOK_BOOK_DETAIL, map);
    }

    @RequestMapping("/{id}/read.do")
    public ModelAndView read(@PathVariable("id") String id) throws DataAccessException {
        ModelMap map = getMap(id);
        map.put("chapters", this.chapterService.findAll(id));
        checkPurchase(id, map);
        return new ModelAndView(BOOK_CHAPTER_LIST, map);
    }

    private void checkPurchase(final String id, final ModelMap map) throws DataAccessException {
        BookEditModel bookViewModel = (BookEditModel) map.get("book");
        BusinessResult result = new BusinessResult(BusinessResult.ResultStatus.OK, "");
        if (bookViewModel.getPrice() > 0) {
            if (SpringSecurityUtils.isAuthenticated()) {
                result = this.businessService.hasPurchased(SpringSecurityUtils.getCurrentUser().getId(), id);
            } else {
                result = new BusinessResult(BusinessResult.ResultStatus.FAIL, "您还未登陆,请登录后阅读");
            }
        }
        map.put("purchased", result);
    }


    @RequestMapping("/{bookId}/chapter/{id}.do")
    public ModelAndView chapter(@PathVariable("bookId") String bookId, @PathVariable("id") String id) throws DataAccessException {
        ModelMap map = getMap(bookId);
        ChapterReaderModel chapter = this.chapterService.findForReader(bookId, id);
        map.put("chapter", chapter);
        checkPurchase(bookId,map);
        return new ModelAndView(BOOK_READER, map);
    }

    @RequestMapping("/increase.do")
    @ResponseBody
    public BusinessResult increase(@RequestParam(required = true) String bookId, @RequestParam(required = true) AmountType amountType) throws DataAccessException {
        BusinessResult businessResult = this.bookService.increaseAmount(bookId, SpringSecurityUtils.getCurrentUser().getId(), amountType);
        return businessResult;
    }

    /**
     * 根据图书类别分页显示图书信息
     *
     * @param bookType
     */
    @RequestMapping("/{bookType}/list.do")
    public ModelAndView listBooksByType(@PathVariable("bookType") String bookId, @RequestParam(value = "pageIndex", required = false) final String pageIndex
            , HttpServletRequest request) throws DataAccessException {
        int index = 0;
        if (!LangUtil.isNullOrEmpty(pageIndex)) {
            index = Integer.parseInt(pageIndex);
        }
        String bookType = classificationService.findById(bookId).getClassificationName();
        final Pager<BookEditModel> books = this.bookService.findBooksByType(index, bookId);
        request.setAttribute("category", bookType);
        return new ModelAndView(BOOK_LIST_BYTYPE, PAGE_DATA, books);
    }

    @RequestMapping(value = "/{bookId}/comment/add.do", method = RequestMethod.POST)
    @ResponseBody
    public String addComment(@PathVariable("bookId") String bookId, @RequestParam(required = true) String content) throws DataAccessException {

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setBookId(bookId);
        comment.setUserName(SpringSecurityUtils.getCurrentUser().getRealName());
        comment.setUserId(SpringSecurityUtils.getCurrentUser().getId());
        this.commentService.save(comment);
        return "ok";
    }

    /**
     * 显示搜索图书的结�?
     *
     * @param bookType
     */
    @RequestMapping("/searchBook.do")
    public ModelAndView searchBook(@RequestParam(required = true) String name, @RequestParam(value = "pageIndex", required = false) final String pageIndex
            , HttpServletRequest request) throws DataAccessException {
        int index = 0;
        if (!LangUtil.isNullOrEmpty(pageIndex)) {
            index = Integer.parseInt(pageIndex);
        }
        Map<String, Object> condition = new HashMap<String, Object>();
        if (name != null && !"".equals(name.trim())) {
            Pattern pattern = Pattern.compile(".*" + name.trim() + ".*", Pattern.CASE_INSENSITIVE);
            condition.put("name", pattern);
        }
        if (name == null || "".equals(name.trim())) {
            return new ModelAndView(BOOK_SEARCH_RESULT);
        } else {
            final Pager<BookEditModel> books = this.bookService.findBooksByName(index, condition);
            request.setAttribute("name", name);
            return new ModelAndView(BOOK_SEARCH_RESULT, PAGE_DATA, books);
        }
    }

    /**
     * 图书高级搜索
     *
     * @param bookType
     */
    @RequestMapping("/searchBooks.do")
    public ModelAndView searchBooks(@RequestParam String name, @RequestParam String author, @RequestParam String bookNumber, @RequestParam String publisher,
                                    @RequestParam String keywords, @RequestParam(value = "pageIndex", required = false) final String pageIndex, HttpServletRequest request) throws DataAccessException {
        int index = 0;
        if (!LangUtil.isNullOrEmpty(pageIndex)) {
            index = Integer.parseInt(pageIndex);
        }
        Map<String, Object> condition = new HashMap<String, Object>();
        Pattern pattern = null;
        if (name != null && !"".equals(name.trim())) {
            pattern = Pattern.compile(".*" + name.trim() + ".*", Pattern.CASE_INSENSITIVE);
            condition.put("name", pattern);
        }
        if (author != null && !"".equals(author.trim())) {
            pattern = Pattern.compile(".*" + author.trim() + ".*", Pattern.CASE_INSENSITIVE);
            condition.put("author", pattern);
        }
        if (bookNumber != null && !"".equals(bookNumber.trim())) {
            pattern = Pattern.compile(".*" + bookNumber.trim() + ".*", Pattern.CASE_INSENSITIVE);
            condition.put("bookNumber", pattern);
        }
        if (publisher != null && !"".equals(publisher.trim())) {
            pattern = Pattern.compile(".*" + publisher.trim() + ".*", Pattern.CASE_INSENSITIVE);
            condition.put("publisher", pattern);
        }
        if (keywords != null && !"".equals(keywords.trim())) {
            pattern = Pattern.compile(".*" + keywords.trim() + ".*", Pattern.CASE_INSENSITIVE);
            condition.put("keywords", pattern);
        }
        if ((name == null || "".equals(name.trim())) && (author == null || "".equals(author.trim())) && (bookNumber == null || "".equals(bookNumber.trim()))
                && (publisher == null || "".equals(publisher.trim())) && (keywords == null || "".equals(keywords.trim()))) {
            return new ModelAndView(BOOK_SEARCH_BOOK);
        } else {
            final Pager<BookEditModel> books = this.bookService.findBooksByName(index, condition);
            request.setAttribute("name", name);
            request.setAttribute("author", author);
            request.setAttribute("bookNumber", bookNumber);
            request.setAttribute("publisher", publisher);
            request.setAttribute("keywords", keywords);
            return new ModelAndView(BOOK_SEARCH_BOOK, PAGE_DATA, books);
        }
    }

    /**
     * 书库
     */
    @RequestMapping("/bookLibrary.do")
    public ModelAndView bookLibrary(@RequestParam(value = "pageIndex", required = false) final String pageIndex, HttpServletRequest request) throws DataAccessException {
        int index = 0;
        if (!LangUtil.isNullOrEmpty(pageIndex)) {
            index = Integer.parseInt(pageIndex);
        }
        final Pager<BookEditModel> books = this.bookService.findAll(index);
//    	 request.setAttribute("", );
        return new ModelAndView(BOOK_BOOKLIBRARY, PAGE_DATA, books);
    }

    @RequestMapping(value = "/{bookId}/addToBookshelf.do", method = RequestMethod.POST)
    @ResponseBody
    public BusinessResult addToBookshelf(@PathVariable("bookId") String bookId) throws DataAccessException {
        return this.businessService.addToBookshelf(SpringSecurityUtils.getCurrentUser().getId(), bookId);
    }

    @RequestMapping(value = "/{bookId}/buy.do", method = RequestMethod.POST)
    @ResponseBody
    public BusinessResult buy(@PathVariable("bookId") String bookId) throws DataAccessException {
        return this.businessService.buy(SpringSecurityUtils.getCurrentUser().getId(), bookId);
    }

    public ModelMap getMap(String bookId) throws DataAccessException {
        ModelMap map = new ModelMap();
        map.put("book", this.bookService.findById(bookId));
        return map;
    }

    /**
     * 按照点击量得到图书排行榜
     */
    @RequestMapping("/listBooksByClick.do")
    public ModelAndView listByClick(@RequestParam(value = "pageIndex", required = false) final String pageIndex) {
        try {
            int index = 0;
            if (!LangUtil.isNullOrEmpty(pageIndex)) {
                index = Integer.parseInt(pageIndex);
            }
            final Pager<BookEditModel> books = this.bookService.findAll(index, "-clickAmount");
            return new ModelAndView(BOOK_LISTBOOKSBYCLICK, PAGE_DATA, books);
        } catch (DataAccessException e) {
            return new ModelAndView("error");
        }
    }

    /**
     * 按照购买量得到图书排行榜
     */
    @RequestMapping("/listBooksBySell.do")
    public ModelAndView listBySell(@RequestParam(value = "pageIndex", required = false) final String pageIndex) {
        try {
            int index = 0;
            if (!LangUtil.isNullOrEmpty(pageIndex)) {
                index = Integer.parseInt(pageIndex);
            }
            final Pager<BookEditModel> books = this.bookService.findAll(index, "-sellAmount");
            return new ModelAndView(BOOK_LISTBOOKSBYSELL, PAGE_DATA, books);
        } catch (DataAccessException e) {
            return new ModelAndView("error");
        }
    }

    /**
     * 按照鲜花量得到图书排行榜
     */
    @RequestMapping("/listBooksByUserLike.do")
    public ModelAndView listByUserLike(@RequestParam(value = "pageIndex", required = false) final String pageIndex) {
        try {
            int index = 0;
            if (!LangUtil.isNullOrEmpty(pageIndex)) {
                index = Integer.parseInt(pageIndex);
            }
            final Pager<BookEditModel> books = this.bookService.findAll(index, "-userLikeAmount");
            return new ModelAndView(BOOK_LISTBOOKSBYLIKE, PAGE_DATA, books);
        } catch (DataAccessException e) {
            return new ModelAndView("error");
        }
    }

    /**
     * 按照收藏量得到图书排行榜
     */
    @RequestMapping("/listBooksByUserFavorite.do")
    public ModelAndView listByUserFavorite(@RequestParam(value = "pageIndex", required = false) final String pageIndex) {
        try {
            int index = 0;
            if (!LangUtil.isNullOrEmpty(pageIndex)) {
                index = Integer.parseInt(pageIndex);
            }
            final Pager<BookEditModel> books = this.bookService.findAll(index, "-userFavoriteAmount");
            return new ModelAndView(BOOK_LISTBOOKSBYFAVORITE, PAGE_DATA, books);
        } catch (DataAccessException e) {
            return new ModelAndView("error");
        }
    }


    /**
     * 图书的系统推荐
     */
    @RequestMapping("/listBooksByRecommend.do")
    public ModelAndView listByRecommend(@RequestParam(value = "pageIndex", required = false) final String pageIndex) {
        try {
            int index = 0;
            if (!LangUtil.isNullOrEmpty(pageIndex)) {
                index = Integer.parseInt(pageIndex);
            }
            final Pager<BookEditModel> books = this.bookService.findAll(index, "-sellAmount,-userFavoriteAmount,-clickAmount,-userLikeAmount");
            return new ModelAndView(BOOK_LISTBOOKSBYRECOMMEND, PAGE_DATA, books);
        } catch (DataAccessException e) {
            return new ModelAndView("error");
        }
    }


    @RequestMapping("/myBookShelf.do")
    public ModelAndView myBookShelf() throws DataAccessException {
        ModelMap map = new ModelMap();
        map.put("favoriteBooks", bookService.findUserBook(SpringSecurityUtils.getCurrentUser().getId(), false));
        map.put("purchasedBooks", bookService.findUserBook(SpringSecurityUtils.getCurrentUser().getId(), true));
        return new ModelAndView(USER_BOOK_SHELF, map);
    }

    @RequestMapping("/myBookShelf/{id}/delete.do")
    @ResponseBody
    public Object removeUserBook(@PathVariable("id") String id) throws DataAccessException {
        UserBook userBook = bookService.deleteUserBook(id);
        bookService.decreaseAmount(userBook.getBookId(), AmountType.userFavoriteAmount);
        return new BusinessResult(BusinessResult.ResultStatus.OK, "移除成功！");
    }

    @RequestMapping(value = "/recharge.do", method = RequestMethod.POST)
    @ResponseBody
    public BusinessResult recharge(@RequestParam(required = true) String serialNumber) throws DataAccessException {
        if (!SpringSecurityUtils.isAuthenticated())
            return new BusinessResult(BusinessResult.ResultStatus.FAIL, "您还没有登陆，请登陆");
        return businessService.recharge(SpringSecurityUtils.getCurrentUser().getId(), serialNumber);
    }
}
