package com.sjtu.onlinelibrary.web.admin;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
	 
	 @RequestMapping("/user.do")
	    public ModelAndView listUser(@RequestParam(value = "pageIndex", required = false) final String pageIndex) {
		 
		 return null;
	 }
	 
	 public static void main(String[] args) throws Exception{
		 System.out.println(getMonthDiff("2013-01-21", "2013-10-01"));
	 }
	 /**
	     * 得到两日期相差几个月
	     *
	     * @param String
	     * @return
	     */
	    public static long getMonthDiff(String startDate, String endDate) throws Exception {
	        long monthday;
	        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
	        Date startDate1 = fmt.parse(startDate);
	        System.out.println("---------StartDate:"+startDate1);
	        Calendar starCal = Calendar.getInstance();
	        starCal.setTime(startDate1);

	        int sYear  = starCal.get(Calendar.YEAR);
	        int sMonth = starCal.get(Calendar.MONTH);
	        int sDay   = starCal.get(Calendar.DAY_OF_MONTH);

	        Date endDate1 = fmt.parse(endDate);
	        Calendar endCal = Calendar.getInstance();
	        endCal.setTime(endDate1);
	        int eYear  = endCal.get(Calendar.YEAR);
	        int eMonth = endCal.get(Calendar.MONTH);
	        int eDay   = endCal.get(Calendar.DAY_OF_MONTH);

	        monthday = ((eYear - sYear) * 12 + (eMonth - sMonth));
	        
	        //这里计算零头的情况，根据实际确定是否要加1 还是要减1
	        if (sDay < eDay) {
	            monthday = monthday + 1;
	        }
	        for(int i=1;i<monthday+1;i++){
	        	starCal.add(Calendar.MONTH, 1);
	        	 System.out.println("========="+starCal.getTime());
	        }
	        return monthday;
	    }
	
}
