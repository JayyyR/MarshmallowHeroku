import requests
import datetime

#kill
hs = {'id': "jayr"}
print ("\n...voting Joe")
getNear = requests.post('http://jayyyyrwerewolf.herokuapp.com/players/killLastNight', params = hs)
