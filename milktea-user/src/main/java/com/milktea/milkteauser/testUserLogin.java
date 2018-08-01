package com.milktea.milkteauser;

import com.milktea.milkteauser.controller.UserInfoController;
import com.milktea.milkteauser.controller.UserLoginController;
import com.milktea.milkteauser.exception.MilkTeaException;

public class testUserLogin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserLoginController userLoginController = new UserLoginController();
		String code = "011A6x9K1DTnG40SOtdK1f6A9K1A6x9f";
		try {
			userLoginController.userInfoLogin(code);
		} catch (MilkTeaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
