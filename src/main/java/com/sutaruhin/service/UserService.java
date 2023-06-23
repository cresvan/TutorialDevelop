package com.sutaruhin.service;

import java.util.List;
import java.util.Set;

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


	/*deleteUser メソッドでは、引数として渡された主キーのSet idck（画面で選択した削除対象UserのIDすべて）をfor文で1件ずつ取り出し、
	 * JpaRepositoryインターフェイスの定義済みメソッドdeleteByIdで削除を行なっています。*/
	@Transactional
	public void deleteUser(Set<Integer> idck) {
		for(Integer id : idck) {
			userRepository.deleteById(id);
		}
	}

	/*今回は、後で別のテーブルと結合して処理を行うため、for文で1件ずつ削除する方法にしていますが、
	 * JpaRepository インターフェイスの定義済みメソッド deleteAllInBatch を使うと一括削除も可能です。その場合の記述内容は以下になります。

	public void deleteUser(Set idck) {
	    userRepository.deleteAllInBatch(userRepository.findAllById(idck));
	}	*/


}
