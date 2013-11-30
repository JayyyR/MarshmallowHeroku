import requests
import datetime

#kill
hs = {'id': "jracosta"}
print ("\n...voting Joe")
getNear = requests.post('http://jayyyyrwerewolf.herokuapp.com/players/setAdmin', params = hs)
