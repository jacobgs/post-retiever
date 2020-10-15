# Purpose
This project has been created by Jacob Han (j.gs.han@outlook.com) for delivering Westpac Online Assessment.

# Prerequisite
- Java 1.8
- npm

# Components
## post-retriever-server
`post-retriever-server` is a Spring based RESTful server which retrieves posts from [SimpleJsonServer](https://jsonplaceholder.typicode.com/) and exposes the data through the following three endpoints;
- /posts/all 
- /posts/{postId}
- /posts/searches - this endpoint supports a query parameter called 'userId'

All of the above endpoints supports additional query parameter called 'embed'. Currently, only 'comments' is supported.

This application doesn't return posts and comments aggregated DTO. Instead, it give an option to the client to choose whether to retrieve comment data or not by setting 'embed' query parameters.

## post-retriever-web-app
`post-retriever-web-app` is a React based web application which consumes `post-retriever-server`.
By default, it uses 'embed' query parameter and retrieves comment data along with the post data.


# Usage
1. Checkout this project
```
git clone https://github.com/jacobgs/post-retiever.git
```
2. Navigate into the checked out project
```
cd post-retiever/
```
3. Navigate into the server directory
```
cd post-retriever-server
```
4. Run the server with using `gradlew`
```
./gradlew clean build run
```
5. Open another terminal or command prompt and navigate into the web app directory
```
cd ${project_dir}/post-retriever-web-app
```
6. Download all Node modules and run the project
```
npm install && npm start
```

