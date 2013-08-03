package com.sjtu.onlinelibrary.web.admin;

import com.sjtu.onlinelibrary.DataAccessException;
import com.sjtu.onlinelibrary.entity.Book;
import com.sjtu.onlinelibrary.service.IBookService;
import com.sjtu.onlinelibrary.web.viewmodel.BookEditModel;
import com.sjtu.onlinelibrary.web.viewmodel.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.ws.rs.PathParam;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-8-3
 * Time: 下午1:17
 */
@Controller
@RequestMapping("/admin/book")
public class BookMgrController {
    public static final String ADMIN_BOOK_MGR_LIST = "admin/bookMgr/list";
    public static final String ADMIN_BOOK_MGR_EDIT = "admin/bookMgr/edit";
    private IBookService bookService;

    public void setBookService(IBookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/list.do")
    public ModelAndView list() {
        try {
            Pager<Book> books = this.bookService.findAll();
            return new ModelAndView(ADMIN_BOOK_MGR_LIST, "books", books);

        } catch (DataAccessException e) {
            return new ModelAndView("error");
        }
    }

    @RequestMapping("/create.do")
    public ModelAndView create() {
        return new ModelAndView(ADMIN_BOOK_MGR_EDIT, "book", new BookEditModel("创建书籍",new Book()));
    }

    @RequestMapping("/{id}/edit.do")
    public ModelAndView edit(@PathParam("id") final String id) {
        try {
            Pager<Book> books = this.bookService.findAll();
            return new ModelAndView(ADMIN_BOOK_MGR_LIST, "book", books);

        } catch (DataAccessException e) {
            return new ModelAndView("error");
        }
    }

    @RequestMapping("/save.do")
    public ModelAndView save() {
        return new ModelAndView("redirect:list.do");

    }
}
