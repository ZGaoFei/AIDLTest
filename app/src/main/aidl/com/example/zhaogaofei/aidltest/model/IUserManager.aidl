package com.example.zhaogaofei.aidltest.model;

import com.example.zhaogaofei.aidltest.model.UserInfo;

interface IUserManager {

    void getNum(int num);

    void getUser(in UserInfo userInfo);
}
