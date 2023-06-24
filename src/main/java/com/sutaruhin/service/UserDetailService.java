package com.sutaruhin.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sutaruhin.entity.Authentication;
import com.sutaruhin.repository.AuthenticationRepository;


//UserDetailService クラスは、認証処理に使用するユーザ情報を作成します。
@Service
public class UserDetailService implements UserDetailsService {
	@Autowired
	private AuthenticationRepository authenticationRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Authentication> authentication = authenticationRepository.findById(username);

		if (!authentication.isPresent()) {
			throw new UsernameNotFoundException("Excetpion:Username Not Found");
		}
		return new UserDetail(authentication.get().getUser());
	}

}

/*loadUserByUsername メソッドで、username をキーに AuthenticationRepository リポジトリから認証エンティティを検索します。
 * 対象の認証エンティティが存在しない場合はUsernameNotFoundException をthrowします。
 * 存在する場合は、認証エンティティに含まれるUserエンティティをもとに、UserDetailオブジェクトを作成して（Spring Securityに）返します。*/

