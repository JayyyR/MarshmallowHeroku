import requests
import datetime

#kill
hs = {'id': 'jayr'}
print ("\n...voting Joe")
getNear = requests.get('http://jayyyyrwerewolf.herokuapp.com/players/hasVoted', params = hs)

print getNear.text
