/**
 * Created by domin_000 on 09.01.2016.
 */
const tokenKey = "userToken";
const accountKey = "accountData";

const loginUrl = (login, password) => `/user/login/${login}/password/${password}`;
const getAllRidesUrl = "/rides/all";
const joinUrl = (rideId) => `/rides/ride/${rideId}/join`;
const unJoinUrl = (rideId) => `/rides/ride/${rideId}/unJoin`;
const addRideUrl = "/rides/create";

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
       this.from = "";
       this.to = "";
       this.price = 0.0;
       this.date = new Date();
       this.amountOfSeats = 0;
   }
}

class RideViewModel{

    constructor(){
        this.rideToAdd = ko.observable(new RideToAdd());
        this.rides = ko.observableArray([]);
        this.isLogged = ko.computed(() => {
            return getTokenValue() != null
        });
        this.getAll();
        this.bind();
    }

    add(){
        console.log(this.rideToAdd());
        $.ajax({
            url:addRideUrl,
            type:"POST",
            contentType: "application/json;charset=utf-8",
            headers: { "Authorization": getTokenValue() },
            data: ko.mapping.toJSON(this.rideToAdd()),
            success: (result) => {
                const resultData = JSON.parse(result);
                if(resultData.isSuccess){
                    console.log("sukces")
                    this.rides.push(new Ride(resultData.value))
                    alert("Pomyślnie dodano trase");
                    this.rideToAdd(new RideToAdd());
                }
            }
        });
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
                const jsonData = JSON.parse(data);
                if(jsonData.isSuccess){
                    this.rides(_.map(this.rides(), (ride) => {
                        if(ride.id == jsonData.value.id){
                            return new Ride(jsonData.value);
                        }
                        else{
                            return ride;
                        }
                    }));
                }
            },
            error: (error) => {
                console.error(error);
            }
        });
    }

    unJoin(rideId){
        console.log("no przeciez przesuniety jestem");
        $.ajax({
            url: unJoinUrl(rideId),
            type:"GET",
            contentType: "application/json;charset=utf-8",
            headers: { "Authorization": getTokenValue() },
            success : (data) => {
                const jsonData = JSON.parse(data);
                if(jsonData.isSuccess){
                    this.rides(_.map(this.rides(), (ride) => {
                        if(ride.id == jsonData.value.id){
                            return new Ride(jsonData.value);
                        }
                        else{
                            return ride;
                        }
                    }));
                }
            },
            error: (error) => {
                console.error(error);
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
            if(data.isSuccess){
                sessionStorage.setItem(tokenKey, data.value);
                sessionStorage.setItem(accountKey, this.login());
                this.isNotLogged(!this.checkCredentials());
                this.isLogged(this.checkCredentials());
                window.location.reload();
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
        window.location.reload();
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
