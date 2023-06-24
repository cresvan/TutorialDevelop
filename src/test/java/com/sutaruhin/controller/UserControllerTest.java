package com.sutaruhin.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.sutaruhin.entity.User;


/*MockMvc はHTTPリクエストを擬似的に再現するクラスです。コントローラーのテストで使用します。@AutoConfigureMockMvc は MockMvc の設定を行なうアノテーションです。*/
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	//@BeforeEach アノテーションを付けると、各テストの前に、この処理が実行されます。
	@BeforeEach
	void beforeEach() {

		mockMvc = MockMvcBuilders
				.webAppContextSetup(webApplicationContext)
				.apply(springSecurity()).build();
	}

	@Test
	@DisplayName("ユーザ編集画面")		//@DisplayName でJUnitビューに表示されるテスト名を設定できます。
	@WithMockUser
	void testGetUser() throws Exception {
		MvcResult result = mockMvc.perform(get("/user/update/1/"))	// mockMvcを使い、getメソッドでURLにアクセスします。
				.andExpect(status().isOk())							//レスポンスのステータスが「200 OK」であることを確認します。
				.andExpect(model().attributeExists("user"))			//viewに渡しているModelにuserが登録されていることを確認します。
				.andExpect(model().hasNoErrors())					//Modelにエラーが無いことを確認します。
				.andExpect(view().name("user/update"))				//viewの名称を確認します。
				.andReturn();										//内容を取得します。内容は result 変数に格納されます。


		//「userの検証」では、Modelから user を取り出して値の検証を行なっています。
		User user = (User)result.getModelAndView().getModel().get("user");

		assertEquals(user.getId(), 1);
		assertEquals(user.getName(), "スタルヒン太郎");
	}

}

/*テストではモックを多く使います。モックは「原寸大の模型」という意味で、機能やクラスを擬似的に再現したものです。
 * たとえば UserService の単体テストを行う場合、サービスの内部で使用している UserRepository は、テストコード上で「常に正しいレスポンスを返すモック」に置き換えます。*/
