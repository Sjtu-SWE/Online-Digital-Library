package com.sjtu.onlinelibrary.web.admin;

import com.sjtu.onlinelibrary.DataAccessException;
import com.sjtu.onlinelibrary.service.IBookService;
import com.sjtu.onlinelibrary.service.IBusinessService;
import com.sjtu.onlinelibrary.service.impl.BookServiceImpl;
import com.sjtu.onlinelibrary.service.impl.BusinessServiceImpl;
import com.sjtu.onlinelibrary.util.LangUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-9-3
 * Time: 上午1:11
 */
@Controller
@RequestMapping("/admin/pointCard")
public class PointCordMgrController {
    public static final String ADMIN_POINT_CARD_MGR_LIST = "admin/pointCardMgr/list";
    private IBusinessService businessService;

    @RequestMapping("/list.do")
    public ModelAndView list(@RequestParam(value = "pageIndex", required = false) final String pageIndex) throws DataAccessException {
        int index = 0;
        if (!LangUtil.isNullOrEmpty(pageIndex)) {
            index = Integer.parseInt(pageIndex);
        }
        return new ModelAndView(ADMIN_POINT_CARD_MGR_LIST,BookMgrController.PAGE_DATA,businessService.pagingAll(index));
    }

    @RequestMapping("/generate.do")
    public ModelAndView generate() throws DataAccessException {
        this.businessService.generatePointCord(50,100);
        return new ModelAndView("redirect:/admin/pointCard/list.do");
    }

    public void setBusinessService(IBusinessService businessService) {
        this.businessService = businessService;
    }
}
