package org.cmy.community.controller;

import org.apache.commons.lang3.StringUtils;
import org.cmy.community.cache.TagCache;
import org.cmy.community.dto.QuestionDTO;
import org.cmy.community.dto.TagDTO;
import org.cmy.community.mapper.QuestionMapper;
import org.cmy.community.model.Question;
import org.cmy.community.model.User;
import org.cmy.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private QuestionService questionService;

    /**
     * 编辑问题
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id")Long id,
                       Model model){
        QuestionDTO question = questionService.getById(id);
        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        model.addAttribute("id", question.getId());
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }

    //post执行请求 get渲染页面

    /**
     * 第一次进入 发布页面
     * @param model
     * @return
     */
    @GetMapping("/publish")
    public String publish(Model model){
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }

    /**
     * 发布问题
     * @param title
     * @param description
     * @param tag
     * @param id
     * @param request
     * @param model
     * @return
     */
    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title",required = false) String title,
            @RequestParam(value = "description",required = false) String description,
            @RequestParam(value = "tag", required = false) String tag,
            @RequestParam(value = "id",required = false) Long id,
            HttpServletRequest request,
            Model model){

        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        model.addAttribute("title", title);
        model.addAttribute("tags", TagCache.get());
        //下面这三个if语句实际上前后端都需要做，不过我把重点放在后端
        if(title == null || title.equals("")){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(description == null || description.equals("")){
            model.addAttribute("error","内容不能为空");
            return "publish";
        }
        if(tag == null || tag.equals("")){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }

        String invalid = TagCache.filterInvalid(tag);
        if(StringUtils.isNotBlank(invalid)){
            model.addAttribute("error","输入非法标签："+invalid);
            return "publish";
        }
        User user = (User) request.getSession().getAttribute("user");

        if (user == null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setId(id);
        questionService.createOrUpdate(question);
        return "redirect:/";
    }
}
