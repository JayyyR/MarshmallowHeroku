import requests
import datetime

#kill
hs = {'score': 1234}
print ("\n...voting Joe")
getNear = requests.post('http://jayyyrmarshmallow.herokuapp.com/insertscore', params = hs)
print getNear