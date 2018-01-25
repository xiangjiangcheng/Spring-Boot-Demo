package com.xiang.demo.service.impl;

import com.xiang.demo.dao.UserRepository;
import com.xiang.demo.entity.User;
import com.xiang.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getOne(String id) {
        return userRepository.findOne(id);
    }

    @Override
    public Page<User> getAll(Integer page, Integer size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.DESC, "createDate");
        Page<User> userList = userRepository.findAll(pageable);
        return userList;
    }

    @Override
    public User save(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public User Update(User user) {
        // User oldUser = userRepository.findByUserIdAndIsDelete(user.getUserId(), IsDeleteEnums.NOTDELETE.getValue());
        // if (oldUser == null) {
        //     throw new BusinessException(ErrorsEnums.SHOP_NOT_EXIST.getCode(), ErrorsEnums.SHOP_NOT_EXIST.getMessage());
        // }
        // BeanUtils.copyPropertiesIgnoreNullValue(user, oldUser);
        // userRepository.save(oldUser);
        User oldUser = null;
        return oldUser;
    }

    @Override
    public Boolean delete(String id) {
        //根据id获取数据
        User user = userRepository.findOne(id);
        if (user == null) {
            return false;
        } else {
            //逻辑删除
            // user.setIsDelete(IsDeleteEnums.DELETE.getValue());
            userRepository.save(user);
        }
        return true;
    }
}
