## About
Spring Boot REST API.
a multi-user web service for creating and solving quizzes.


## Register a user


To register a new user, the client needs to send a JSON with email and password via POST request to **/api/register**.

    {
      "email": "test@gmail.com",
      "password": "secret"
    }

The service returns *200* (OK) status code if the registration has been completed successfully.

If the email is already taken by another user, the service will return the *400* (Bad request) status code.

Here are some additional restrictions to the format of user credentials:

the email must have a valid format (with @ and .);
the password must have at least five characters.
If any of them is not satisfied, the service will also return the *400* (Bad request) status code.

All the following operations need a registered user to be successfully completed.


## Create a new quiz

To create a new quiz, the client needs to send a JSON as the request's body via POST to **/api/quizzes**. The JSON should contain the four fields:

**title**: a string, required;
**text**: a string, required;
**options**: an array of strings, required, should contain at least 2 items;
**answer**: an array of indexes of correct options, optional, since all options can be wrong.
Here is a new JSON quiz as an example:

    {
      "title": "Coffee drinks",
      "text": "Select only coffee drinks.",
      "options": ["Americano","Tea","Cappuccino","Sprite"],
      "answer": [0,2]
    }

The answer equals [0,2] corresponds to the first and the third item from the options array ("Americano" and "Cappuccino").






## Get a quiz by id

To get a quiz by id, the client sends the GET request to **/api/quizzes/{id}**.

The server response is a JSON with four fields: id, title, text and options. Here is an example:

    {
      "id": 1,
      "title": "Coffee drinks",
      "text": "Select only coffee drinks.",
      "options": ["Americano","Tea","Cappuccino","Sprite"]
    }


If the request JSON does not contain title or text, or they are empty strings (""), then the server responds with the *400* (Bad request) status code.
If the number of options in the quiz is less than 2, the server returns the same status code.




## Solving a quiz

To solve a quiz, the client sends the POST request to **/api/quizzes/{id}/solve** with a JSON that contains the indexes of all chosen options as the answer.
This looks like a regular JSON object with key "answer" and value as the array: {"answer": [0,2]}.

It is also possible to send an empty array [] since some quizzes may not have correct options.

The service returns a JSON with two fields: success (true or false) and feedback (just a string). There are three possible responses.

If the passed answer is correct:

    {"success":true,"feedback":"Congratulations, you're right!"}

If the answer is incorrect:

    {"success":false,"feedback":"Wrong answer! Please, try again."}

If the specified quiz does not exist, the server returns the *404* (Not found) status code.






## Delete a quiz

A user can delete their quiz by sending the DELETE request to **/api/quizzes/{id}.**

If the operation was successful, the service returns the *204* (No content) status code without any content.

If the specified quiz does not exist, the server returns *404* (Not found).
 If the specified user is not the author of this quiz, the response is the *403* (Forbidden) status code.





## Get all quizzes with paging 

To get all existing quizzes in the service, the client sends the GET request to **/api/quizzes** as before.

In this regard, your API should returns only 10 quizzes at once and supports the ability to specify which portion of quizzes is needed.

The response contains a JSON with quizzes (inside content) and some additional metadata:

    {
      "totalPages":1,
      "totalElements":3,
      "last":true,
      "first":true,
      "sort":{ },
      "number":0,
      "numberOfElements":3,
      "size":10,
      "empty":false,
      "pageable": { },
      "content":[
        {"id":102,"title":"Test 1","text":"Text 1","options":["a","b","c"]},
        {"id":103,"title":"Test 2","text":"Text 2","options":["a", "b", "c", "d"]},
        {"id":202,"title":"The Java Logo","text":"What is depicted on the Java logo?",
         "options":["Robot","Tea leaf","Cup of coffee","Bug"]}
      ]
    }


The API supports the navigation through pages by passing the page parameter ( `/api/quizzes?page=1`).
The first page is 0 since pages start from zero.
If there are no quizzes, content is empty []. If the user is authorized, the status code is *200* (OK); otherwise, it's *401* (Unauthorized).

## Get all completions of quizzes with paging

The service provides a new operation for getting all completions of quizzes for a specified user by sending the GET request to /api/quizzes/completed 
together with the user auth data. All the completions should are sorted from the most recent to the oldest.

A response is separated by pages since the service may return a lot of data. 
It contains a JSON with quizzes (inside content) and some additional metadata.

Here is a response example:

    {
      "totalPages":1,
      "totalElements":5,
      "last":true,
      "first":true,
      "empty":false,
      "content":[
        {"id":103,"completedAt":"2019-10-29T21:13:53.779542"},
        {"id":102,"completedAt":"2019-10-29T21:13:52.324993"},
        {"id":101,"completedAt":"2019-10-29T18:59:58.387267"},
        {"id":101,"completedAt":"2019-10-29T18:59:55.303268"},
        {"id":202,"completedAt":"2019-10-29T18:59:54.033801"}
      ]
    }

Since it is allowed to solve a quiz multiple times, the response may contain duplicate quizzes, but with the different completion date.


If there are no quizzes, content is empty []. If the user is authorized, the status code is *200 (OK)*; otherwise, it's *401 (Unauthorized).*


**Create a Quiz set**
---------------------------------------
To create a new quiz set, the client needs to send a JSON as the request's body via POST to  **/api/quizSet**. The JSON should contain the four fields:

**category**: a string, required;  **description**: a string, required;  **quizzes**: an array of quizzes, required, should contain at least 5 items; 


````JSON
{

"category":"Test",

"description":"Test",

"quizzes":[{

"title":  "Coffee drinks",

"text":  "Select only coffee drinks.",

"options":  ["Americano","Tea","Cappuccino","Sprite"],

"answer":  [0,2]

},{

"title":  "Coffee drinks",

"text":  "Select only coffee drinks.",

"options":  ["Americano","Tea","Cappuccino","Sprite"],

"answer":  [0,2]

},{

"title":  "Coffee drinks",

"text":  "Select only coffee drinks.",

"options":  ["Americano","Tea","Cappuccino","Sprite"],

"answer":  [0,2]

}

  

]
````


**Delete a Quiz set**
---------------------------------------

A user can delete their quiz set by sending the DELETE request to  **/api/quizSet/{id}.**

If the operation was successful, the service returns the  _204_  (No content) status code without any content.

If the specified quiz does not exist, the server returns  _404_  (Not found). If the specified user is not the author of this quiz, the response is the  _403_  (Forbidden) status code.



**Get a Quiz set by Id**
---------------------------------------
To get a quiz set by id, the client sends the GET request to  **/api/quizSet/{id}**.

The server response is a JSON with four fields: id, category, description and quizzes. Here is an example:
````JSON
{
        "id": 174,
        "category": "Test",
        "description": "Test",
        "quizzes": [
            {
                "id": 173,
                "title": "Coffee drinks",
                "text": "Select only coffee drinks.",
                "options": [
                    "Americano",
                    "Tea",
                    "Cappuccino",
                    "Sprite"
                ]
            },
            {
                "id": 175,
                "title": "Coffee drinks",
                "text": "Select only coffee drinks.",
                "options": [
                    "Americano",
                    "Tea",
                    "Cappuccino",
                    "Sprite"
                ]
            },
            {
                "id": 176,
                "title": "Coffee drinks",
                "text": "Select only coffee drinks.",
                "options": [
                    "Americano",
                    "Tea",
                    "Cappuccino",
                    "Sprite"
                ]
            }
        ]
    }
````
**Get all Quiz sets**
---------------------------------------
To get all quiz sets, the client sends the GET request to  **/api/allQuizSets**.

````JSON
[
    
    {
        "id": 174,
        "category": "Test",
        "description": "Test",
        "quizzes": [
            {
                "id": 173,
                "title": "Coffee drinks",
                "text": "Select only coffee drinks.",
                "options": [
                    "Americano",
                    "Tea",
                    "Cappuccino",
                    "Sprite"
                ]
            },
            {
                "id": 175,
                "title": "Coffee drinks",
                "text": "Select only coffee drinks.",
                "options": [
                    "Americano",
                    "Tea",
                    "Cappuccino",
                    "Sprite"
                ]
            },
            {
                "id": 176,
                "title": "Coffee drinks",
                "text": "Select only coffee drinks.",
                "options": [
                    "Americano",
                    "Tea",
                    "Cappuccino",
                    "Sprite"
                ]
            }
        ]
    },
    {
        "id": 178,
        "category": "Test",
        "description": "Test",
        "quizzes": [
            {
                "id": 177,
                "title": "Coffee drinks",
                "text": "Select only coffee drinks.",
                "options": [
                    "Americano",
                    "Tea",
                    "Cappuccino",
                    "Sprite"
                ]
            },
            {
                "id": 179,
                "title": "Coffee drinks",
                "text": "Select only coffee drinks.",
                "options": [
                    "Americano",
                    "Tea",
                    "Cappuccino",
                    "Sprite"
                ]
            },
            {
                "id": 180,
                "title": "Coffee drinks",
                "text": "Select only coffee drinks.",
                "options": [
                    "Americano",
                    "Tea",
                    "Cappuccino",
                    "Sprite"
                ]
            }
        ]
    }
]



