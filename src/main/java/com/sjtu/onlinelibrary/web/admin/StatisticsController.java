package com.sjtu.onlinelibrary.web.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sjtu.onlinelibrary.DataAccessException;
import com.sjtu.onlinelibrary.service.IBookService;
import com.sjtu.onlinelibrary.util.LangUtil;
import com.sjtu.onlinelibrary.web.viewmodel.BookEditModel;
import com.sjtu.onlinelibrary.web.viewmodel.Pager;

@Controller
@RequestMapping("/admin/statistics")
public class StatisticsController {
	public static final String ADMIN_STATISTICS_BOOK_LIST = "admin/statistics/bookStatistics";
	public static final String PAGE_DATA = "pageData";
	
	 private IBookService bookService;
	 
	 public void setBookService(final IBookService bookService) {
	        this.bookService = bookService;
	 }
	 
	 @RequestMapping("/book.do")
	    public ModelAndView listBook(@RequestParam(value = "pageIndex", required = false) final String pageIndex) {
	        try {
	            int index = 0;
	            if (!LangUtil.isNullOrEmpty(pageIndex)) {
	                index = Integer.parseInt(pageIndex);
	            }
	            final Pager<BookEditModel> books = this.bookService.findAll(index,"-sellAmount,-userFavoriteAmount,-clickAmount,-userLikeAmount");
	            return new ModelAndView(ADMIN_STATISTICS_BOOK_LIST, PAGE_DATA, books);

	        } catch (DataAccessException e) {
	            return new ModelAndView("error");
	        }
	    }
}
