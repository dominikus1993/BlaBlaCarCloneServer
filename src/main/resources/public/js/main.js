/**
 * Created by domin_000 on 09.01.2016.
 */
const tokenKey = "userToken";
const accountKey = "accountData";

const loginUrl = (login, password) => `/user/login/${login}/password/${password}`;

class Person{
    constructor(data){
        this.firstName = ko.observable(data.firstName);
        this.lastName = ko.observable(data.lastName);
        this.email = ko.observable(data.email);
    }
}

class Ride{

}

class UserViewModel{
    constructor(){
        this.login = ko.observable(sessionStorage.getItem(accountKey));
        this.password = ko.observable("");
        this.isNotLogged = ko.observable(!this.checkCredentials());
        this.isLogged = ko.observable(this.checkCredentials());
        this.bind();
    }

    loginUser(){
        $.getJSON(loginUrl(this.login(), this.password()), (data) => {
            console.log(data);
            if(data.isSuccess){
                sessionStorage.setItem(tokenKey, data.value);
                sessionStorage.setItem(accountKey, this.login());
                this.isNotLogged(!this.checkCredentials());
                this.isLogged(this.checkCredentials());
            }
        });
    }

    registerUser(){

    }

    logout(){
        sessionStorage.clear(accountKey);
        sessionStorage.clear(tokenKey);
        this.isNotLogged(!this.checkCredentials());
        this.isLogged(this.checkCredentials());
    }

    checkCredentials(){
        return (sessionStorage.getItem(accountKey) != null && sessionStorage.getItem(tokenKey) != null);
    }

    bind(){
        console.log("bind");
        ko.applyBindings(this, document.getElementById("loginPanel"))
    }
}

const main = (function(){
    var userViewModel = new UserViewModel();
})();
