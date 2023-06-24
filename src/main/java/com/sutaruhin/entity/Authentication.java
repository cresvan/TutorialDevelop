package com.sutaruhin.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "authentication")
public class Authentication {

	@Id
	private String loginUser;


	private String password;


	private Date validDate;


	/*@OneToOne アノテーションは認証（Authentication）エンティティとユーザ（User）エンティティが1対1の関係であることを示します。
	 * @JoinColumn(name="user_id", referencedColumnName="id") は、リレーションを行なう項目を定義します。
	 * name が結合元（認証エンティティ）の項目名、 referencedColumnName が結合先（ユーザエンティティ）の項目名です。*/
	@OneToOne
	@JoinColumn(name="user_id", referencedColumnName="id")
	private User user;

}
