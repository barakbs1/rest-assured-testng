# rest-assured-testng example project

These tests are aimed to test a petstore server which runs on local docker. In order to run the tests, first you must 
run the server, and then run the API tests.

### Run petstore Server
1. Pull the petstore docker: `docker pull swaggerapi/petstore`
2. Run the docker image in a docker container: `docker run -d --name petstore-server -e SWAGGER_HOST=http://petstore.swagger.io -e 
SWAGGER_URL=http://localhost -e SWAGGER_BASE_PATH=/v3 -p 8080:8080 swaggerapi/petstore`

### Run petstore API tests
1. Pull the petstore API tests image: `docker pull barakbensimhon1/rest-assured-testng:latest`
2. In order to run the API tests against the petstore server, you should provide the petstore server IP address. To get 
the pet store server run: `docker inspect petstore-server`. You can find the IP address under `NetworkSettings > IPAddress`
3. Run the API tests container, which also runs the tests: `docker run --name api-tester 
-e PETSTORE_SERVER_HOST=http://<<PETSROTE SERVER IP>> barakbensimhon1/rest-assured-testng:latest`
4. When test run ends, you can see the test result in the console
    