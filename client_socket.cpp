#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>

void error(const char *msg)
{
    perror(msg);
    exit(0);
}

int main(int argc, char *argv[])
{
    int sockfd, portno, n;
    struct sockaddr_in serv_addr;
    struct hostent *server;

    //server reads characters from socket connection into this buffer
    char buffer[256];
    if (argc < 3) {
       fprintf(stderr,"usage %s hostname port\n", argv[0]);
       exit(0);
    }

    portno = atoi(argv[2]); //parses string as int
    /*AF_INET is address domain of the socket
    SOCK_STREAM is type of socket (TCP). SOCK_DGRAM for UDP
    3rd argument is protocol. Always 0 unless unsual circumstances.*/
    sockfd = socket(AF_INET, SOCK_STREAM, 0);
    if (sockfd < 0)
        error("ERROR opening socket");

    server = gethostbyname(argv[1]);
    if (server == NULL) {
        fprintf(stderr,"ERROR, no such host\n");
        exit(0);
    }

    /*sets all values in buffer to 0
    argument 1 = pointer to buffer
    argument 2 = size of buffer */
    bzero((char *) &serv_addr, sizeof(serv_addr));

    serv_addr.sin_family = AF_INET;

    /*void bcopy(char *s1, char *s2, int length)
    bcopy copies length bytes from s1 to s2*/
    bcopy((char *)server->h_addr,
         (char *)&serv_addr.sin_addr.s_addr,
         server->h_length);
    serv_addr.sin_port = htons(portno); //convert to network bytes

    /*connect function called by client to establish server connection
    arg 1: socket file descriptor
    arg 2: address of host
    arg 3: size of the address
    returns 0 on success and -1 on fail*/
    if (connect(sockfd,(struct sockaddr *) &serv_addr,sizeof(serv_addr)) < 0)
        error("ERROR connecting");

    printf("Please enter the message: ");
    bzero(buffer,256); //initialize buffer.
    fgets(buffer,255,stdin); // reads message from stdin
    n = write(sockfd,buffer,strlen(buffer)); // write message to socket
    if (n < 0)
         error("ERROR writing to socket");
    bzero(buffer,256);
    n = read(sockfd,buffer,255); //reads reply
    if (n < 0)
         error("ERROR reading from socket");
    printf("%s\n",buffer); //display message on screen
    close(sockfd);
    return 0;
}
