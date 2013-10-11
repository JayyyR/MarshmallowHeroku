import requests

#Create Users
User1 = {'firstname':"Joe", 'lastName':"Acosta", 'id':'JayyyyyyyyyR', 
           'hashedPassword':'234', 'imageURl':'idk', 'email':'flatpita92@gmail.com'}

User2 = {'firstname':"Bill", 'lastName':"O Reilly", 'id':'foxnewsrox', 
           'hashedPassword':'welldoitlive', 'imageURl':'idk', 'email':'brise@gmail.com'}

#r = requests.post('http://localhost:8080/werewolf/players/insert', data=payload)

r2 = requests.post('http://jayyyyrwerewolf.herokuapp.com/users/create', params=payload);
