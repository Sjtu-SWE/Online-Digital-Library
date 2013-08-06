package com.sjtu.onlinelibrary.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Huabei Yin <a href="mailto:huabei.yin@autodesk.com">huabei.yin@autodesk.com</a>
 */
@Controller
public class HomeController {
    public static final String ADMIN_DASHBOARD = "admin/dashboard";

    @RequestMapping("/admin/dashboard.do")
    public String dashboard(){
        return ADMIN_DASHBOARD;
    }

}
