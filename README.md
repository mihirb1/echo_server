# About
Echo is a simple framework for building client-server applications.

The echo client is a simple console user interface that perpetually prompts its user for a request, forwards the request to a server, then displays the server's response.

Upon receiving a request, the server spawns a request handler thread connected to the client, then resumes listening for more requests.

The request handler's run method begins a request-response-loop with the client. The loop ends when the client sends the "quit" request.

# Testing
To test echo you'll need to run the server and several clients in different Java virtual machines (JVMs). This can be done easily within the IntelliJ IDE. Simply right click the Server in the Project tab and select "run". Then do the same for SimpleClient. By selecting "allow multiple instances" in the run configuration under "modify options" drop down menu you can have multiple simple clients talking to the server.

Starting the Client
In a separate command window on the same directory start the simple client:

C:\Users\000030278\Desktop\bin>java echo.SimpleClient
-> hello
sending: hello
received: echo: hello
-> bye
sending: bye
received: echo:bye
-> quit
bye

Here's the output on the server side:

Server listening at port 5555
received: hello
sending: echo hello
received: bye
sending: echo bye
received: quit
request handler shutting down

# Implement MathHandler class
Implement a math handler extending the request handler able to execute commands of the form:

command ::= operator num num etc.
operator ::= add | mul | sub | div
num ::= any number

Starting the math server:

C:\Users\smith\Desktop\bin>java echo.Server math.MathHandler
server address: 0.0.0.0/0.0.0.0
Server listening at port 5555

Starting the client:

C:\Users\000030278\Desktop\bin>java echo.SimpleClient
-> add 2 3 4
9.0
-> mul 2 3 4
24.0
-> quit
bye
