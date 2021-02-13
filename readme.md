# Xmeme

 Xmeme is a simple web application where anyone can post 
 memes.

## Technology stack

    FrontEnd - HTML,CSS,JAVASCRIPT

    Backend - Spring-Boot, Java

    Database - MongoDB

## Backend overview

    Xmeme's backend has four APIs implemented in MVC
    (inside controller folder class XmemeController)

    1. findMeme which takes memeId as parameter
       and returns the meme which have that id.

    2. findAllMemes which takes no parameters
       and returns the all memes data present 
       in the database

    3. postMeme which takes meme data(name,url,caption)
       as parameter and save the data into database 
       followed by returning the newly created id.

    4. updateMeme which takes memeid and data(url,caption)
       as parameter and updates the url and caption in the 
       related memeid.

### Components which is used to implement the APIs
    
    MemeEntity is used to map json data in entities.
    
    MemeRepository is a interface which extends the 
    methods of MongoRepository so it will process all
    the mongoDB queries.

    service folder has all the classes and interfaces
    which is used to implement service class for all
    APIs.(includes all the core implementation of GET, POST
    and PATCH request).

## Publicly Deployed Link for this project

    https://satyamxmeme.herokuapp.com
    
    End-Point = /memes


### For curl commands


    1. For Post request


    curl --location --request POST 'https://satyamxmeme.herokuapp.com/memes' \

    --header 'Content-Type: application/json' \

    --data-raw '{

    "name": "ashok kumar",

    "url": "https://images.pexels.com/photos/3573382/pexels-photo-3573382.jpeg",

    "caption": "This is a meme"

    }'


    2. For Get request

    curl --location --request GET 'https://satyamxmeme.herokuapp.com/memes'
    
    curl --location --request GET 'https://satyamxmeme.herokuapp.com/memes/<id>'


    3. For Patch request


    curl --location --request PATCH 'https://satyamxmeme.herokuapp.com/memes/<id>'

    --header 'Content-Type: application/json' \

    --data-raw '{

    "url": "new_url",

    "caption": "new_caption"

    }'
