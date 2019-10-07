show dbs;
use testdb;
db.dropDatabase();

show collections; 

db.createCollection("posts")


db.posts.insert({
    "title": "Post 1"
})

db.posts.insertMany(
    {
        "title": "Post 1"
    },{
        "title": "Post 1"
    },{
        "title": "Post 1"
    },{
        "title": "Post 1",
        date: Date()
    },
)
