package com.sutaruhin.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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


	/*POST側では引数にエンティティを指定することで、HTMLのFormの項目値が、引数のuserの属性としてセットされた状態で受け取ることができます。
	 * @Validated アノテーションにより User エンティティの設定に基づいた入力チェックが行われます。入力チェックの結果は BindingResult res に格納されます。
	 * res.hasErrors() でエラーの有無を確認できます。エラーだった場合は getUser() メソッドを呼び出すことで、ユーザ編集画面を表示します。*/
	@PostMapping("/register")
	public String postRegister(@Validated User user, BindingResult res, Model model) {
		if(res.hasErrors()) {

			return getRegister(user);
		}

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


	/*deleteRunメソッドの引数に@RequestParam(name="idck") Set<Integer> idckの記述があります。
	 * @RequestParamアノテーションで、リクエストパラメータからidckの配列値を取得しています。取得したidckの値をサービスに渡して、対象のUserを削除します。*/
	@PostMapping(path="list", params="deleteRun")
	public String deleteRun(@RequestParam(name="idck") Set<Integer> idck, Model model) {

		service.deleteUser(idck);

		return "redirect:/user/list";
	}

}
