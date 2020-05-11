package com.xducs.controller;

import com.xducs.domain.Product;
import com.xducs.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * @author Linbo Ge
 * @date 2020/5/3 - 11:21
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    /**
     * 查询所有商品
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll")
    @RolesAllowed("ADMIN")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> ps = productService.findAll();
        mv.addObject("productList", ps);
        mv.setViewName("product-list1");
        return mv;
    }

    /**
     * 添加产品
     *
     * @param product
     */
    @RequestMapping("/save")
    public String save(Product product) throws Exception {
        productService.save(product);
        return "redirect:findAll";
    }
}
