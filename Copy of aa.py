import requests
import datetime

#kill
hs = {'id': 'tomk'}
print ("\n...voting Joe")
getNear = requests.post('http://jayyyyrwerewolf.herokuapp.com/players/setvoted', params = hs)

