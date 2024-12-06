package com.wealth.demo.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 獲取分類列表	GET	       /api/categories	返回用戶的所有分類
 * 新增分類	    POST	   /api/categories	用戶新增自定義分類
 * 刪除分類	    DELETE	   /api/categories/{id}	刪除分類
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class CategoryController {
}
