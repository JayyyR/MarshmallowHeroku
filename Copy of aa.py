import requests
import datetime

#kill
hs = {'id': 'poo'}
print ("\n...voting Joe")
getNear = requests.post('http://jayyyyrwerewolf.herokuapp.com/players/kill', params = hs)
