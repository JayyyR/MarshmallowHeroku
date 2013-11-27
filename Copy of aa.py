import requests
import datetime

#kill
hs = {'news': "HI I'm paul"}
print ("\n...voting Joe")
getNear = requests.post('http://jayyyyrwerewolf.herokuapp.com/games/changenews', params = hs)
