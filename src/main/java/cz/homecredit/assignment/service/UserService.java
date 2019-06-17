package cz.homecredit.assignment.service;

import cz.homecredit.assignment.model.User;

public interface UserService {

   /**
    * Returns user's personal details and to-do
    * @param userId identification of the user
    * @return User
    */
   User getUsersTodos(long userId);
}
