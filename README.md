# tasks-for-BG
## First Task
Execute main method at tasks.first.Main
## Second Task
Go to project root folder and execute at command line: mvn spring-boot:run

Test service with
- Add user: 
curl localhost:8080/add/user/junir/gabidullin/22.11.1994/g.junir@gmail.com/AwwwWhereIsEncryptingBoooi
i.e curl localhost:8080/add/user/{firstName}/{LastName}/{birhday}/{email}/{password}
- Find user:
curl localhost:8080/find/user/g.junir@gmail.com
- Show all users:
curl localhost:8080/all
- Delete user:
curl localhost:8080/delete/user/g.junir@gmail.com
## Third task
Execute main method at tasks.third.YTranslate

