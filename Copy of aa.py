import requests
import datetime

#kill
hs = {'id': 'poopy'}
print ("\n...killing Joe")
getNear = requests.post('http://jayyyyrwerewolf.herokuapp.com/players/setAdmin', params = hs)

