package ua.nure.gudkov.ST4.converter;

import java.sql.Date;

import ua.nure.gudkov.ST4.bean.form.RegFormBean;
import ua.nure.gudkov.ST4.converter.api.Converter;
import ua.nure.gudkov.ST4.entity.Category;
import ua.nure.gudkov.ST4.entity.Role;
import ua.nure.gudkov.ST4.entity.User;
import ua.nure.gudkov.ST4.util.Password;

/**
 * Registration form bean to user entity converter.
 * 
 * @author A.Gudkov
 *
 */
public class RegFormBeanToUserConverter implements Converter<RegFormBean, User> {

	@Override
	public User convert(RegFormBean regFormBean) {
		User user = new User();
		if (Role.DOCTOR.equalsTo(regFormBean.getRole())) {
			user.setCategoryId(Category.valueOf(regFormBean.getCategory().toUpperCase()).ordinal() + 1);
		}
		user.setRoleId(Role.valueOf(regFormBean.getRole().toUpperCase()).ordinal());
		user.setDateOfBirth(Date.valueOf(regFormBean.getDateOfBirth()));
		user.setEmail(regFormBean.getEmail());
		user.setLastName(regFormBean.getLastName());
		user.setFirstName(regFormBean.getFirstName());
		user.setPassword(Password.createPassword(8));
		user.setPhone(regFormBean.getPhonNumber());
		return user;
	}

}
