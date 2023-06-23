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


	/*saveUser メソッドでは、 JpaRepository インターフェイスの定義済みメソッド save を呼び出しています。
	 * save メソッドは、引数で渡したエンティティインスタンスのデータをテーブルに保存します。 */
	@Transactional
	public User saveUser(User user) {
		return userRepository.save(user);
	}


}
