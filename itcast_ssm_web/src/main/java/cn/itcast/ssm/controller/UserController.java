package cn.itcast.ssm.controller;

import cn.itcast.ssm.domain.Role;
import cn.itcast.ssm.domain.UserInfo;
import cn.itcast.ssm.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * presentation layer:user_controller
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * search all user
     * @return
     */
    @RequestMapping("/findAll.do")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,
                                @RequestParam(name = "size",required = true,defaultValue = "5") Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> users =  userService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(users);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("user-list");
        return mv;
    }

    /**
     * save user
     * @param userInfo
     * @throws Exception
     */
    @RequestMapping("/save.do")
    public String save(UserInfo userInfo) throws Exception{
        userService.save(userInfo);
        return "redirect:findAll.do?page=1&size=5";
    }

    /**
     * search user by id
     * @param userId
     * @return
     * @throws Exception
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true)Integer userId) throws Exception{
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(userId);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;
    }

    /**
     * search available roles by userId
     * @param userId
     * @return
     * @throws Exception
     */
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true)Integer userId) throws Exception{
        ModelAndView mv = new ModelAndView();
        // search user by id
        UserInfo userInfo = userService.findById(userId);
        //search available roles by userid
        List<Role> otherRoles = userService.findOtherRoles(userId);
        mv.addObject("user",userInfo);
        mv.addObject("roleList",otherRoles);
        mv.setViewName("user-role-add");
        return mv;
    }

    /**
     * add role to user
     * @param userId
     * @param roleIds
     * @return
     */
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId",required = true) Integer userId,@RequestParam(name = "ids",required = true) Integer[] roleIds) throws Exception {
        userService.addRoleToUser(userId,roleIds);
        return "redirect:findAll.do?page=1&size=5";
    }

    /**
     * delete user
     * @param userId
     * @return
     * @throws Exception
     */
    @RequestMapping("/delete.do")
    public String delete(@RequestParam(name = "id",required = true)Integer userId) throws Exception{
        userService.delete(userId);
        return "redirect:findAll.do";
    }

    /**
     * update form
     * @param userId
     * @return
     * @throws Exception
     */
    @RequestMapping("/updateById.do")
    public ModelAndView updateById(@RequestParam(name = "id",required = true)Integer userId) throws Exception{
        ModelAndView mv = new ModelAndView();
        UserInfo user = userService.findById(userId);
        mv.addObject("user",user);
        mv.setViewName("user-update");
        return mv;
    }

    /**
     * update user
     * @param userInfo
     * @return
     * @throws Exception
     */
    @RequestMapping("/update.do")
    public String update(UserInfo userInfo) throws Exception{
        userService.update(userInfo);
        return "redirect:findAll.do";
    }

    @RequestMapping("/batchDelete.do")
    public String batchDelete(String itemList) throws Exception{
        String[] items = itemList.split(",");
        List<Integer> ids = new ArrayList<>();
        for(String item:items){
            ids.add(Integer.parseInt(item));
        }
        userService.batchDelete(ids);
        return "redirect:findAll.do";
    }
}
