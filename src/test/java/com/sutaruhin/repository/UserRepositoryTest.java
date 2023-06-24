package com.sutaruhin.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sutaruhin.entity.User;

/*@SpringBootTest アノテーションにより、Spring Bootアプリケーションの初期化が行われます。
 * これにより、 data.sql などが実行され、テストに必要な環境が準備されます。また、テストコードで @Autowired などのSpring Bootのアノテーションを使用できるようになります。*/
@SpringBootTest
class UserRepositoryTest {
	@Autowired
	UserRepository userRepository;

	@Test
	void testFindById() {
		User user = userRepository.findById(1).get();
		assertEquals(user.getId(), 1);
		assertEquals(user.getName(), "スタルヒン太郎");
	}

}
