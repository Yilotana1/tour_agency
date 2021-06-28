package com.example.touragency.validation.user;

import com.example.touragency.constants.Messages;
import com.example.touragency.model.entity.User;
import com.example.touragency.validation.InvalidDataException;
import com.example.touragency.validation.user.exceptions.*;

public class UserValidator {

    public final static int PASSWORD_LENGTH = 8;



    private UserValidator(){}

    public static UserValidator createUserValidator(){
        return new UserValidator();
    }


    public void checkEmailIsValid(String email) throws InvalidEmailException {
         if (email.matches("^(.+)@(.+)$")) return;

         throw new InvalidEmailException(Messages.ENTER_EMAIL_LIKE_THIS, email);
    }

    public void checkPasswordIsValid(String password) throws InvalidPasswordException {
         if(password.length() >= PASSWORD_LENGTH) return;

         throw new InvalidPasswordException(Messages.PASSWORD_MUST_BE_MORE, password);
    }

    public void checkFirstNameIsValid(String firstName) throws InvalidFirstNameException {
        if (firstName.matches("^[\\p{IsCyrillic}A-Za-z]+$")) return;

        throw new InvalidFirstNameException(Messages.FIRST_NAME_SHOULD_ONLY_CONSIST, firstName);
    }

    public void checkLastNameIsValid(String lastName) throws InvalidLastNameException {
        if (lastName.matches("^[\\p{IsCyrillic}A-Za-z]+$")) return;

        throw new InvalidLastNameException(Messages.LAST_NAME_SHOULD_ONLY_CONSIST, lastName);
    }

    public void checkPhoneNumberIsValid(String phone) throws InvalidPhoneException {
        if (phone.matches("^\\+\\d+$")) return;

        throw new InvalidPhoneException(Messages.PHONE_NUMBER_SHOULD_CONSIST, phone);
    }

    public void checkLoginIsValid(String login) throws InvalidLoginException {
        if (login.matches("[A-Za-z\\d_]+")) return;

        throw new InvalidLoginException(Messages.LOGIN_SHOULD_CONSIST, login);
    }

    public void checkUserIsValid(User user) throws InvalidDataException {
        checkEmailIsValid(user.getEmail());
        checkPasswordIsValid(user.getPassword());
        checkFirstNameIsValid(user.getFirstname());
        checkLastNameIsValid(user.getLastname());
        checkPhoneNumberIsValid(user.getPhone());
        checkLoginIsValid(user.getLogin());
    }

    public void checkUserIsValid(String firstName, String lastName, String phone, String email, String login,
                                 String password) throws InvalidDataException {
        checkFirstNameIsValid(firstName);
        checkLastNameIsValid(lastName);
        checkPhoneNumberIsValid(phone);
        checkEmailIsValid(email);
        checkLoginIsValid(login);
        checkPasswordIsValid(password);
    }

}
