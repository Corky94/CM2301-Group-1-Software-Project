/* A simple server in the internet domain using TCP
   The port number is passed as an argument 
   This version runs forever, forking off a separate 
   process for each connection
*/
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h> 
#include <sys/socket.h>
#include <netinet/in.h>

void dostuff(int); /* function prototype */

// Called when a system call fails. 
// Displays message about error then aborts program.
void error(const char *msg)
{
    perror(msg);
    exit(1);
}

int main(int argc, char *argv[])
{
    /*sockfd & newsockfd store values returned by socket system
    and accept system call
    portno stores portnumber where server accepts connections
    pid = process ID. Used to keep track of clients (I think)*/
    int sockfd, newsockfd, portno, pid;
    socklen_t clilen;
    struct sockaddr_in serv_addr, cli_addr;

    if (argc < 2) {
        fprintf(stderr,"ERROR, no port provided\n");
        exit(1);
    }
    sockfd = socket(AF_INET, SOCK_STREAM, 0);
    if (sockfd < 0) 
        error("ERROR opening socket");
        
    /*sets all values in buffer to 0
    argument 1 = pointer to buffer
    argument 2 = size of buffer */
    bzero((char *) &serv_addr, sizeof(serv_addr));
    
    portno = atoi(argv[1]); //convert from string to int
    serv_addr.sin_family = AF_INET;
    
    /*contains ip address of machine server is running on*/
    serv_addr.sin_addr.s_addr = INADDR_ANY;
    
    /*contains port number converted to network byte order*/
    serv_addr.sin_port = htons(portno);
    
    /*binds socket to address
    arg 1: socket file descriptor 
    arg 2: address bound
    arg 3: size of address which is bound to it.*/
    if (bind(sockfd, (struct sockaddr *) &serv_addr,
        sizeof(serv_addr)) < 0) 
        error("ERROR on binding");
        
    /*listen on socket for connection
    arg 1: socket file descriptor
    arg 2: size of backlog, i.e., number of connections that can be waiting*/
    listen(sockfd,5);
    
    /*
    1. Put accept statement in infinite loop
    2. After connection established, call fork() to create new process
    3. Child process will close sockfd and call dostuff(). When two processes
        have finished their conversation, as indicated by dostuff() returning
        this process exits
    4. parent process closes newsockfd*/
    clilen = sizeof(cli_addr);
    while (1) {
        newsockfd = accept(sockfd, 
            (struct sockaddr *) &cli_addr, &clilen);
        if (newsockfd < 0) 
            error("ERROR on accept");
        pid = fork();
        if (pid < 0)
            error("ERROR on fork");
        if (pid == 0)  {
            close(sockfd);
            dostuff(newsockfd);
            exit(0);
        }
        else close(newsockfd);
    } /* end of while */
    close(sockfd);
    return 0; /* we never get here */
}

/******** DOSTUFF() *********************
 There is a separate instance of this function 
 for each connection.  It handles all communication
 once a connnection has been established.
 *****************************************/
void dostuff (int sock)
{
    int n;
    char buffer[256];
    
    /*only get here once connection successful*/
    /*initialize buffer*/
    bzero(buffer,256);
    
    /*read from socket*/
    n = read(sock,buffer,255);
    if (n < 0) error("ERROR reading from socket");
    printf("Here is the message: %s\n",buffer);
    
    /*send acknowledgement to client.
    18 = size of the message*/
    n = write(sock,"I got your message",18);
    if (n < 0) error("ERROR writing to socket");
}
