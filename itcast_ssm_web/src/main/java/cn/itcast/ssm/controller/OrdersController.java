package cn.itcast.ssm.controller;

import cn.itcast.ssm.domain.Orders;
import cn.itcast.ssm.service.OrdersService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


/**
 * presentation layer:product_controller
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    /**
     * search all orders-Paginated
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")int page,
                                @RequestParam(name = "size",required = true,defaultValue = "5")int size) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Orders> orders = ordersService.findAll(page,size);
        //pageinfo->pagebean
        PageInfo pageInfo = new PageInfo(orders);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-list");
        return mv;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true)int id) throws Exception {
        ModelAndView mv = new ModelAndView();
        Orders orders = ordersService.findById(id);
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");
        return mv;
    }


}
