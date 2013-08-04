package com.sjtu.onlinelibrary.web.admin;

import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.sjtu.onlinelibrary.DataAccessException;
import com.sjtu.onlinelibrary.entity.Classification;
import com.sjtu.onlinelibrary.service.IClassificationService;
import com.sjtu.onlinelibrary.util.LangUtil;
import com.sjtu.onlinelibrary.web.viewmodel.ClassificationEditModel;
import com.sjtu.onlinelibrary.web.viewmodel.Pager;

/**
 * @author Crystal
 *
 */
@Controller
@RequestMapping("/admin/classification")
public class ClassificationController {
	    public static final String ADMIN_CLASSIFICATION_MGR_LIST = "admin/classificationMgr/list";
	    public static final String ADMIN_CLASSIFICATION_MGR_EDIT = "admin/classificationMgr/edit";
	    public static final String PAGE_DATE = "pageData";
	    private IClassificationService classificationService;

		public void setClassificationService(IClassificationService classificationService) {
			this.classificationService = classificationService;
		}

		@RequestMapping("/list.do")
	    public ModelAndView list(@RequestParam(value = "pageIndex", required = false) final String pageIndex) {
	        try {
	            int index = 0;
	            if (!LangUtil.isNullOrEmpty(pageIndex)) {
	                index = Integer.parseInt(pageIndex);
	            }
	            final Pager<ClassificationEditModel> classifications = this.classificationService.findAll(index);
	            return new ModelAndView(ADMIN_CLASSIFICATION_MGR_LIST, PAGE_DATE, classifications);

	        } catch (DataAccessException e) {
	            return new ModelAndView("error");
	        }
	    }

	    @RequestMapping("/create.do")
	    public ModelAndView create() {
	        final Map<String, Object> map = new HashMap<String, Object>();
	        map.put("classification", new ClassificationEditModel("添加书籍类别", new Classification()));
	        return new ModelAndView(ADMIN_CLASSIFICATION_MGR_EDIT, map);
	    }

	    @RequestMapping("/{id}/edit.do")
	    public ModelAndView edit(@PathVariable("id") final String id) {
	        try {
	            final ClassificationEditModel classification = this.classificationService.findById(id);
	            final Map<String, Object> map = new HashMap<String, Object>();
	            map.put("classification", classification);
	            return new ModelAndView(ADMIN_CLASSIFICATION_MGR_EDIT, map);

	        } catch (DataAccessException e) {
	            return new ModelAndView("error");
	        }

	    }

	    @RequestMapping(value = "/save.do", method = RequestMethod.POST)
	    public ModelAndView save(@Valid @ModelAttribute("classification") final ClassificationEditModel classificationEditModel, final BindingResult bindingResult) throws DataAccessException {
	        if (bindingResult.hasErrors()) {
	            final Map<String, Object> map = new HashMap<String, Object>();
	            classificationEditModel.setEditType("编辑类别");
	            map.put("classification", classificationEditModel);
	            return new ModelAndView(ADMIN_CLASSIFICATION_MGR_EDIT, map);
	        }
	        classificationEditModel.setCreateDate();
	        classificationService.save(classificationEditModel.innerClassificationEntity());
	        return new ModelAndView("forward:/success.jsp", "message", "保存类别成功！");

	    }

	    @RequestMapping("/{id}/delete.do")
	    public ModelAndView delete(@PathVariable("id") final String id) {
	        String result = "删除类别失败！";
	        if (classificationService.delete(id)) {
	            result = "删除类别成功！";
	        }
	        return new ModelAndView("forward:/success.jsp", "message", result);
	    }

}
