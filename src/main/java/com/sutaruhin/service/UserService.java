package com.sutaruhin.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sutaruhin.entity.User;
import com.sutaruhin.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository ;


	public List<User> getUserList() {

		return userRepository.findAll();
	}


	/*更新するユーザ情報を取得するために getUser メソッドを追加しました。getUser メソッドでは、JpaRepository インターフェイスの定義済みメソッド findById を呼び出しています。
	 * findById メソッドは、引数で渡した主キーフィールドidの値を元にUserエンティティのインスタンスを取得します。*/
	public User getUser(Integer id) {
		return userRepository.findById(id).get();
	}


	/*saveUser メソッドでは、 JpaRepository インターフェイスの定義済みメソッド save を呼び出しています。
	 * save メソッドは、引数で渡したエンティティインスタンスのデータをテーブルに保存します。 */
	@Transactional
	public User saveUser(User user) {
		return userRepository.save(user);
	}


}
