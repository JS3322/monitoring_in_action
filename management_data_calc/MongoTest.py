from pymongo import MongoClient

# Connect to the default MongoDB instance running on localhost and default port 27017
client = MongoClient('localhost', 27017)

# Connect to the database named 'testdb'
db = client['test']

# Connect to a collection named 'testcollection'
collection = db['test']

# Find one document in the collection
doc = collection.find_one()
print(doc)

# Find all documents in the collection
for doc in collection.find():
    print(doc)

# Find documents with a specific criterion
# For example, find all documents where 'name' is 'Alice'
for doc in collection.find({'username': 'Bret'}):
    print(doc)

# Close the connection
client.close()
