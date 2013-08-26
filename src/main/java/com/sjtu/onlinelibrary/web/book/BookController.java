package com.sjtu.onlinelibrary.web.book;

import com.sjtu.onlinelibrary.DataAccessException;
import com.sjtu.onlinelibrary.service.impl.BookServiceImpl;
import com.sjtu.onlinelibrary.service.impl.CommentService;
import com.sjtu.onlinelibrary.web.viewmodel.BookViewModel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/book")
public class BookController {

    private BookServiceImpl bookService;
    private CommentService commentService;

    public void setBookService(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping("/{id}.do")
    public ModelAndView book(@PathVariable("id") String id) throws DataAccessException {
        BookViewModel bookViewModel = new BookViewModel();
        bookViewModel.setBook(this.bookService.findById(id).innerBookEntity());
        bookViewModel.setComments(this.commentService.findAll(id, 1));
//        return bookViewModel;
        return new ModelAndView("book/bookDetail","book",bookViewModel);
    }

}
