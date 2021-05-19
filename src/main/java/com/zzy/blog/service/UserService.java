package com.zzy.blog.service;

import com.zzy.blog.po.User;

public interface UserService {
    User checkUser(String username, String password);
}
