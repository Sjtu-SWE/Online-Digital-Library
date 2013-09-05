package com.sjtu.onlinelibrary.web.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sjtu.onlinelibrary.DataAccessException;
import com.sjtu.onlinelibrary.service.IBookService;
import com.sjtu.onlinelibrary.service.IUserService;
import com.sjtu.onlinelibrary.util.LangUtil;
import com.sjtu.onlinelibrary.web.viewmodel.BookEditModel;
import com.sjtu.onlinelibrary.web.viewmodel.Pager;
import com.sjtu.onlinelibrary.web.viewmodel.StatisticsUserModel;

@Controller
@RequestMapping("/admin/statistics")
public class StatisticsController {
	public static final String ADMIN_STATISTICS_BOOK_LIST = "admin/statistics/bookStatistics";
	public static final String ADMIN_STATISTICS_USER_LIST = "admin/statistics/userStatistics";
	public static final String PAGE_DATA = "pageData";
	
	 private IBookService bookService;
	 private IUserService userService;
	 
	 public void setBookService(final IBookService bookService) {
	        this.bookService = bookService;
	 }
	 
	public void setUserService(IUserService userService) {
	        this.userService = userService;
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
	    public ModelAndView listUser(@RequestParam(value = "pageIndex", required = false) final String pageIndex)  throws DataAccessException, ParseException{
			 int index = 0;
	         if (!LangUtil.isNullOrEmpty(pageIndex)) {
	             index = Integer.parseInt(pageIndex);
	         }
			 Date fromDate = userService.findAll(index, "createDate").get(0).getCreateDate();//最早注册用户
			 Date toDate = userService.findAll(index, "-createDate").get(0).getCreateDate();//最晚注册用户
			 long diff = getMonthDiff(LangUtil.getDefaultDateFormat().format(fromDate), LangUtil.getDefaultDateFormat().format(toDate));
			 Calendar cal = Calendar.getInstance();
			 SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			 SimpleDateFormat fmt2 = new SimpleDateFormat("yyyy-MM");
			 String temp = fmt.format(fromDate);
		     cal.setTime(fmt.parse(temp));
		     
		     Map<String, Object> condition = new HashMap<String, Object>();
		     List<StatisticsUserModel> countList = new ArrayList<StatisticsUserModel>();
			 for(int i=0 ; i<diff+1 ; i++){
				 	condition.clear();
				 	Date fromTimeDate = cal.getTime();
		        	cal.set(Calendar.DAY_OF_MONTH,1);//获取当月第一天
		        	condition.put("createdOn > ", cal.getTime());//传入日期MONTH
		        	
		        	cal.add(Calendar.MONTH, 1);      	
		        	condition.put("createdOn < ", cal.getTime());//传入日期MONTH
		        	countList.add(new StatisticsUserModel(fmt2.format(fromTimeDate), String.valueOf(userService.countUser(condition))));
		        }
			 Map<String, Object> map = new HashMap<String, Object>();
			 map.put("userCount", countList);
			 return new ModelAndView(ADMIN_STATISTICS_USER_LIST, map);
	 }
	
//	 public static void main(String[] arg) throws Exception{
//		 System.out.print(getMonthDiff("2013-08-27","2013-09-04"));
//	 }
	 /**
	     * 得到两日期相差几个月
	     *
	     * @param String
	     * @return
	 * @throws ParseException 
	     */
	    public static long getMonthDiff(String startDate, String endDate) throws DataAccessException, ParseException{
	        long monthday;
	        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
	        Date startDate1 = fmt.parse(startDate);
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
	        return monthday;
	    }
	
}
