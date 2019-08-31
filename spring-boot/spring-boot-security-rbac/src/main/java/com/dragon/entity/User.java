package com.dragon.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

/** 用户信息表
 *  SpringSecurity底层要依赖于UserDetails定义的一些终端做权限控制，
 *  UserDetails定义了用户表的一些终端。
 *  @author wanglei
 * */
@Data
public class User implements UserDetails {

	private Integer id;
	private String username;
	private String realname;
	private String password;
	private Date createDate;
	private Date lastLoginTime;

	private boolean enabled;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;

	/** 用户所有权限集合*/
	private List<GrantedAuthority> authorities = new ArrayList<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

}
