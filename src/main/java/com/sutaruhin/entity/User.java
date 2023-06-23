package com.sutaruhin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data	//Lombok Annotation - 「getter/setter、toString、hashCode、equals」のメソッドを生成
@Entity					//Spring JPA Annotation - エンティティクラス（データベースのテーブルにマッピングするクラス）であることを示します
@Table(name = "user")	//Spring JPA Annotation - エンティティが対応する（紐づく）テーブル名を指定します。MySQLの user テーブルに対応しています
public class User {

	public static enum Gender {
		男性, 女性
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 20, nullable = false)		//Spring JPAのアノテーション、フィールドをマッピングするテーブルのカラム（項目）を指定
	private String name;

	@Column(length = 2)
	@Enumerated(EnumType.STRING)	//Spring JPAのアノテーション、このフィールドの型が列挙型であることを指定。 EnumType.STRING 属性でテーブルのカラムを文字列型に指定
	private Gender gender;

	private Integer age;

	@Column(length = 50)
	private String email;

}
