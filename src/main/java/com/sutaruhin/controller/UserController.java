package com.sutaruhin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sutaruhin.entity.User;
import com.sutaruhin.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService service;


	@GetMapping("/list")
	public String getList(Model model) {

		model.addAttribute("userlist", service.getUserList());

		return "user/list";
	}


	/*@ModelAttributeアノテーションを付与すると、自動的に（テンプレートにデータを受け渡す）Modelにインスタンスが登録されます。
	 * これは@ModelAttributeアノテーションを付与せずに model.addAttribute("user", user); と記述することと同一です。
	 * テンプレート側では user を使い <input type="text" th:field="${user.name}> のように記述できます。
	 * 実際に生成されるHTMLでは th:field="${user.name} の部分が id="name" name="name" のように変換されます。*/
	@GetMapping("/register")
	public String getRegister(@ModelAttribute User user) {

		return "user/register";
	}


	//POST側では引数にエンティティを指定することで、HTMLのFormの項目値が、引数のuserの属性としてセットされた状態で受け取ることができます。
	@PostMapping("register")
	public String postRegister(User user) {

		service.saveUser(user);

		return "redirect:/user/list";
	}


	//@PathVariable("id") Integer id でパスパラメータからidを取得しています。そのidを使用し、サービスの getUser(id) 関数で更新対象のUserを取得し、Modelに登録しています。
	@GetMapping("/update/{id}")
	public String getUser(@PathVariable("id") Integer id, Model model) {

		model.addAttribute("user", service.getUser(id));

		return "user/update";
	}


	@PostMapping("/update/{id}")
	public String postUser(User user) {

		service.saveUser(user);

		return "redirect:/user/list";
	}

}
