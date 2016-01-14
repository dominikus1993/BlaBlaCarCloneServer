/**
 * Created by domin_000 on 09.01.2016.
 */
const tokenKey = "userToken";
const accountKey = "accountData";

const loginUrl = (login, password) => `/user/login/${login}/password/${password}`;
const getAllRidesUrl = "/rides/all";
const joinUrl = (rideId) => `/rides/ride/${rideId}/join`;


const getTokenValue = () => sessionStorage.getItem(tokenKey);
const getAccountKey = () => sessionStorage.getItem(accountKey);

class Person{
    constructor(data){
        this.id = 0;
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.rides = [];
        ko.mapping.fromJS(data, {}, this);
    }
}

class Ride{
    constructor(data){
        this.id = 0;
        this.owner = "";
        this.from = "";
        this.to = "";
        this.price = 0.0;
        this.date = new Date();
        this.amountOfSeats = 0;
        this.persons = [];

        ko.mapping.fromJS(data, {}, this);

        this.isNotFull = ko.computed(() => {
            return this.persons.length !== this.amountOfSeats;
        });

        this.userJoined = ko.computed(() => {
            return this.persons.filter(x => x.email() == getAccountKey()).length === 0;
        })
    }
}

class RideToAdd{
   constructor(){
       this.from = ko.observable("");
       this.to = ko.observable("");
       this.price = ko.observable( 0.0);
       this.date = ko.observable(new Date());
       this.amountOfSeats = ko.observable(0);
   }
}

class RideViewModel{

    constructor(){
        this.rideToAdd = ko.observable(new RideToAdd());
        this.rides = ko.observable([]);

        this.isLogged = ko.computed(() => {
            return getTokenValue() != null
        });

        this.getAll();
        this.bind();
    }

    getAll(){
        $.getJSON(getAllRidesUrl, (data) => {
            if(data.isSuccess){
                const rides = _.map(data.value, (ride) => new Ride(ride));
                console.log(rides);
                this.rides(rides);
                console.log(this.rides())
            }
        });
    }

    join(rideId){

        $.ajax({
            url: joinUrl(rideId),
            type:"GET",
            contentType: "application/json;charset=utf-8",
            headers: { "Authorization": getTokenValue() },
            success : (data) => {
                console.log(data)
                if(data.isSuccess){
                    console.log("siema");
                    this.rides(_.map(this.rides(), (ride) => {
                        console.log(`${ride} <-> ${data}`);
                        if(ride.id == data.value.id){
                            return new Ride(data.value);
                        }
                        else{
                            return ride;
                        }
                    }));
                }
            }
        });
    }

    bind(){
        console.log("rides bind");
        ko.applyBindings(this, document.getElementById("rides"))
    }
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
    var rideViewModel = new RideViewModel();
})();
