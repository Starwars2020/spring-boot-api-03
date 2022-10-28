# curl test script
curl -i -X GET "http://rest-api-03.info/product/1"

curl -i -X POST "http://rest-api-03.info/product" -d 'name=Model Y' --data 'price=1500' --data 'stock=300'

curl -i -X PUT "http://rest-api-03.info/product" -d 'number=1' --data 'stock=500'

curl -i -X DELETE "http://rest-api-03.info/product" -d 'number=2'