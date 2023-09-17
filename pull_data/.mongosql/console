//CREATE
db.createCollection("test100", {})

//READ
db.test100.countDocuments()

use test

function  getRandomInt(max) {
    return Math.floor(Math.random() * Math.random(max))
}

for (let i = 0; i < 50000; i++) {
    let doc = {
        name: "user" + getRandomInt(10000),
        age: getRandomInt(100)
    };
    db.test100.insertOne(doc);
}

print("All documents inserted");
