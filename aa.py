import requests
import datetime

date = datetime.date.today()

#Create a game
game = {'dayNightFreq': 1, 'createdDate': date.strftime("%Y-%m-%d %H:%M:%S")}

print "...Adding game to database\n"
gameCreate = requests.post('http://jayyyyrwerewolf.herokuapp.com/games/create', params=game);

#Create Users
User1 = {'firstName':"Joe", 'lastName':"Acosta", 'id':'JayyyyyyyyyR', 
           'hashedPassword':'234', 'imageURL':'idk', 'email':'flatpita92@gmail.com'}
User2 = {'firstName':"Bill", 'lastName':"O Reilly", 'id':'foxnewsrox', 
           'hashedPassword':'welldoitlive', 'imageURL':'idk', 'email':'brise@gmail.com'}
User3 = {'firstName':"Peter", 'lastName':"Parker", 'id':'iamspiderman', 
           'hashedPassword':'maryjanestacy', 'imageURL':'idk', 'email':'woeisme@gmail.com'}
User4 = {'firstName':"Jerry", 'lastName':"Seinfeld", 'id':'whatsthedealwithtisgame', 
           'hashedPassword':'mulva', 'imageURL':'idk', 'email':'129west81st@gmail.com'}
Users = [User1,User2,User3,User4]
 
print "...Adding 4 Users to Database\n"
#add users to databse
for user in Users:
    addUser = requests.post('http://jayyyyrwerewolf.herokuapp.com/users/create', params=user);
      

print("...printing our users\n")
# get all users to make sure they were added
getUser = requests.get('http://jayyyyrwerewolf.herokuapp.com/users/getAll')
print getUser.text

#update an email
spiderEmail = {'id':'iamspiderman', 'email':'gr8pwrgr8rsponsblty@benparker.com'}
print("\n...updating spiderman's email to gr8pwrgr8rsponsblty@benparker.com")
updateEmail = requests.post('http://jayyyyrwerewolf.herokuapp.com/users/updateEmail', params=spiderEmail)

#verify change
print("\nverify spider-man's email changed:")
getUser = requests.get('http://jayyyyrwerewolf.herokuapp.com/users/getAll')
print getUser.text

#change it back
print("\n...changing his email back")
spiderEmail = {'id':'iamspiderman', 'email':'woeisme@gmail.com'}
updateEmail = requests.post('http://jayyyyrwerewolf.herokuapp.com/users/updateEmail', params=spiderEmail)


print("\n...Adding players")
#Add players
player1 = {'id':"Joe", 'isDead':False, 'lat':2.0, 
           'lng':40.0, 'userId':'JayyyyyyyyyR', 'isWerewolf':False}
player2 = {'id':"Bill", 'isDead':False, 'lat':8.0, 
           'lng':11.0, 'userId':'foxnewsrox', 'isWerewolf':False}
player3 = {'id':"Spider-Man", 'isDead':False, 'lat':3.0, 
           'lng':11.0, 'userId':'iamspiderman', 'isWerewolf':False}
player4 = {'id':"Jerry", 'isDead':False, 'lat':1.0, 
           'lng':23.0, 'userId':'whatsthedealwithtisgame', 'isWerewolf':False}
players = [player1,player2,player3,player4]

for player in players:
    addUser = requests.post('http://jayyyyrwerewolf.herokuapp.com/players/insert', params=player);
    
print ("\n..getting all alive players")
# get all players to make sure they were added
getPlayers = requests.get('http://jayyyyrwerewolf.herokuapp.com/players/alive')
print getPlayers.text

#get player by id
print ("\n just get Joe by his id: Joe")
getJoe = requests.get('http://jayyyyrwerewolf.herokuapp.com/players/Joe')
print getJoe.text

#get players nearby
Joe = {'id': 'Joe'}
print ("\n get players nearby Joe (lat and long is within 10")
getNear = requests.get('http://jayyyyrwerewolf.herokuapp.com/players/nearby', params = Joe)
print getNear.text

#kill
print ("\n...killing Joe")
getNear = requests.get('http://jayyyyrwerewolf.herokuapp.com/players/kill', params = Joe)

print ("\nverify Joe is dead:")
getJoe = requests.get('http://jayyyyrwerewolf.herokuapp.com/players/Joe')
print getJoe.text

#vote
Jerry = {'id':'Jerry'}
print ("\n...placing vote on Jerry")
voteJerry = requests.get('http://jayyyyrwerewolf.herokuapp.com/players/vote', params = Jerry)

print ("\nverify Jerry is voted on:")
getJer = requests.get('http://jayyyyrwerewolf.herokuapp.com/players/Jerry')
print getJer.text
