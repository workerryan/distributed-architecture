package com.dragon.service;

import java.util.ArrayList;
import java.util.List;

import com.dragon.entity.Permission;
import com.dragon.entity.User;
import com.dragon.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 设置动态用户信息，查询用户的权限信息
 */
@Slf4j
@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private UserMapper userMapper;


	/**
	 * 用户登录的时候会调用该方法
	 * @param username  用户名
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// 1.根据用户名称查询数据用户信息
		User user = userMapper.findByUsername(username);
		// 2.底层会根据数据库查询用户信息，判断密码是否正确，不需要手动代码去验证密码是否正确

		// 3. 给用户设置权限
		List<Permission> listPermission = userMapper.findPermissionByUsername(username);
		if (listPermission != null && listPermission.size() > 0) {
			log.info("username:" + username + ",对应权限:" + listPermission.toString());
			// 定义用户权限
			List<GrantedAuthority> authorities = new ArrayList<>(listPermission.size());
			for (Permission permission : listPermission) {
				authorities.add(new SimpleGrantedAuthority(permission.getPermTag()));
			}
			user.setAuthorities(authorities);
		}
		return user;
	}

}
