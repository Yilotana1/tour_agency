package com.example.touragency.validation.user;

import com.example.touragency.model.entity.User;
import com.example.touragency.validation.user.exceptions.*;

public class UserValidator {

    public final static int PASSWORD_LENGTH = 8;



    private UserValidator(){}

    public static UserValidator createUserValidator(){
        return new UserValidator();
    }


    public void checkEmailIsValid(String email) throws InvalidEmailException {
         if (email.matches("^(.+)@(.+)$")) return;

         throw new InvalidEmailException("Please enter email like this: example1@gmail.com", email);
    }

    public void checkPasswordIsValid(String password) throws InvalidPasswordException {
         if(password.length() >= PASSWORD_LENGTH) return;

         throw new InvalidPasswordException("Your password should be more or equals " + PASSWORD_LENGTH + " symbols", password);
    }

    public void checkFirstNameIsValid(String firstName) throws InvalidFirstNameException {
        if (firstName.matches("^[\\p{IsCyrillic}A-Za-z]+$")) return;

        throw new InvalidFirstNameException("Your firstname should consist only latin or cyrillic characters", firstName);
    }

    public void checkLastNameIsValid(String lastName) throws InvalidLastNameException {
        if (lastName.matches("^[\\p{IsCyrillic}A-Za-z]+$")) return;

        throw new InvalidLastNameException("Your firstname should consist only latin or cyrillic characters", lastName);
    }

    public void checkPhoneNumberIsValid(String phone) throws InvalidPhoneException {
        if (phone.matches("^\\+\\d+$")) return;

        throw new InvalidPhoneException("Your phone number should start with + and consist only digits", phone);
    }

    public void checkLoginIsValid(String login) throws InvalidLoginException {
        if (login.matches("[A-Za-z\\d_]+")) return;

        throw new InvalidLoginException("Your login should consist only latin characters or digits", login);
    }

    public void checkUserIsValid(User user) throws InvalidDataException {
        checkEmailIsValid(user.getEmail());
        checkPasswordIsValid(user.getPassword());
        checkFirstNameIsValid(user.getFirstname());
        checkLastNameIsValid(user.getLastname());
        checkPhoneNumberIsValid(user.getPhone());
        checkLoginIsValid(user.getLogin());
    }

}
