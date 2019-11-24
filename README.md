# FileTransfer
netcat-like utility for transfer files via tcp/ip connection

## For build use
```
gradle build
```

## For get help use
```
java -jar ft.jar -h

FileTransfer is here...
usage: [OPTIONS] [FILES]
FileTransfer utiltity
        -address <arg>  Set target or accept port address for send file. Example: localhost:9000 for
                        sending, :9000 for receiving.
        -file <arg>     Set file name for receive or send
        -h,--help       Show help information
        -receive        Receives file
        -send           Sends file
AG
```

## For send file use
```
java -jar ft.jar -send -file <filename> -address host:port
```

## Sending MP4 Linux Example
```
for i in *.MP4; do java -jar ~/bin/ft/ft.jar -send -address 192.168.0.105:9000 -file "$i"; done
```

## For receive files use
In this mode ft starts in accept mode & ready to receive files.
Use Ctrl + C to abort receiving files.
```
java -jar ft.jar -receive -address :9000
```
