package pl.edu.wszib.petShop.Services;


import pl.edu.wszib.petShop.Model.View.RegisterUser;

public interface IAuthenticationService {
    void authenticate(String login, String password);
    void register(RegisterUser registerUser);
}